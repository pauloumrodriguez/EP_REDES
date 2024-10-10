package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Login extends Screen{
	private static final long serialVersionUID = 1L;
	private static int idUser;
	
	private JTextField userField;
	private JPasswordField passwordField;
	private ArrayList<StringBuilder> list;

	public Login() {
		super("Login");
		addComponents();
	}
	
	@Override
	protected void addComponents() {
		userField = createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		JLabel forgotPassword = createLink(1, 3, GridBagConstraints.WEST, "Forgot Password?");
		MouseListiner(forgotPassword, new ForgotKeyScreen());
		
		JButton btnLogin = createButton(1, 4, GridBagConstraints.CENTER, "Enter");
		btnLogin.addActionListener(e -> {
			String message = "LOGIN;" + getUserField() + ";" + new String(getPasswordField()) + ":";
			list = new ArrayList<>(sendMessage(message));
			
			if(checkResponse(list)) {
				this.dispose();
			}
			
		});
		
		createLogoCenter(0, 0, 2);
	}
	
	public String getUserField() {
		return userField.getText();
	}
	
	public char[] getPasswordField() {
		return passwordField.getPassword();
	}
	
    public static boolean checkResponse(ArrayList<StringBuilder> list) {
        if (list != null) {
            for (StringBuilder sb : list) {
                String[] value = sb.toString().split("\\|");
                
                String mensagemSemAspas = value[0].replace("\"", "").trim();
                String valorBooleano = value[1].trim();
                idUser = Integer.parseInt(value[2].trim());
                
                if (mensagemSemAspas.trim().equals("Connect with sucess")) {
                	if(Boolean.parseBoolean(valorBooleano)) {
                        AdmScreen admScreen = new AdmScreen();
                        admScreen.showScreen();
                	}
                	else {
                        XYScreen xyScreen = new XYScreen();
                        xyScreen.showScreen();
                    }
                	
                	return true;
                	
                }
 
            }
            
        }
        
        return false;
    }
    
    
    public static int getIdUser() {
    	return idUser;
    }
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login(); 
            frame.showScreen();
        });
        
    }
}
