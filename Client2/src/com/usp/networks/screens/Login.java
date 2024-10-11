package com.usp.networks.screens;

import javax.swing.*;

import com.usp.networks.client.Admin;
import com.usp.networks.client.Client;

import java.awt.*;
import java.util.List;


public class Login extends Screen{
	private static final long serialVersionUID = 1L;
	private static int idUser;
	private static String login;
	private static boolean admin;
	
	private JTextField userField;
	private JPasswordField passwordField;
	private List<StringBuilder> list;

	public Login() {
		super("Login");
		addComponents();
	}
	
	@Override
	protected void addComponents() { //Cria o layout da página
		userField = createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		JLabel forgotPassword = createLink(1, 3, GridBagConstraints.WEST, "Forgot Password?");
		MouseListiner(forgotPassword, new ForgotKeyScreen()); //Entra na tela de  "Esqueci senha"
		
		JButton btnLogin = createButton(1, 4, GridBagConstraints.CENTER, "Enter");
		btnLogin.addActionListener(e -> {
			if(!getUserField().isEmpty() && !(new String(getPasswordField()).isEmpty())) {
				String message = "LOGIN;" + getUserField() + ";" + new String(getPasswordField()) + ":";
				list = sendMessage(message);
				if(checkResponse(list)) {//Verifica se foi aceito o login
					login = getUserField();
					this.dispose(); //Fecha tela
				}
				else {
					clearFields();
					//Exibe Pop-Up de erro
					JOptionPane.showMessageDialog(null, list.getFirst().toString().replace("\"", "").trim(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				//Precisa preencher os campos
				JOptionPane.showMessageDialog(null, "Fill in the fields", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		createLogoCenter(0, 0, 2); //Cria a logo
	}
	
	public String getUserField() {
		return userField.getText();
	}
	
	public char[] getPasswordField() {
		return passwordField.getPassword();
	}
	
	private void clearFields() {
    	userField.setText("");
    	passwordField.setText("");
    }
	
    private boolean checkResponse(List<StringBuilder> list) { //Verifica se o login foi aceito
        if (list != null) {
            for (StringBuilder sb : list) {
                String[] value = sb.toString().split("\\|");
                
                String mensagemSemAspas = value[0].replace("\"", "").trim();
                
                if (mensagemSemAspas.trim().equals("Connect with success")) {
                	String valorBooleano = value[1].trim();
                    admin = Boolean.parseBoolean(valorBooleano);
                	idUser = Integer.parseInt(value[2].trim());
                	if(admin) { //Verifica se é admin
                        Admin.getInstance().show();
                	}
                	else {
                        Client.getInstance().show();
                    }
                	
                	return true;
                	
                }
 
            }
            
        }
        
        return false;
    }
    
    
    public static int getIdUser() { //Devolve ID do usuário
    	return idUser;
    }
    
    public static String getLogin() { //Devolve login do usuário
    	return login;
    }
    
    public static boolean getAdmin() { //Devolve admin
    	return admin;
    }
	
}
