package com.usp.networks.screens;

import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AssociationScreen extends Screen{
	private static final long serialVersionUID = 1L;

	
	private String userSelect; //Valor do campo selecionado
	private String zoneSelect;
	private HashMap<String, Integer> EmailId; //Mapeia os emails com os id do usuários
	private int valorIdUser;


	public AssociationScreen() {
		super("Association");
		addComponents();
	}

	@Override
	protected void addComponents() {//Cria layout da página
		EmailId = new HashMap<>(); 
		
		createLogoCenter(0, 0, 3); //Cria a logo
		
		JButton btnExit = createIcon(28, 28, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png"); //Botão de sair
		this.ActionListinerBtn(btnExit, new AdmScreen()); //Volta para página anterior

        JComboBox<String> userBox = createComboBox(1, 3, GridBagConstraints.CENTER, "User ID"); //Caixa para selecionar usuário
        
		String message = "\"LIST-USER\";";
		List<StringBuilder> list = sendMessage(message); //Requisita a lista de usuários
                
        for (int i = 0; i < list.size(); i++) {
        	String UserContent = list.get(i).toString().replace("\"", "");
        	String[] splitUserContent = decode(UserContent); //Decodifica o registro
        	
        	
        	
            String id = splitUserContent[0]; //ID do usuário
            String email = splitUserContent[3]; //email
            String admin = splitUserContent[5]; //admin
            
         // Adicionar ao JComboBox e armazenar o ID
            if(!Boolean.parseBoolean(admin)) {
            	userBox.addItem(email);
                EmailId.put(email, Integer.parseInt(id));
            }
        	
        }
        

        userBox.addActionListener(e -> { //Seleciona o usuário
            userSelect = (String) userBox.getSelectedItem();
            valorIdUser = EmailId.get(userSelect);
        });
		
        
        JComboBox<String> zoneBox = createComboBox(1, 4, GridBagConstraints.CENTER, "Zone ID");

		String message2 = "\"LIST-ZONE\";";
		list = sendMessage(message2); //Requisita a lista de zonas
                
        for (int i = 0; i < list.size(); i++) {
        	String ZoneContent = list.get(i).toString().replace("\"", "");
        	String[] splitZoneContent = decode(ZoneContent); //Decodifica o registro
        	
            String id = splitZoneContent[0]; // ID da zona

            // Adicionar ao JComboBox e armazenar o ID
            zoneBox.addItem(id);
        }
        
        zoneBox.addActionListener(e -> { //Seleciona a zona
            zoneSelect = (String) zoneBox.getSelectedItem();
        });
	
		
		JButton btnAssociate = createButton(1, 6, GridBagConstraints.CENTER, "Associate"); //Botão de Associar	
		btnAssociate.addActionListener(e -> {
			if(zoneSelect != null  && userSelect != null) {
				String msg = "\"CREATE-ASS\";\"" + valorIdUser + "\";\"" + zoneSelect + "\":";
				List<StringBuilder> listAss= sendMessage(msg);
				
				if(listAss.get(0).toString().equals("\"Association Zone-User created with success\"")) { 
					//Criou associação com sucesso
					JOptionPane.showMessageDialog(null, "Association Zone-User created with succcess", "OK", JOptionPane.INFORMATION_MESSAGE);
        		}
				else {
					JOptionPane.showMessageDialog(null, "Unable to register zone-user", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnDesassociate = createButton(1, 5, GridBagConstraints.CENTER, "Desassociate"); //Botão de Dessassociar		
		btnDesassociate.addActionListener(e -> {
			if(zoneSelect != null  && userSelect != null) {
				String msg = "\"DELETE-ASS\";\"" + valorIdUser + "\";\"" + zoneSelect + "\":";
				List<StringBuilder> listAss= sendMessage(msg);
				
				if(listAss.get(0).toString().equals("\"Association Zone-User deleted with success\"")) {
					//Excluiu associação com sucesso
					JOptionPane.showMessageDialog(null, "Association Zone-User deleted with succcess", "OK", JOptionPane.INFORMATION_MESSAGE);
        		}else {
					JOptionPane.showMessageDialog(null, "Unable to delete zone-user", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
}
