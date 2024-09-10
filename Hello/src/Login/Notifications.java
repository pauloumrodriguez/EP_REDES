package Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Notifications extends JFrame {

    private static final long serialVersionUID = 1L;
    private JList<String> notificationList;

    public Notifications(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 350); 
        setResizable(false);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        // Título
        JLabel titleLabel = new JLabel("Notifications", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Lista de notificações
        DefaultListModel<String> notificationModel = new DefaultListModel<>();
        // Adicionando algumas notificações de exemplo
        notificationModel.addElement("Notificação 1: Você tem uma nova mensagem.");
        notificationModel.addElement("Notificação 2: Sua senha foi alterada.");
        notificationModel.addElement("Notificação 3: Nova atualização disponível.");
        notificationModel.addElement("Notificação 4: Lembrete de reunião às 14h.");
        
        notificationList = new JList<>(notificationModel);
        notificationList.setFont(new Font("Arial", Font.PLAIN, 14));
        notificationList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        JScrollPane scrollPane = new JScrollPane(notificationList);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Botão "Fechar"
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(51, 153, 255));
        closeButton.setForeground(Color.WHITE);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> {
        	dispose();
        	CoordScreen coordFrame = new CoordScreen("User");
        	coordFrame.setVisible(true);
        }); // Fecha a janela quando clicado

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPane.add(closeButton);

        contentPane.add(buttonPane, BorderLayout.SOUTH);

        ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png")); // Ícone do aplicativo
        setIconImage(appIcon.getImage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Notifications frame = new Notifications("Notifications");
            frame.setVisible(true);
        });
    }
}
