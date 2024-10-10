package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class UpdateUserScreen extends Screen{
	private static final long serialVersionUID = 1L;
	
	private JTextField userField, fnameField, lnameField, passwordField;
	private JComboBox<String> adminField;
    private Boolean isAdmin = true;

	
	public UpdateUserScreen() {
		super("Update User");
		addComponents();
	}
	
	protected void addComponents() {
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});

		
        userField = createTextField(1, 1, GridBagConstraints.WEST, "Login:");
        fnameField = createTextField(1, 2, GridBagConstraints.WEST, "First Name:");
        lnameField = createTextField(1, 3, GridBagConstraints.WEST, "Last Name:");
        passwordField = createTextField(1, 4, GridBagConstraints.WEST, "Senha:");
        
        adminField = createComboBox(1, 5, GridBagConstraints.WEST, "Admin:");
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
        
		JButton btnUpdate = this.createButton(1, 6, GridBagConstraints.CENTER, "Update User");
		
		btnUpdate.addActionListener(e -> {
			if(!getUser().isEmpty() && !getPassword().isEmpty() && getFname().isEmpty() && getLname().isEmpty()) {
					String message = "\"UPDATE-USER\";\"" + getFname() + "\";\"" + getLname() + "\";\"" + getUser() + "\";\"" + getPassword() + "\";\"" + isAdmin +"\":";
					List<StringBuilder> list = sendMessage(message);
					if(!list.get(0).toString().equals("\"User updated with sucess\"")) {
						showErrorPopup("Não foi possível atualizar o usuário!");
						clearFields();
					}
					else {
						this.dispose();
						AdmScreen admScreen = new AdmScreen();
						admScreen.showScreen();
					}
				} else {
					clearFields();
				}
				
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
	
    private static void showErrorPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new UpdateUserScreen(); 
            frame.showScreen();
        });
    }
}
