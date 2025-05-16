package com.usp.networks.screens;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;

public class MessageScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextArea messageField;
    private JButton btnSend;
	private String userSelect;
	private HashMap<String, Integer> EmailId;
	private int valorIdUser = -1; //Caso se não houve seleção do usuário

    public MessageScreen() {
        super("Messages");
        addComponents();
    }

    @Override
    protected void addComponents() { //Cria layout da página

    	createLogoCenter(0, 0, 3); //Cria a logo
    	
    	EmailId = new HashMap<String, Integer>();

        JButton btnExit = createIcon(25, 25, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png"); //Botão de voltar
        this.ActionListinerBtn(btnExit, new AdmScreen()); //Volta para a tela de Admin
        add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));

        JComboBox<String> userBox = createComboBox(1, 3, GridBagConstraints.CENTER, "Message to:");

        add(new JLabel("Message:"), getConstraints(0, 2, GridBagConstraints.WEST, 20, 20, 0, 0));

        add(getMessageField(), getConstraints(1, 2, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getBtnSend(), getConstraints(2, 3, GridBagConstraints.CENTER, 10, 10, 10, 10));
        
		String message = "\"LIST-USER\";";
		List<StringBuilder> list = sendMessage(message); //Pede a lista de usuários
                
        for (int i = 0; i < list.size(); i++) {
        	String UserContent = list.get(i).toString().replace("\"", "");
        	String[] splitUserContent = decode(UserContent);//Decodifica registro
        	
        	
            String id = splitUserContent[0]; //ID do usuário
            String email = splitUserContent[3]; //email
            String admin = splitUserContent[5]; //admin
        	
            // Adicionar ao JComboBox e armazenar o ID
            if(!Boolean.parseBoolean(admin)) {
            	userBox.addItem(email);
                EmailId.put(email, Integer.parseInt(id));
            }
            
        }

        userBox.addActionListener(e -> { //Seleciona usuário
            userSelect = (String) userBox.getSelectedItem();
            valorIdUser = EmailId.get(userSelect);
        });
    }


    private JTextArea getMessageField() {
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

    private JButton getBtnSend() {
        if (btnSend == null) {
            btnSend = createIcon(25, 25, 1, 0, GridBagConstraints.EAST, "/icons/send-message.png");
            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String message = getMessageField().getText().trim();

                    if (!message.isEmpty()) {
        				//achar o Id do usuário
                    	int idUser = valorIdUser;
                    	if(idUser != -1) {
            				String msg = "\"SEND\";\""  + userSelect + "\";\"" + Login.getLogin() + "\";\"" + message + "\":";
            				List<StringBuilder> listSend = sendMessage(msg); //Envia a mensagem
            				
                    		if(listSend.get(0).toString().equals("\"Sent with success\"")) {
	                            getMessageField().setText("");
                    		}
                    	}
                    } 
                 }
            });
        }
        return btnSend;
    }
      
    
}
