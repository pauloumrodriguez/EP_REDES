package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ZonesScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField latitudeField;
    private JTextField longitudeField;
    private JTextField radiusField;
    private String selectedItem;
    private DefaultListModel<String> listModel;

    public ZonesScreen() {
        super("Zones");
        addComponents();
    }

    @Override
    protected void addComponents() {//Cria o layout da página
     
          createLogoCenter(0, 0, 2);

          JButton btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
          this.ActionListinerBtn(btnExit, new AdmScreen());
          add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));
            
          listModel = new DefaultListModel<>();
          JList<String> list = new JList<>(listModel);
            
          updateListZone();
            
         // Captura o item selecionado
    	  list.addListSelectionListener(e -> {
    	      if (!e.getValueIsAdjusting()) {
    	          selectedItem = list.getSelectedValue();
    	      }
    	  });
            
          JButton btnAdd = createIcon(20, 20, 1, 3, GridBagConstraints.EAST, "/icons/add.png");
          btnAdd.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if (!getLatitude().isEmpty() && !getLongitude().isEmpty() && !getRadius().isEmpty()) { //Verifica se os campos não estão vazios
                	  CreateZone(getLatitude(), getLongitude(), getRadius());  // adiciona o nome do usuário à lista
                      clearFields();
                   }
                }
            });
            
            JButton btnDelete = createIcon(40, 40, 4, 2, GridBagConstraints.EAST, "/icons/trash-bin.png");
            
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Verifica se algum item está selecionado
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Remove o item do modelo (isso atualiza o JList automaticamente)
                		String[] rs = selectedItem.split("\\|");
                		for (int i = 0; i < rs.length; i++) {
                			rs[i] = rs[i].trim();
                		}
                		String msg = "\"DELETE-ZONE\";\"" + rs[0] + "\":";
                		List<StringBuilder> listMSG = sendMessage(msg);//Pede para apagar zona
                		
                		if(listMSG.get(0).toString().equals("\"Zone deleted with success\"")) {
                            listModel.remove(selectedIndex);
                		}
                		else {
                			JOptionPane.showMessageDialog(null, "Delete the association first", "Error", JOptionPane.ERROR_MESSAGE);
                		}
                    } else {
                        JOptionPane.showMessageDialog(null, "No items selected to remove", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            
          latitudeField = createTextField(1, 1, GridBagConstraints.WEST, "X:");
          longitudeField = createTextField(1, 2, GridBagConstraints.WEST, "Y:");
          radiusField = createTextField(1, 3, GridBagConstraints.WEST, "radius:");

            
          list.setPreferredSize(new Dimension(350, 110));
          JScrollPane scrollPane = new JScrollPane(list);
          scrollPane.setPreferredSize(new Dimension(400, 200));  // Tamanho da área de exibição
          add(scrollPane, getConstraints(0, 6, GridBagConstraints.CENTER, 20, 0, 0, 0, 3));  // Adiciona o painel
    }
    
    public String getLatitude() {
        return latitudeField.getText().trim();
    }
    public String getLongitude() {
        return longitudeField.getText().trim();
    }
    public String getRadius() {
        return radiusField.getText().trim();
    }

    private void CreateZone(String latitude, String longitude, String radius) {
    	String msgCreate = "\"CREATE-ZONE\";\"" + latitude + "\";\"" + longitude + "\";\"" + radius + "\":";
		List<StringBuilder> list = sendMessage(msgCreate);//Pede para criar zona
		if(list.get(0).toString().equals("\"Zone created with success\"")) {
			updateListZone();
		}
    }
    
    private void updateListZone() {
    	listModel.clear();
    	String msgList = "\"LIST-ZONE\";";
		List<StringBuilder>listResponse = sendMessage(msgList);//pede lista
      
        for (int i = 0; i < listResponse.size(); i++) {
        	String ZoneContent = listResponse.get(i).toString().replace("\"", "");
        	String[] splitZoneContent = decode(ZoneContent);//decodifica o registro
        	
            String id = splitZoneContent[0]; //id
            String x = splitZoneContent[1]; //x
            String y = splitZoneContent[2]; //y
            String radius = splitZoneContent[3]; //radius
            
            listModel.addElement(id + " | " + "X: " + x + " | " + "Y: " + 
            y + " | " + "Radius: " + radius);
        }
    }

    //limpa todas as caixas de texto após adicionar uma zona
    private void clearFields() {
        latitudeField.setText("");
        longitudeField.setText("");
        radiusField.setText("");
    }
    

}
