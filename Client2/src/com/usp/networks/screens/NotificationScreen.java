package com.usp.networks.screens;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class NotificationScreen extends Screen {

    private static final long serialVersionUID = 1L;
    private String selectedItem;

    public NotificationScreen() {
        super("Notifications");
        addComponents();
    }

    protected void addComponents() {//Cria o layout da página
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        
        // Definir o renderizador personalizado
        list.setCellRenderer(new CustomListCellRenderer());

        // Define a altura fixa das células
        list.setFixedCellHeight(20);

        // Cria o JScrollPane e ajusta as políticas de rolagem
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Força a largura mínima do conteúdo da lista
        list.setPrototypeCellValue("This is a very long text that ensures the horizontal scrollbar appears.");

        // Adiciona o JScrollPane ao layout
        add(scrollPane, getConstraints(1, 2, GridBagConstraints.CENTER, 20, 10, 0, 0));

        String msg = "\"LIST-NOTIFY\";\"" + Login.getLogin() + "\";\"" + Login.getAdmin() + "\":";
        List<StringBuilder> listResponse = sendMessage(msg); //Pede as notificações

        if (listResponse == null) { //Volta se não há notificações
            Screen back;
            if (Login.getAdmin()) back = new AdmScreen();
            else back = new XYScreen();
            back.showScreen();
        }

        for (int i = 0; i < listResponse.size(); i++) {
            String notifyContent = listResponse.get(i).toString().replace("\"", "");
            String[] splitNotifyContent = decode(notifyContent);
            String id = splitNotifyContent[0]; //id
            String user = splitNotifyContent[1]; //user
            String sender = splitNotifyContent[2]; //sender
            String message = splitNotifyContent[3]; //message
            
            if (Login.getAdmin()) {
                listModel.addElement(id + " | Recipient: " + user + " | Message: " + message);
            } else {
                listModel.addElement(id + " | Sender: " + sender + " | Message: " + message);
            }
        }

        this.createLogoCenter(0, 0, 3);
        JLabel notifys = createLabel(1, 1, GridBagConstraints.SOUTH, "Notifications");
        notifys.setFont(new Font("Arial", Font.PLAIN, 17));

        JButton btnDelete = createIcon(40, 40, 4, 2, GridBagConstraints.EAST, "/icons/trash-bin.png");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String[] rs = selectedItem.split("\\|");
                    for (int i = 0; i < rs.length; i++) {
                        rs[i] = rs[i].trim();
                    }
                    String msgDel = "\"DELETE-NOTIFY\";\"" + rs[0] + "\":";
                    List<StringBuilder> listMSG = sendMessage(msgDel); //Pede para excluir notificação

                    if (listMSG.get(0).toString().equals("\"Notification deleted with success\"")) {
                        listModel.remove(selectedIndex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No items selected to remove", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnExit = createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
        btnExit.addActionListener(e -> {
            this.dispose();
            Screen back;
            if (Login.getAdmin()) back = new AdmScreen();
            else back = new XYScreen();
            back.showScreen();
        });

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedItem = list.getSelectedValue();
            }
        });

        // Define tamanho preferido da lista para garantir que a rolagem funcione
        list.setPreferredSize(new Dimension(350, 110));
    }

    // Custom renderer para garantir que o conteúdo da célula não seja cortado
    class CustomListCellRenderer extends JLabel implements ListCellRenderer<String> {
        
		private static final long serialVersionUID = 1L;

		public CustomListCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends String> list,
                String value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            setText(value);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            // Ajusta a largura da célula para forçar a barra horizontal
            setPreferredSize(new Dimension(800, 20));
            return this;
        }
    }
}
