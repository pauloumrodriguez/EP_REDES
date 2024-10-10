package com.usp.networks.screens;

import java.awt.GridBagConstraints;


import javax.swing.*;

public class XYScreen extends Screen{
	
	private static final long serialVersionUID = 1L;
	private JTextField latitudeField;
	private JTextField longitudeField;
	private JButton btnSend;
	
	public XYScreen() {
		super("User");
		addComponents();
		
	}
	
	protected void addComponents() {
		JButton btnSino = createIcon(20, 20, 1, 0, GridBagConstraints.NORTHEAST, "/icons/sino-png.png");
		btnSino.addActionListener(e -> {
			this.dispose();
			NotificationScreen notificationScreen = new NotificationScreen();
			notificationScreen.showScreen();
		});
		
		JButton btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		this.ActionListinerBtn(btnExit, new Login());
		this.createLogoCenter(0,0, 2);
		latitudeField = this.createTextField(1, 1, GridBagConstraints.WEST, "Latitude:");
		longitudeField = this.createTextField(1, 2, GridBagConstraints.WEST, "Longitude:");
		
		btnSend = this.createButton(1, 3, GridBagConstraints.WEST, "Send Position");
		btnSend.addActionListener(e -> {
			if(!getLatitude().isEmpty() && !getLongitude().isEmpty()) {
				String msgXY = "XY;" + Login.getIdUser() + ";" + getLatitude() + ";" + getLongitude() + ":";
				sendMessage(msgXY);
				clearFields();
			}
		});
		
		
	}
	
	private void clearFields() {
       this.latitudeField.setText("");
       this.longitudeField.setText("");
    }
	
	public String getLatitude() {
		return latitudeField.getText().trim();
	}
	
	public String getLongitude() {
		return longitudeField.getText().trim();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new XYScreen(); 
            frame.showScreen();
        });
    }
}
