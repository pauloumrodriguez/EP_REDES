package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;

public class AdmScreen extends Screen {
	private static final long serialVersionUID = 1L;

	private JButton btnExit;
	private JButton btnSino;
	private JButton btnUser;
	private JButton btnZones;
	private JButton btnMessages;
	private JButton btnAssociation;
	private JButton btnUpdate;

	private JLabel usersLabel;
	private JLabel zonesLabel;
	private JLabel messagesLabel;
	private JLabel associationLabel;
	private JLabel updateLabel;
	
	public AdmScreen() {
		super("Admin");
		addComponents();
	}

	@Override
	protected void addComponents() {

		btnExit = createIcon(28, 28, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		this.ActionListinerBtn(btnExit, new Login());

		btnSino = createIcon(25, 25, 2, 0, GridBagConstraints.NORTHEAST, "/icons/sino-png.png");
		btnSino.addActionListener(e -> {
			this.dispose();
			NotificationScreen notificationScreen = new NotificationScreen();
			notificationScreen.showScreen();
		});
		
		usersLabel = createLabel(0, 1, GridBagConstraints.WEST, "Users");
		btnUser = createIcon(64, 64, 0, 2, GridBagConstraints.WEST, "/icons/user-128.png");
		btnUser.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir UsersScreen
			UsersScreen usersScreen = new UsersScreen();
			usersScreen.showScreen();  // Abre a nova tela UsersScreen
		});

		zonesLabel = createLabel(1, 1, GridBagConstraints.CENTER, "Zones");
		btnZones = createIcon(64, 64, 1, 2, GridBagConstraints.CENTER, "/icons/zones.png");
		btnZones.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir ZonesScreen
			ZonesScreen zonesScreen = new ZonesScreen();
			zonesScreen.showScreen();  // Abre a nova tela ZonesScreen
		});
		
		messagesLabel = createLabel(2, 1, GridBagConstraints.EAST, "Messages");
		btnMessages = createIcon(64, 64, 2, 2, GridBagConstraints.EAST, "/icons/messages.png");
		btnMessages.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir messageScreen
			MessageScreen messageScreen = new MessageScreen();
			messageScreen.showScreen();  // Abre a nova tela messageScreen
		});
		
		associationLabel = createLabel(1, 3, GridBagConstraints.WEST, "Association");
		btnAssociation = createIcon(64, 64, 1, 4, GridBagConstraints.WEST, "/icons/association.png");
		btnAssociation.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir AssociationScreen
			AssociationScreen associationScreen = new AssociationScreen();
			associationScreen.showScreen();  // Abre a nova tela AssociationScreen
		});
		
		updateLabel = createLabel(0, 3, GridBagConstraints.WEST, "Update");
		btnUpdate = createIcon(64, 64, 0, 4, GridBagConstraints.WEST, "/icons/update-icon.png");
		btnUpdate.addActionListener(e -> {
			this.dispose(); // Fecha a tela atual antes de abrir AssociationScreen
			UpdateScreen updateScreen = new UpdateScreen();
			updateScreen.showScreen();  // Abre a nova tela AssociationScreen
		});
		
		createLogoCenter(0, 0, 3);
	}

	
	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnSino() {
		return btnSino;
	}

	public JButton getBtnUser() {
		return btnUser;
	}

	public JButton getBtnZones() {
		return btnZones;
	}

	public JButton getBtnMessages() {
		return btnMessages;
	}
	
	public JButton getBtnAssociation() {
		return btnAssociation;
	}

	public JLabel getUsersLabel() {
		return usersLabel;
	}

	public JLabel getZonesLabel() {
		return zonesLabel;
	}

	public JLabel getMessagesLabel() {
		return messagesLabel;
	}
	
	public JLabel getAssociationLabel() {
		return associationLabel;
	}
	
	public JLabel getUpdateLabel() {
		return updateLabel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AdmScreen frame = new AdmScreen();
			frame.showScreen();
		});
	}
}
