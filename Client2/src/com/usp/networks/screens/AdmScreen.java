package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;

public class AdmScreen extends Screen {
	private static final long serialVersionUID = 1L;
	
	public AdmScreen() {
		super("Admin");
		addComponents();
	}

	@Override
	protected void addComponents() { //Cria o layout da página

		JButton btnExit = createIcon(28, 28, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png"); //Botão de sair
		this.ActionListinerBtn(btnExit, new Login()); //Volta para a tela de login

		JButton btnSino = createIcon(25, 25, 2, 0, GridBagConstraints.NORTHEAST, "/icons/sino-png.png"); //Botão de notificação
		btnSino.addActionListener(e -> {
			this.dispose();
			NotificationScreen notificationScreen = new NotificationScreen();
			notificationScreen.showScreen(); //Abre a tela de notificações quando clicado
		});
		
		createLabel(0, 1, GridBagConstraints.WEST, "Users");
		JButton btnUser = createIcon(64, 64, 0, 2, GridBagConstraints.WEST, "/icons/user-128.png");
		btnUser.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir UsersScreen
			UsersScreen usersScreen = new UsersScreen();
			usersScreen.showScreen();  // Abre a nova tela UsersScreen
		});

		createLabel(1, 1, GridBagConstraints.CENTER, "Zones");
		JButton btnZones = createIcon(64, 64, 1, 2, GridBagConstraints.CENTER, "/icons/zones.png");
		btnZones.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir ZonesScreen
			ZonesScreen zonesScreen = new ZonesScreen();
			zonesScreen.showScreen();  // Abre a nova tela ZonesScreen
		});
		
		createLabel(2, 1, GridBagConstraints.EAST, "Messages");
		JButton btnMessages = createIcon(64, 64, 2, 2, GridBagConstraints.EAST, "/icons/messages.png");
		btnMessages.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir messageScreen
			MessageScreen messageScreen = new MessageScreen();
			messageScreen.showScreen();  // Abre a nova tela messageScreen
		});
		
		createLabel(1, 3, GridBagConstraints.WEST, "Association");
		JButton btnAssociation = createIcon(64, 64, 1, 4, GridBagConstraints.WEST, "/icons/association.png");
		btnAssociation.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir AssociationScreen
			AssociationScreen associationScreen = new AssociationScreen();
			associationScreen.showScreen();  // Abre a nova tela AssociationScreen
		});
		
		createLabel(0, 3, GridBagConstraints.WEST, "Update");
		JButton btnUpdate = createIcon(64, 64, 0, 4, GridBagConstraints.WEST, "/icons/update-icon.png");
		btnUpdate.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir AssociationScreen
			UpdateScreen updateScreen = new UpdateScreen();
			updateScreen.showScreen();  // Abre a nova tela AssociationScreen
		});
		
		createLogoCenter(0, 0, 3); //Cria a logo central
	}

}
