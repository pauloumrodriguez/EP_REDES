package com.usp.networks.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageScreen extends Screen {
    private static final long serialVersionUID = 1L;

    private JTextField recipientField;
    private JTextArea messageField;
    private JButton btnSend;
    private JLabel messageToLabel;
    private JLabel messageLabel;
    private JButton btnExit;

    public MessageScreen() {
        super("Messages");
        addComponents();
    }

    @Override
    protected void addComponents() {

    	createLogoCenter(0, 0, 2);

        btnExit = createIcon(20, 20, 0, 0, GridBagConstraints.WEST, "/icons/seta-left-icon.png");
        this.ActionListinerBtn(btnExit, new AdmScreen());
        add(btnExit, getConstraints(0, 0, GridBagConstraints.WEST, 0, 0, 0, 0));

        add(getMessageToLabel(), getConstraints(0, 1, GridBagConstraints.WEST, 20, 20, 0, 0));
        add(getRecipientField(), getConstraints(1, 1, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getMessageLabel(), getConstraints(0, 2, GridBagConstraints.WEST, 20, 20, 0, 0));

        add(getMessageField(), getConstraints(1, 2, GridBagConstraints.CENTER, 20, 10, 0, 0));

        add(getBtnSend(), getConstraints(0, 3, GridBagConstraints.WEST, 20, 20, 0, 0));
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
        }
        return recipientField;
    }

    public JTextArea getMessageField() {
        if (messageField == null) {
            messageField = new JTextArea(3, 20);  // 3 linhas, 20 colunas
            messageField.setLineWrap(true);  // quebra automática de linha onde a menssagem deve ser escrita
            messageField.setWrapStyleWord(true);  // quebra de linha por palavra
            messageField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        return messageField;
    }

    public JButton getBtnSend() {
        if (btnSend == null) {
            btnSend = createIcon(20, 20, 1, 0, GridBagConstraints.NORTHEAST, "/icons/send-message.png");
            btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String recipient = getRecipientField().getText().trim();
                    String message = getMessageField().getText().trim();

                    if (!recipient.isEmpty() && !message.isEmpty()) {
                        // falta implementar isso aqui para enviar a mensagem
                    	// por enquanto ele imprime o que está abaixo no terminal, só para ver como funciona
                        System.out.println("Mensagem enviada para: " + recipient);
                        System.out.println("Conteúdo: " + message);

                        // limpa as caixas de texto
                        getRecipientField().setText("");
                        getMessageField().setText("");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageScreen frame = new MessageScreen();
            frame.showScreen();
        });
    }
}
