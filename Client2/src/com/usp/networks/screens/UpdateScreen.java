package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UpdateScreen extends Screen{
	private static final long serialVersionUID = 1L;
	
    private JLabel updateLabel;
	
	public UpdateScreen() {
		super("Update Lobby");
		addComponents();
	}
	
	protected void addComponents() {
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});
		
		
        updateLabel = createLabel(1, 1, GridBagConstraints.NORTH, "Choose what you want to change");

		JButton btnUpdateUser = this.createButton(1, 2, GridBagConstraints.CENTER, "Update User");
		
		JButton btnUpdateZone = this.createButton(1, 4, GridBagConstraints.CENTER, "Update Zone");
		
		btnUpdateUser.addActionListener(e -> {
			this.dispose();
			UpdateUserScreen updateUser = new UpdateUserScreen();
			updateUser.showScreen();
		});
		
		btnUpdateZone.addActionListener(e -> {
			this.dispose();
			UpdateZoneScreen updateZone = new UpdateZoneScreen();
			updateZone.showScreen();
		});
	}
	
   
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new UpdateScreen(); 
            frame.showScreen();
        });
    }
}
