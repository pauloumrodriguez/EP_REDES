package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZonesScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField latitudeField;
    private JTextField longitudeField;
    private JTextField radiusField;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnExit;
    private String selectedItem;
    
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public ZonesScreen() {
        super("Zones");
        addComponents();
    }

    @Override
    protected void addComponents() {
     
          createLogoCenter(0, 0, 2);

          btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
          this.ActionListinerBtn(btnExit, new AdmScreen());
          add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));
            
          listModel = new DefaultListModel<>();
          list = new JList<>(listModel);
            
          updateListZone();
            
         // Captura o item selecionado
    	  list.addListSelectionListener(e -> {
    	      if (!e.getValueIsAdjusting()) {
    	          selectedItem = list.getSelectedValue();
    	      }
    	  });
            
          btnAdd = createIcon(20, 20, 1, 3, GridBagConstraints.EAST, "/icons/add.png");
          btnAdd.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if (!getLatitude().isEmpty() && !getLongitude().isEmpty() && !getRadius().isEmpty()) {
                	  CreateZone(getLatitude(), getLongitude(), getRadius());  // adiciona o nome do usuário à lista
                      clearFields();
                   }
                }
            });
            
            btnDelete = createIcon(40, 40, 4, 2, GridBagConstraints.EAST, "/icons/trash-bin.png");
            
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
                		List<StringBuilder> listMSG = sendMessage(msg);
                		
                		if(listMSG.get(0).toString().equals("\"Zone deleted with sucess\"")) {
                            listModel.remove(selectedIndex);
                		}
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum item selecionado para remover.", "Erro", JOptionPane.WARNING_MESSAGE);
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

    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right, int gridwidth) {
        GridBagConstraints gbc = getConstraints(gridx, gridy, anchor, top, left, bottom, right);
        gbc.gridwidth = gridwidth;
        return gbc;
    }


    private void CreateZone(String latitude, String longitude, String radius) {
    	String msgCreate = "\"CREATE-ZONE\";\"" + latitude + "\";\"" + longitude + "\";\"" + radius + "\":";
		List<StringBuilder> list = sendMessage(msgCreate);
		if(list.get(0).toString().equals("\"Zone created with sucess\"")) {
			updateListZone();
		}
    }
    
    private void updateListZone() {
    	listModel.clear();
    	String msgList = "\"LIST-ZONE\";";
		List<StringBuilder>listResponse = sendMessage(msgList);
      
        for (int i = 1; i < listResponse.size(); i++) {
        	String ZoneContent = listResponse.get(i).toString().replace("\"", "");
        	String[] splitZoneContent = decodeZone(ZoneContent);
        	
            String id = splitZoneContent[0];
            String x = splitZoneContent[1];
            String y = splitZoneContent[2];
            String radius = splitZoneContent[3];

//            idZone.put(email, Integer.parseInt(id));	 
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
    
	private String[] decodeZone(String zone) {
		String[] clean = zone.split(",");
		return clean;
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZonesScreen frame = new ZonesScreen();
            frame.showScreen();
        });
    }
}
