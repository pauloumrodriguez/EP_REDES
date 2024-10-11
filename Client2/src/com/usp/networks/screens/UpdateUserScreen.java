package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.HashMap;


public class UpdateUserScreen extends Screen{
	private static final long serialVersionUID = 1L;
	
	private JTextField userField, fnameField, lnameField, passwordField;
    private Boolean isAdmin = true;
    private HashMap<String, Integer> idUsers;

	
	public UpdateUserScreen() {
		super("Update User");
		addComponents();
	}
	
	protected void addComponents() { //Cira o layout da página
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		idUsers = new HashMap<>();
		
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});

		
        userField = createTextField(1, 1, GridBagConstraints.WEST, "Login:");
        fnameField = createTextField(1, 2, GridBagConstraints.WEST, "First Name:");
        lnameField = createTextField(1, 3, GridBagConstraints.WEST, "Last Name:");
        passwordField = createTextField(1, 4, GridBagConstraints.WEST, "Senha:");
        
        JComboBox<String> adminField = createComboBox(1, 5, GridBagConstraints.WEST, "Admin:");
        adminField.addItem("Yes");
        adminField.addItem("No");
        
        adminField.addActionListener(e -> { //Selecionou o campo "Admin"
             String opcao = (String) adminField.getSelectedItem();
             if(opcao.equals("Yes")) {
            	 isAdmin = true;
             } else {
            	 isAdmin = false;
             }
        });
        
        String msgList = "\"LIST-USER\";";
		List<StringBuilder>listResponse = sendMessage(msgList); //Pede a lista de usuários
      
        for (int i = 0; i < listResponse.size(); i++) {
        	String UserContent = listResponse.get(i).toString().replace("\"", "");
        	String[] splitUserContent = decode(UserContent); //Decodifica os campos
        	
        	
            String id = splitUserContent[0]; // ID do usuários
            String email = splitUserContent[3]; // email
            
            idUsers.put(email, Integer.parseInt(id));	
        }
        
		JButton btnUpdate = this.createButton(1, 6, GridBagConstraints.CENTER, "Update User");
		
		btnUpdate.addActionListener(e -> {
			if(!getUser().isEmpty() && !getPassword().isEmpty() && !getFname().isEmpty() && !getLname().isEmpty()) {//Verifica os campos não estão vazios
					if(getUser().equals(Login.getLogin())) {
						JOptionPane.showMessageDialog(null, "Can't update yourself", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String message = "\"UPDATE-USER\";\"" + idUsers.get(getUser()) + "\";\"" + getFname() + "\";\"" + getLname() + "\";\"" + getUser() + "\";\"" + getPassword() + "\";\"" + isAdmin +"\":";
						List<StringBuilder> list = sendMessage(message);//Pede para atualizar usuário
						if(list.get(0).toString().equals("\"User updated with success\"")) {
							JOptionPane.showMessageDialog(null, "User updated with success", "OK", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Unable to update", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				} 
			clearFields();
				
		});
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
   
    
    private void clearFields() {
        userField.setText("");
        fnameField.setText("");
        lnameField.setText("");
        passwordField.setText("");
    }
	    
}
