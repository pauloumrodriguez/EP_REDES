package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;

public class UsersScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField userField;
    private JTextField passwordField;
    private JTextField fnameField;
    private JTextField lnameField;
    private String selectedItem;
    private DefaultListModel<String> listModel;
    private Boolean isAdmin = true;
    private HashMap<String, Integer> idUsers;
    
    public UsersScreen() {
        super("Users");
        idUsers = new HashMap<>();
        addComponents();
    }

    @Override
    protected void addComponents() { //Cria layout da página

        createLogoCenter(0, 0, 2);

        JButton btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
        this.ActionListinerBtn(btnExit, new AdmScreen());
        add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));
        
        
        listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        
    	
        updateList();
        
     // Captura o item selecionado
	    list.addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting()) {
	            selectedItem = list.getSelectedValue();
	        }
	    });
        
        JButton btnAdd = createIcon(20, 20, 2, 5, GridBagConstraints.EAST, "/icons/add.png");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getUser().isEmpty() && !getFname().isEmpty() &&
                		!getLname().isEmpty() && !getPassword().isEmpty()) {
                    CreateUser(getUser(), getFname(), getLname(), getPassword(), isAdmin);  // adiciona o nome do usuário à lista
                    clearFields();
                }
            }
            
        });
        
        JButton btnDelete = createIcon(40, 40, 4, 2, GridBagConstraints.EAST, "/icons/trash-bin.png");
        
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
            		String msg = "\"DELETE-USER\";\"" + idUsers.get(rs[3]) + "\":";
            		List<StringBuilder> listMSG = sendMessage(msg);//Deleta usuário
            		
            		if(listMSG.get(0).toString().equals("\"User deleted with success\"")) {
                        listModel.remove(selectedIndex);
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "Delete the association first", "Error", JOptionPane.ERROR_MESSAGE);
            		}
                } else {
                    JOptionPane.showMessageDialog(null, "No items selected to remove", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        userField = createTextField(1, 1, GridBagConstraints.WEST, "Login:");
        fnameField = createTextField(1, 2, GridBagConstraints.WEST, "First Name:");
        lnameField = createTextField(1, 3, GridBagConstraints.WEST, "Last Name:");
        passwordField = createTextField(1, 4, GridBagConstraints.WEST, "Senha:");
        
        JComboBox<String> adminField = createComboBox(1, 5, GridBagConstraints.WEST, "Admin:");
        adminField.addItem("Yes");
        adminField.addItem("No");
        
        adminField.addActionListener(e -> {
             String opcao = (String) adminField.getSelectedItem();
             if(opcao.equals("Yes")) {
            	 isAdmin = true;
             } else {
            	 isAdmin = false;
             }
        });
        
        list.setPreferredSize(new Dimension(350, 110));
		JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 200));  // Tamanho da área de exibição
        add(scrollPane, getConstraints(0, 6, GridBagConstraints.CENTER, 20, 0, 0, 0, 3));  // Adiciona o painel
    }

    public String getUser() {
        return userField.getText().trim();
    }
    
    public String getPassword(){
        return passwordField.getText().trim();
    }
    
    public String getFname() {
        return fnameField.getText().trim();
    }
    
    public String getLname(){
        return lnameField.getText().trim();
    }
   
    private void updateList() {
    	listModel.clear();
    	String msgList = "\"LIST-USER\";";
		List<StringBuilder>listResponse = sendMessage(msgList); //Pede a lista
      
        for (int i = 0; i < listResponse.size(); i++) {
        	String UserContent = listResponse.get(i).toString().replace("\"", "");
        	String[] splitUserContent = decode(UserContent);//Decodifica o registro
        	
        	
            String id = splitUserContent[0]; // ID do usuário
            String firstName = splitUserContent[1]; // FNAME
            String lastName = splitUserContent[2]; // LNAME
            String email = splitUserContent[3]; // email
            String password = splitUserContent[4]; // password
            String admin = splitUserContent[5]; // admin

            idUsers.put(email, Integer.parseInt(id));	 
            listModel.addElement(firstName + " | " + lastName + " | " +
            password + " | " + email + " | " + admin);
        }
    }

    // aqui é adicionado um usuário à lista com o ícone de lixeira ao lado dele
    private void CreateUser(String login, String fname, String lname, String password, Boolean admin) {
    	String msgCreate = "\"CREATE-USER\";\"" + fname + "\";\"" + lname + "\";\"" + login + "\";\"" + password + "\";\"" + admin + "\":";
		List<StringBuilder> list = sendMessage(msgCreate);//Pede para criar usuário
		if(list.get(0).toString().equals("\"User created with success\"")) {
			updateList();
		}
    }
    
    private void clearFields() {
        userField.setText("");
        fnameField.setText("");
        lnameField.setText("");
        passwordField.setText("");
    }
    
}
