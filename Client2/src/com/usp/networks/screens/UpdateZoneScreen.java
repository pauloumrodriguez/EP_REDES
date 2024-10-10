package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UpdateZoneScreen extends Screen{
	private static final long serialVersionUID = 1L;
	
	private JTextField XField, YField, radiusField;
	
	public UpdateZoneScreen() {
		super("Update Zone");
		addComponents();
	}
	
	protected void addComponents() {
		createLogoCenter(0, 0, 3);
		JButton btnExit = this.createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		
		btnExit.addActionListener(e -> {
			this.dispose();
			AdmScreen admScreen = new AdmScreen();
			admScreen.showScreen();
		});

		
		XField = this.createTextField(1, 1, GridBagConstraints.WEST, "X:");
		YField = this.createTextField(1, 2, GridBagConstraints.WEST, "Y:");
		radiusField = this.createTextField(1, 3, GridBagConstraints.WEST, "Radius:");
		JButton btnUpdate = this.createButton(1, 4, GridBagConstraints.CENTER, "Update Zone");
		
		btnUpdate.addActionListener(e -> {
			if(!getXField().isEmpty() && !getYField().isEmpty() && !getRadiusField().isEmpty()) {
					String message = "\"UPDATE-ZONE\";\"" + getXField() + "\";\"" + getYField() + "\";\"" + getRadiusField() + "\":";
					List<StringBuilder> list = sendMessage(message);
					if(!list.get(0).toString().equals("\"Zone updated with sucess\"")) {
						showErrorPopup("Não foi possível atualizar a zone!");
						clearFields();
					}
					else {
						this.dispose();
						AdmScreen admScreen = new AdmScreen();
						admScreen.showScreen();
					}
				} else {
					clearFields();
				}
				
		});
	}
	
	public String getXField() {
		return XField.getText().trim();
	}
	
	public String getYField() {
		return YField.getText().trim();
	}
	
	public String getRadiusField() {
		return radiusField.getText().trim();
	}
	
    private void clearFields() {
    	XField.setText("");
    	YField.setText("");
    	radiusField.setText("");
    }
	
    private static void showErrorPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Screen frame = new UpdateZoneScreen(); 
            frame.showScreen();
        });
    }
}
