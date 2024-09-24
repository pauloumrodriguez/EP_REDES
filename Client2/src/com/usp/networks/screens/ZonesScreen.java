package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZonesScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField latitudeField;
    private JTextField longitudeField;
    private JTextField radiusField;
    private JPanel zonesListPanel;
    private JButton btnAdd;
    private JLabel addLatitudeLabel;
    private JLabel addLongitudeLabel;
    private JLabel addRadiusLabel;
    private JButton btnExit;

    private int zoneIdCounter = 1;  // contador de id para as zonas. isso é temporário (só para ver como fica a adição da zona à tabela)

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

        add(getAddLatitudeLabel(), getConstraints(0, 1, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getLatitudeField(), getConstraints(1, 1, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getAddLongitudeLabel(), getConstraints(0, 2, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getLongitudeField(), getConstraints(1, 2, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getAddRadiusLabel(), getConstraints(0, 3, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getRadiusField(), getConstraints(1, 3, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getBtnAdd(), getConstraints(1, 4, GridBagConstraints.CENTER, 20, 10, 0, 20));

        zonesListPanel = new JPanel();
        zonesListPanel.setLayout(new BoxLayout(zonesListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(zonesListPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        add(scrollPane, getConstraints(0, 5, GridBagConstraints.CENTER, 20, 0, 0, 0, 3));
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

    public JTextField getLatitudeField() {
        if (latitudeField == null) {
            latitudeField = new JTextField(15);
        }
        return latitudeField;
    }

    public JTextField getLongitudeField() {
        if (longitudeField == null) {
            longitudeField = new JTextField(15);
        }
        return longitudeField;
    }

    public JTextField getRadiusField() {
        if (radiusField == null) {
            radiusField = new JTextField(15);
        }
        return radiusField;
    }

    public JButton getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = createIcon(20, 20, 1, 0, GridBagConstraints.NORTHEAST, "/icons/add.png");
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String latitude = getLatitudeField().getText().trim();
                    String longitude = getLongitudeField().getText().trim();
                    String radius = getRadiusField().getText().trim();

                    if (!latitude.isEmpty() && !longitude.isEmpty() && !radius.isEmpty()) {
                        addZoneToList(zoneIdCounter++, latitude, longitude, radius);
                        clearFields();  // limpa as caixas de texto
                    }
                }
            });
        }
        return btnAdd;
    }

    public JLabel getAddLatitudeLabel() {
        if (addLatitudeLabel == null) {
            addLatitudeLabel = new JLabel("x(latitude):");
        }
        return addLatitudeLabel;
    }

    public JLabel getAddLongitudeLabel() {
        if (addLongitudeLabel == null) {
            addLongitudeLabel = new JLabel("y(longitude):");
        }
        return addLongitudeLabel;
    }

    public JLabel getAddRadiusLabel() {
        if (addRadiusLabel == null) {
            addRadiusLabel = new JLabel("raio:");
        }
        return addRadiusLabel;
    }

    // método para adicionar uma zona à lista com o ícone de lixeira
    private void addZoneToList(int zoneId, String latitude, String longitude, String radius) {
        JPanel zonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel zoneLabel = new JLabel("ID: " + zoneId + ", x: " + latitude + ", y: " + longitude + ", raio: " + radius);
        JButton btnDelete = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/trash-bin.png");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zonesListPanel.remove(zonePanel);
                zonesListPanel.revalidate();
                zonesListPanel.repaint();
            }
        });

        zonePanel.add(zoneLabel);
        zonePanel.add(btnDelete);

        zonesListPanel.add(zonePanel);
        zonesListPanel.revalidate();
        zonesListPanel.repaint();
    }

    //limpa todas as caixas de texto após adicionar uma zona
    private void clearFields() {
        getLatitudeField().setText("");
        getLongitudeField().setText("");
        getRadiusField().setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZonesScreen frame = new ZonesScreen();
            frame.showScreen();
        });
    }
}
