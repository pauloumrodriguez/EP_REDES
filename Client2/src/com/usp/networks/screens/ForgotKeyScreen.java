package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import javax.swing.*;
import java.util.List;

public class ForgotKeyScreen extends Screen {
	
	private static final long serialVersionUID = 1L;
	private JTextField userField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	
	public ForgotKeyScreen() {
		super("Forgot Password");
		addComponents();
	}
	
	protected void addComponents() {
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		btnExit.addActionListener(e -> {
			this.dispose();
			Login login = new Login();
			login.showScreen();
		});

		
		userField = this.createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = this.createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		confirmField = this.createPasswordField(1, 3, GridBagConstraints.WEST, "Confirm Password:");
		JButton btnReset = this.createButton(1, 4, GridBagConstraints.CENTER, "Reset Password");
		
		btnReset.addActionListener(e -> {
			if(!getUserField().isEmpty() && getPasswordField().length > 0 && getConfirmField().length > 0) {

				if(new String(getPasswordField()).equals(new String(getConfirmField()))) {
					String message = "\"UPDATE-PASSWORD\";\"" + getUserField() + "\";\"" + new String(getPasswordField()) + "\";\"" +
				new String(this.getConfirmField()) + "\":";
					List<StringBuilder> list = sendMessage(message);
					if(!list.get(0).toString().equals("\"Updated with sucess\"")) {
						JOptionPane.showMessageDialog(null, list.getFirst().toString().replace("\"", "").trim(), "Error", JOptionPane.ERROR_MESSAGE);
						clearFields();
					}
					else {
						this.dispose();
						Login login = new Login();
						login.showScreen();
					}
				} else {
					clearFields();
					JOptionPane.showMessageDialog(null, "Enter the same password in the fields");
				}
				
			}
		});
	}
	
	public String getUserField() {
		return userField.getText().trim();
	}
	
	public char[] getPasswordField() {
		return passwordField.getPassword();
	}
	
	public char[] getConfirmField() {
		return confirmField.getPassword();
	}
	
    private void clearFields() {
    	userField.setText("");
    	passwordField.setText("");
    	confirmField.setText("");
    }
	
}
