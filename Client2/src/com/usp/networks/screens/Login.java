package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;

public class Login extends Screen{
	private static final long serialVersionUID = 1L;
	
	private JTextField userField;
	private JPasswordField passwordField;

	public Login() {
		super("Login");
		addComponents();
	}
	
	@Override
	protected void addComponents() {
		userField = createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		JLabel forgotPassword = createLink(1, 3, GridBagConstraints.WEST, "Forgot Password?");
		MouseListiner(forgotPassword, null);//forgotKey
		JButton btnLogin = createButton(1, 4, GridBagConstraints.CENTER, "Enter");
		ActionListinerBtn(btnLogin, null);//XYScreen
		createLogoCenter(0, 0, 2);
	}
	
	public String getText() {
		return userField.getText();
	}
	
	//Verify
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login(); 
            frame.showScreen();
        });
    }
}
