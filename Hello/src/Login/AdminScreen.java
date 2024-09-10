package Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminScreen extends JFrame {

    private static final long serialVersionUID = 1L;

    public AdminScreen(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 450); 
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes

        // Ícone de logout no canto superior esquerdo
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/icons/exit-icon.png"));
        Image exitImg = exitIcon.getImage();
        Image scaledExitImg = exitImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensiona para 20x20
        exitIcon = new ImageIcon(scaledExitImg);

        JButton exitButton = new JButton();
        exitButton.setIcon(exitIcon);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false); // Remove o fundo do botão
        exitButton.setBorderPainted(false); // Remove as bordas do botão
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão
        exitButton.addActionListener(e -> {
        	dispose();
        	JLogin loginFrame = new JLogin("Login");
        	loginFrame.setVisible(true);
        }); // Ação de deslogar

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Canto superior esquerdo
        gbc.weightx = 1.0; // Para expandir o espaço
        gbc.weighty = 0;
        contentPane.add(exitButton, gbc);

        // Ícone do sino no canto superior direito
        ImageIcon sinoIcon = new ImageIcon(getClass().getResource("/icons/sino-icon.png"));
        Image sinoImg = sinoIcon.getImage();
        Image scaledSinoImg = sinoImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensiona para 20x20
        sinoIcon = new ImageIcon(scaledSinoImg);

        JButton sinoButton = new JButton();
        sinoButton.setIcon(sinoIcon);
        sinoButton.setFocusPainted(false);
        sinoButton.setContentAreaFilled(false); // Remove o fundo do botão
        sinoButton.setBorderPainted(false); // Remove as bordas do botão
        sinoButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar sobre o botão
        sinoButton.addActionListener(e -> {
        	dispose();
        	Notifications notifyFrame = new Notifications("Notifications");
        	notifyFrame.setVisible(true);
        }); // Ação de deslogar

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Canto superior direito
        gbc.weightx = 1.0;
        contentPane.add(sinoButton, gbc);

        // Centralizando a logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png"));
        Image img = logoIcon.getImage();
        Image scaledImage = img.getScaledInstance(120, 80, Image.SCALE_SMOOTH); // Escala o ícone
        logoIcon = new ImageIcon(scaledImage);
        logoLabel.setIcon(logoIcon);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        gbc.fill = GridBagConstraints.NONE; // Não expande o componente
        contentPane.add(logoLabel, gbc);

        Dimension buttonSize = new Dimension(150, 40);
        
        // Três botões centralizados
        JButton btnZones = createButton("Zones", buttonSize);
        JButton btnUsers = createButton("Users", buttonSize);
        JButton btnMessages = createButton("Messages", buttonSize);

        gbc.gridwidth = 2; // Ocupa duas colunas para centralizar os botões
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(btnZones, gbc);

        gbc.gridy = 3;
        contentPane.add(btnUsers, gbc);

        gbc.gridy = 4;
        contentPane.add(btnMessages, gbc);

        // Ícone da janela
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png")); // Caminho do ícone
        setIconImage(appIcon.getImage());
    }

    // Método para criar botões com estilo consistente
    private JButton createButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(51, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMinimumSize(size); // Define o tamanho mínimo do botão
        button.setPreferredSize(size); // Define o tamanho preferido do botão
        button.setMaximumSize(size); // Define o tamanho máximo do botão
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminScreen frame = new AdminScreen("Admin");
            frame.setVisible(true);
        });
    }
}
