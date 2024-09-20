package com.usp.networks.screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class Screen extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel mainScreen;
	
	protected Screen(String title){
		super(title);
		settingsWindow();
		addLogo();
	}
	
	private void  settingsWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 350);
		this.setLayout(new GridBagLayout());
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		
		mainScreen = new JPanel(new GridBagLayout());
        mainScreen.setBackground(new Color(245, 245, 245));
        mainScreen.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(mainScreen);
	}
	
	public GridBagConstraints getGBC(int width, int top, int left, int bottom, int right) {
		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.insets = new Insets(top, left, bottom, right);
		  gbc.gridwidth = width;
		  return gbc;
	}
	
	private void addLogo() {
		ImageIcon appIcon = new ImageIcon(getClass().getResource("src/icons/eagle-logo.png"));
        setIconImage(appIcon.getImage());
	}
	
	
	protected JLabel createLogoCenter(int x, int y, int width) {
        GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("Client/src/icons/eagle-logo.png"));
        Image img = logoIcon.getImage();
        Image scaledImage = img.getScaledInstance(120, 80, Image.SCALE_SMOOTH); 
        logoIcon = new ImageIcon(scaledImage);
        logoLabel.setIcon(logoIcon);

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        this.addPanel(logoLabel, gbc);
        return logoLabel;
	}
	
	protected JButton createIcon(int width, int height, int x, int y, int anchor, String fileIcon) {
	    ImageIcon icon = new ImageIcon(getClass().getResource(fileIcon));
        Image iconImg = icon.getImage();
        Image scaledIconImg = iconImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledIconImg);
        
        JButton iconButton = new JButton();
        iconButton.setIcon(icon);
        iconButton.setFocusPainted(false);
        iconButton.setContentAreaFilled(false); 
        iconButton.setBorderPainted(false); 
        iconButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10); 
        
        gbc.gridx = x;
        gbc.gridy = y; 
        gbc.anchor = anchor; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.weightx = 1.0; 
        gbc.weighty = 0.0; 
        this.addPanel(iconButton, gbc);
        return iconButton;
       
  }

	protected void ActionListinerBtn(JButton btn, Screen frame) {
		btn.addActionListener(e -> {
			dispose();
			frame.showScreen();
		});
	}
	
	protected void MouseListiner(JLabel label, Screen frame) {
		label.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				dispose();
				frame.showScreen();
			}
			
			public void mouseEntered(java.awt.event.MouseEvent e) {
				label.setForeground(Color.BLUE);
			}
			
			public void mouseExited(java.awt.event.MouseEvent e) {
				label.setForeground(Color.BLUE.darker());
			}
		});
	}
	
	protected JTextField createTextField(int x, int y, int anchor, String title) {
		createLabel(0, y, GridBagConstraints.EAST, title);
		GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
		JTextField field = new JTextField(15);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        this.addPanel(field, gbc);
        return field;
	}
	
	protected JPasswordField createPasswordField(int x, int y, int anchor, String title) {
		createLabel(0, y, GridBagConstraints.EAST, title);
		GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
		JPasswordField password = new JPasswordField(15);
		password.setFont(new Font("Arial", Font.PLAIN, 14));
		password.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
		this.addPanel(password, gbc);
		return password;
	}
	
	protected JLabel createLabel(int x, int y, int anchor, String title) {
		GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
		JLabel label = new JLabel(title);
		this.addPanel(label, gbc);
		return label;
	}
	
	protected JLabel createLink(int x, int y, int anchor, String title) {
		JLabel label = createLabel(x, y, anchor, title);
		label.setForeground(Color.blue.darker());
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return label;
	}
	
	protected JButton createButton(int x, int y, int anchor, String title) {
		GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		JButton btn = new JButton(title);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(51, 153, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = anchor;
        this.addPanel(btn, gbc);
        return btn;
	}
	
	protected abstract void addComponents();
	
	protected void addPanel(Component c, GridBagConstraints gbc) {
		mainScreen.add(c, gbc);
	}
	
	public void showScreen() {
		setVisible(true);
	}
	
}
