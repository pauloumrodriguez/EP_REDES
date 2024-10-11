package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import javax.swing.JButton;

public class UpdateScreen extends Screen{
	private static final long serialVersionUID = 1L;
	
	public UpdateScreen() {
		super("Update Lobby");
		addComponents();
	}
	
	protected void addComponents() {//Cria o layout da página
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});
		

		JButton btnUpdateUser = this.createButton(1, 2, GridBagConstraints.CENTER, "Update User");
		
		btnUpdateUser.addActionListener(e -> {
			this.dispose();
			UpdateUserScreen updateUser = new UpdateUserScreen();
			updateUser.showScreen();
		});
	
	}
	
}
