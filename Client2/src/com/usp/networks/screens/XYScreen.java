package com.usp.networks.screens;
import java.awt.GridBagConstraints;
import javax.swing.*;

public class XYScreen extends Screen{
	
	private static final long serialVersionUID = 1L;
	private JTextField latitudeField;
	private JTextField longitudeField;
	
	public XYScreen() {
		super("User");
		addComponents();
		
	}
	
	protected void addComponents() {
		JButton btnSino = createIcon(20, 20, 1, 0, GridBagConstraints.NORTHEAST, "/icons/sino-icon.png");
		this.ActionListinerBtn(btnSino, null);//Notify
		JButton btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/exit-icon.png");
		this.ActionListinerBtn(btnExit, new Login());
		this.createLogoCenter(0,0, 2);
		latitudeField = this.createTextField(1, 1, GridBagConstraints.WEST, "Latitude:");
		longitudeField = this.createTextField(1, 2, GridBagConstraints.WEST, "Longitude:");
		this.createButton(1, 3, GridBagConstraints.WEST, "Submmit");
	}
	
	public String getLatitude() {
		return latitudeField.getText();
	}
	
	public String getLongitude() {
		return longitudeField.getText();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new XYScreen(); 
            frame.showScreen();
        });
    }
}
