package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField userField;
    private JPanel usersListPanel;
    private JButton btnAdd;
    private JLabel addUserLabel;
    private JButton btnExit;

    public UsersScreen() {
        super("Users");
        addComponents();
    }

    @Override
    protected void addComponents() {

        createLogoCenter(0, 0, 2);

        btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
        this.ActionListinerBtn(btnExit, new AdmScreen());
        add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));

        add(getAddUserLabel(), getConstraints(0, 1, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getUserField(), getConstraints(1, 1, GridBagConstraints.CENTER, 20, 10, 0, 0));
        add(getBtnAdd(), getConstraints(2, 1, GridBagConstraints.CENTER, 20, 10, 0, 20));

        usersListPanel = new JPanel();
        usersListPanel.setLayout(new BoxLayout(usersListPanel, BoxLayout.Y_AXIS));  // Layout vertical
        JScrollPane scrollPane = new JScrollPane(usersListPanel);
        scrollPane.setPreferredSize(new Dimension(300, 200));  // Tamanho da área de exibição
        add(scrollPane, getConstraints(0, 2, GridBagConstraints.CENTER, 20, 0, 0, 0, 3));  // Adiciona o painel
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right, int gridwidth) {
        GridBagConstraints gbc = getConstraints(gridx, gridy, anchor, top, left, bottom, right);
        gbc.gridwidth = gridwidth;
        return gbc;
    }

    public JTextField getUserField() {
        if (userField == null) {
            userField = new JTextField(15);
        }
        return userField;
    }

    public JButton getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = createIcon(20, 20, 1, 0, GridBagConstraints.NORTHEAST, "/icons/add.png");
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userName = getUserField().getText().trim();
                    if (!userName.isEmpty()) {
                        addUserToList(userName);  // adiciona o nome do usuário à lista
                        getUserField().setText("");  // limpa o caixa de texto
                    }
                }
            });
        }
        return btnAdd;
    }

    public JLabel getAddUserLabel() {
        if (addUserLabel == null) {
            addUserLabel = new JLabel("Adicionar usuário:");
        }
        return addUserLabel;
    }

    // aqui é adicionado um usuário à lista com o ícone de lixeira ao lado dele
    private void addUserToList(String userName) {
        // cria um painel para cada usuário
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel(userName);
        JButton btnDelete = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/trash-bin.png");

        // botão que remove o usuário da lista ao clicar na lixeira
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersListPanel.remove(userPanel);  // remove o painel do usuário
                usersListPanel.revalidate();  // atualiza a lista
                usersListPanel.repaint();
            }
        });

        userPanel.add(userLabel);
        userPanel.add(btnDelete);

        usersListPanel.add(userPanel);
        usersListPanel.revalidate();
        usersListPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsersScreen frame = new UsersScreen();
            frame.showScreen();
        });
    }
}
