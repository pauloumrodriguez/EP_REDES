package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.List;
import javax.swing.*;

public class XYScreen extends Screen{
	
	private static final long serialVersionUID = 1L;
	private JTextField latitudeField;
	private JTextField longitudeField;
	
	public XYScreen() {
		super("User");
		addComponents();
		
	}
	
	protected void addComponents() { //Cria layout da página
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
		
		JButton btnSend = this.createButton(1, 3, GridBagConstraints.WEST, "Send Position");
		btnSend.addActionListener(e -> {
			if(!getLatitude().isEmpty() && !getLongitude().isEmpty()) { //Verifica se os campos não estão vazios
				String msgXY = "XY;" + Login.getLogin() + ";" + getLatitude() + ";" + getLongitude() + ":";
				List<StringBuilder> response = sendMessage(msgXY);//Envia as coordenadas atuais
				if(response.getFirst().toString().replace("\"", "").trim().equals("Successfully verified")) {
					JOptionPane.showMessageDialog(null, "Sent successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Unable to send", "Error", JOptionPane.ERROR_MESSAGE);
				}
				clearFields();
			}
			else {
				JOptionPane.showMessageDialog(null, "Fill in the fields correctly", "Error", JOptionPane.ERROR_MESSAGE);
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
	
}
