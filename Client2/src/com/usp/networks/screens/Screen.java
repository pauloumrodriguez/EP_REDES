package com.usp.networks.screens;

import java.awt.*;
import java.util.List;
import java.io.*;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.usp.networks.protocol.Protocol;

public abstract class Screen extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel mainScreen;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	
	protected Screen(String title){
		super(title);
		settingsWindow();
		addLogo();
	}
	
	private void  settingsWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 350);
		this.setLayout(new GridBagLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		mainScreen = new JPanel(new GridBagLayout());
        mainScreen.setBackground(new Color(245, 245, 245));
        mainScreen.setBorder(new EmptyBorder(80, 80, 80, 80));
        setContentPane(mainScreen);
	}
	
	public GridBagConstraints getGBC(int width, int top, int left, int bottom, int right) {
		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.insets = new Insets(top, left, bottom, right);
		  gbc.gridwidth = width;
		  return gbc;
	}
	
	private void addLogo() {
		ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png"));
        setIconImage(appIcon.getImage());
	}
	
	
	protected JLabel createLogoCenter(int x, int y, int width) {
        GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png"));
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
        field.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.GRAY),
        		new EmptyBorder(4, 4, 4, 4)
        ));
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
		password.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createLineBorder(Color.GRAY),
        		new EmptyBorder(4, 4, 4, 4)
        ));
		this.addPanel(password, gbc);
		return password;
	}
	
	protected JComboBox<String> createComboBox(int x, int y, int anchor, String title) {
		createLabel(0, y, GridBagConstraints.EAST, title);
		GridBagConstraints gbc = getGBC(1, 10, 10, 10, 10);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.anchor = anchor;
//		JComboBox<String> field = new JComboBox<>(userOptions);
		JComboBox<String> field = new JComboBox<>();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(170, 25)); 
        this.addPanel(field, gbc);
        return field;
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
	
	protected List<StringBuilder> sendMessage(String message) {
		try {
			client = new Socket("192.168.0.55", 12345);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			
			//mandar mensagem
			if(message != null && !message.isEmpty()) {
				out.println(message);
			}
			
			
			String response;
			while((response = in.readLine()) != null) {
				Protocol p = Protocol.getInstance();
				return p.execute(response);
			}
				
			
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Server not available", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	protected abstract void addComponents();
	
	protected void addPanel(Component c, GridBagConstraints gbc) {
		mainScreen.add(c, gbc);
	}
	
	public void showScreen() {
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	
	protected GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }
	
	 protected GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right, int gridwidth) {
	        GridBagConstraints gbc = getConstraints(gridx, gridy, anchor, top, left, bottom, right);
	        gbc.gridwidth = gridwidth;
	        return gbc;
	 }
	 
	 protected String[] decode(String str) {
			String[] clean = str.split(",");
			return clean;
	}
	
}
