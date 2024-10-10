package com.usp.networks.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MessageScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField recipientField;
    private JTextArea messageField;
    private JButton btnSend;
    private JLabel messageToLabel;
    private JLabel messageLabel;
    private JButton btnExit;
	private int IdUserSend;
	private List<StringBuilder> list;

    public MessageScreen() {
        super("Messages");
        addComponents();
    }

    @Override
    protected void addComponents() {

    	createLogoCenter(0, 0, 3);

        btnExit = createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
        this.ActionListinerBtn(btnExit, new AdmScreen());
        add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));

        add(getMessageToLabel(), getConstraints(0, 1, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getRecipientField(), getConstraints(1, 1, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getMessageLabel(), getConstraints(0, 2, GridBagConstraints.WEST, 20, 20, 0, 0));

        add(getMessageField(), getConstraints(1, 2, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getBtnSend(), getConstraints(0, 3, GridBagConstraints.CENTER, 20, 20, 0, 0));
    }

    private GridBagConstraints getConstraints(int gridx, int gridy, int anchor, int top, int left, int bottom, int right) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        gbc.insets = new Insets(top, left, bottom, right);
        return gbc;
    }

    public JTextField getRecipientField() {
        if (recipientField == null) {
            recipientField = new JTextField(15);
            recipientField.setBorder(BorderFactory.createCompoundBorder(
            		BorderFactory.createLineBorder(Color.GRAY),
            		new EmptyBorder(4, 4, 4, 4)
            ));
        }
        return recipientField;
    }

    public JTextArea getMessageField() {
        if (messageField == null) {
            messageField = new JTextArea(3, 20);  // 3 linhas, 20 colunas
            messageField.setPreferredSize(new Dimension(250, 150)); 
            messageField.setLineWrap(true);  // quebra automática de linha onde a menssagem deve ser escrita
            messageField.setWrapStyleWord(true);  // quebra de linha por palavra
            messageField.setBorder(BorderFactory.createCompoundBorder(
            		BorderFactory.createLineBorder(Color.GRAY),
            		new EmptyBorder(4, 4, 4, 4)
            ));
            
        }
        return messageField;
    }

    public JButton getBtnSend() {
        if (btnSend == null) {
            btnSend = createIcon(25, 25, 1, 0, GridBagConstraints.EAST, "/icons/send-message.png");
            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String recipient = getRecipientField().getText().trim();
                    String message = getMessageField().getText().trim();

                    if (!recipient.isEmpty() && !message.isEmpty()) {
        				//achar o Id do usuário
                    	int idUser = getIdUser(recipient);
                    	if(idUser != -1) {
            				String msg = "\"SEND\";\""  + idUser + ";" + recipient + ";" + message + ":";
            				List<StringBuilder> listSend = sendMessage(msg);
            				
                    		if(listSend.get(0).toString().equals("\"Sent with sucess\"")) {
	                            // limpa as caixas de texto
	                            getRecipientField().setText("");
	                            getMessageField().setText("");
                    		}
                    	}
                    } 
                 }
            });
        }
        return btnSend;
    }
      

    public JLabel getMessageToLabel() {
        if (messageToLabel == null) {
            messageToLabel = new JLabel("Message to:");
        }
        return messageToLabel;
    }

    public JLabel getMessageLabel() {
        if (messageLabel == null) {
            messageLabel = new JLabel("Message:");
        }
        return messageLabel;
    }
    
    private int getIdUser(String user) {
    	IdUserSend = -1;
		String message = "LIST-USER:";
		list = sendMessage(message);
                
        for (int i = 1; i < list.size(); i++) {
        	StringBuilder UserContent = list.get(i);
        	String eachUser = new String(UserContent);
        	String[] splitUserContent = decodeUser(eachUser);
        	
            String id = splitUserContent[0]; // ID do usuário
            String email = splitUserContent[1];
            if(email.equals(user)) {
            	IdUserSend = Integer.parseInt(id);
            	break;
            }
    	
        }
        
        return IdUserSend;
        
    }
    
	private String[] decodeUser(String user) {
		String[] clean = user.split(user);
		return clean;
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageScreen frame = new MessageScreen();
            frame.showScreen();
        });
    }
}
