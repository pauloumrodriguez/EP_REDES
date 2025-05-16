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
	
	protected void addComponents() {//Cria layout da página
		createLogoCenter(0, 0, 3);//Cria logo
		JButton btnExit = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png"); //Botão de voltar
		
		btnExit.addActionListener(e -> {
			this.dispose();
			Login login = new Login();
			login.showScreen(); //Volta para a página de login
		});

		
		userField = this.createTextField(1, 1, GridBagConstraints.WEST, "User:");
		passwordField = this.createPasswordField(1, 2, GridBagConstraints.WEST, "Password:");
		confirmField = this.createPasswordField(1, 3, GridBagConstraints.WEST, "Confirm Password:");
		JButton btnReset = this.createButton(1, 4, GridBagConstraints.CENTER, "Reset Password"); //Atualiza a senha
		
		btnReset.addActionListener(e -> {
			if(!getUserField().isEmpty() && getPasswordField().length > 0 && getConfirmField().length > 0) { //Verifica se os campos não estão vazios

				if(new String(getPasswordField()).equals(new String(getConfirmField()))) { //Verifca se os campos "senha" e "confirmar senha" são iguais
					String message = "\"UPDATE-PASSWORD\";\"" + getUserField() + "\";\"" + new String(getPasswordField()) + "\";\"" +
				new String(this.getConfirmField()) + "\":";
					List<StringBuilder> list = sendMessage(message); //Pede para atualizar
					if(!list.get(0).toString().equals("\"Updated with success\"")) {
						//Exibe o Pop-Up de erro
						JOptionPane.showMessageDialog(null, list.getFirst().toString().replace("\"", "").trim(), "Error", JOptionPane.ERROR_MESSAGE);
						clearFields(); //Limpa os campos
					}
					else {
						this.dispose();
						Login login = new Login();
						login.showScreen(); //Volta para o login
					}
				} else {
					clearFields();
					JOptionPane.showMessageDialog(null, "Enter the same password in the fields"); // As senhas não são iguais
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
	
    private void clearFields() { //Limpa os campos
    	userField.setText("");
    	passwordField.setText("");
    	confirmField.setText("");
    }
	
}
