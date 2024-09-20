package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import javax.swing.*;

public class ForgotKeyScreen extends Screen {
	
	private static final long serialVersionUID = 1L;
	private JTextField userField;
	private JPasswordField passwordField;
	private JPasswordField confirmField;
	
	public ForgotKeyScreen() {
		super("Forgot Screen");
		addComponents();
	}
	
	protected void addComponents() {
		JButton setaBtn = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		this.ActionListinerBtn(setaBtn, new Login());
		this.createLogoCenter(0, 0, 2);
		userField = this.createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = this.createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		confirmField = this.createPasswordField(1, 3, GridBagConstraints.WEST, "Confirm Password:");
		JButton resetBtn = this.createButton(1, 4, GridBagConstraints.CENTER, "Reset Password");
		this.ActionListinerBtn(resetBtn, new Login());//observation
	}
	
	public String getUser() {
		return userField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getConfirm() {
		return confirmField.getText();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new ForgotKeyScreen(); 
            frame.showScreen();
        });
    }
}
