package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

public class AssociationScreen extends Screen{
	private static final long serialVersionUID = 1L;

	private JButton btnExit;
	private JButton btnSino;
	private JButton btnAssociate;
	private JButton btnDesassociate;
	
	private JComboBox<String> userBox;
	private JComboBox<String> zoneBox;
	
	private String userSelect;
	private String zoneSelect;
	private List<StringBuilder> list;
	private List<StringBuilder> list2;
	private HashMap<String, Integer> EmailId; 
	
	private int valorIdUser;


	public AssociationScreen() {
		super("Association");
		addComponents();
	}

	@Override
	protected void addComponents() {
		EmailId = new HashMap<>(); 
		
		createLogoCenter(0, 0, 3);
		
		btnExit = createIcon(28, 28, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
		this.ActionListinerBtn(btnExit, new AdmScreen());

		btnSino = createIcon(25, 25, 2, 0, GridBagConstraints.NORTHEAST, "/icons/sino-png.png");
		btnSino.addActionListener(e -> {
			this.dispose();
			NotificationScreen notificationScreen = new NotificationScreen();
			notificationScreen.showScreen();
		});
		
		
//		String[] opcoes = {"User 10", "User 11", "User 12", "User 13"};
        userBox = createComboBox(1, 3, GridBagConstraints.CENTER, "User ID");
        
		String message = "\"LIST-USER\";";
		list = sendMessage(message);
                
        for (int i = 1; i < list.size(); i++) {
        	String UserContent = list.get(i).toString().replace("\"", "");
        	String[] splitUserContent = decodeUser(UserContent);
        	
        	
            String id = splitUserContent[0]; // ID do usuário
            String email = splitUserContent[3]; // email
        	
            // Adicionar ao JComboBox e armazenar o ID
            userBox.addItem(email);
            EmailId.put(email, Integer.parseInt(id));
            
        }

        userBox.addActionListener(e -> {
            userSelect = (String) userBox.getSelectedItem();
            valorIdUser = EmailId.get(userSelect);
        });
		
        
//        String[] opcoes2 = {"Zona 1", "Zona 2", "Zona 3", "Zona 4"};
        zoneBox = createComboBox(1, 4, GridBagConstraints.CENTER, "Zone ID");

		String message2 = "\"LIST-ZONE\";";
		list2 = sendMessage(message2);
                
        for (int i = 1; i < list2.size(); i++) {
        	String ZoneContent = list2.get(i).toString().replace("\"", "");
        	String[] splitZoneContent = decodeUser(ZoneContent);
        	
            String id = splitZoneContent[0]; // ID da zona

            // Adicionar ao JComboBox e armazenar o ID
            zoneBox.addItem(id);
        }
        
        zoneBox.addActionListener(e -> {
            zoneSelect = (String) zoneBox.getSelectedItem();
        });
	
		
		btnAssociate = createButton(1, 6, GridBagConstraints.CENTER, "Desassociate");		
		btnAssociate.addActionListener(e -> {
			if(zoneSelect != null  && userSelect != null) {
				String msg = "\"CREATE-ASS\";\"" + valorIdUser + "\";\"" + zoneSelect + "\":";
				List<StringBuilder> listAss= sendMessage(msg);
				
				if(listAss.get(0).toString().equals("\"Association Zone-User created with success\"")) {
					this.dispose(); 
					AdmScreen admScreen = new AdmScreen();
					admScreen.showScreen();
        		}
			}
		});
		
		btnDesassociate = createButton(1, 5, GridBagConstraints.CENTER, "Associate");		
		btnDesassociate.addActionListener(e -> {
			if(zoneSelect != null  && userSelect != null) {
				String msg = "\"DELETE-ASS\";\"" + valorIdUser + "\";\"" + zoneSelect + "\":";
				List<StringBuilder> listAss= sendMessage(msg);
				
				if(listAss.get(0).toString().equals("\"Association Zone-User deleted with success\"")) {
					this.dispose(); 
					AdmScreen admScreen = new AdmScreen();
					admScreen.showScreen();
        		}
			}
		});
	}
	
	private String[] decodeUser(String user) {
		String[] clean = user.split(",");
		return clean;
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AssociationScreen frame = new AssociationScreen();
			frame.showScreen();
		});
	}
}
