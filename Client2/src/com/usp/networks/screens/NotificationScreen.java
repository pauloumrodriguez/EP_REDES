package com.usp.networks.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class NotificationScreen extends Screen{
	
	private static final long serialVersionUID = 1L;
	private JButton btnExit;
	private JButton btnDelete;
	private JLabel notifys;
	private JList<String> list;
	private JPanel space;
    private DefaultListModel<String> listModel;
	
	private String selectedItem;
	private List<StringBuilder> listResponse;
	
	public NotificationScreen() {
		super("Notifications");
		addComponents();
		
	}
	
	protected void addComponents() {
		
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);

		
//		String msg = "\"LIST-NOTIFY\";" + Login.getIdUser() + "\":";
        String msg = "\"LIST-NOTIFY\";" + Login.getIdUser() + "\":";
		listResponse = sendMessage(msg);
		
        for (int i = 1; i < listResponse.size(); i++) {
        	String notifyContent = listResponse.get(i).toString().replace("\"", "");
        	String[] splitNotifyContent = decodeNotification(notifyContent);
        	
        	String id = splitNotifyContent[0]; //id
            String sender = splitNotifyContent[1]; // sender
            String message = splitNotifyContent[2]; // message
          
            listModel.addElement(id + " | " + "Sender: " + sender + "| Message: " + message);
        }
		
		
		
		this.createLogoCenter(0,0, 3);
        notifys = createLabel(1, 1, GridBagConstraints.SOUTH, "Notifications");
        notifys.setFont(new Font("Arial", Font.PLAIN, 17));
        
		btnDelete = createIcon(40, 40, 4, 2, GridBagConstraints.EAST, "/icons/trash-bin.png");
		btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica se algum item está selecionado
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove o item do modelo (isso atualiza o JList automaticamente)
            		String[] rs = selectedItem.split("\\|");
            		for (int i = 0; i < rs.length; i++) {
            			rs[i] = rs[i].trim();
            		}
        			String msgDel = "\"DELETE-NOTIFY\";\"" + rs[0] + "\":";
            		List<StringBuilder> listMSG = sendMessage(msg);
            		
            		if(listMSG.get(0).toString().equals("\"Notification deleted with sucess\"")) {
                        listModel.remove(selectedIndex);
            		}
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum item selecionado para remover.", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            }
			
		});
		
		
		
		btnExit = createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});
        
		        
		
	    // Adiciona espaçamento entre os itens
	    list.setCellRenderer(new DefaultListCellRenderer() {
	        @Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            label.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7)); // Espaçamento entre os itens
	            return label;
	        }
	    });
	    
	    // Captura o item selecionado
	    list.addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting()) {
	            selectedItem = list.getSelectedValue();
	        }
	    });
		
//		list.setEnabled(false);
		list.setPreferredSize(new Dimension(350, 110));
		
		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane, getConstraints(1, 2, GridBagConstraints.CENTER, 30, 0, 0, 20));
		
	}
	
    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }
    
	private String[] decodeNotification(String notify) {
		String[] clean = notify.split(",");
		return clean;
	}
	
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new NotificationScreen(); 
            frame.showScreen();
        });
    }
	
}
