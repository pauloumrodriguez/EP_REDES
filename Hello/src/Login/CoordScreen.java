package Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CoordScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField textLatitude;
	private JTextField textLongitude;
	
	public CoordScreen(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 350); 
        setResizable(false);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(245, 245, 245)); // Cor de fundo suave
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes

        ImageIcon sinoIcon = new ImageIcon(getClass().getResource("/icons/sino-icon.png"));
        Image sinoImg = sinoIcon.getImage();
        Image scaledSinoImg = sinoImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensiona para 30x30
        sinoIcon = new ImageIcon(scaledSinoImg);
        
        JButton sinoButton = new JButton();
        sinoButton.setIcon(sinoIcon);
        sinoButton.setFocusPainted(false);
        sinoButton.setContentAreaFilled(false); // Remove o fundo do botão
        sinoButton.setBorderPainted(false); // Remove as bordas do botão
        sinoButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mão ao passar sobre o botão

        gbc.gridx = 1; // Última coluna
        gbc.gridy = 0; // Primeira linha
        gbc.anchor = GridBagConstraints.NORTHEAST; // Alinha no canto superior direito
        gbc.fill = GridBagConstraints.NONE; // Não esticar o botão
        gbc.weightx = 1.0; // Garante que o layout se expanda horizontalmente
        gbc.weighty = 0.0; // Sem expansão vertical nesse caso
        contentPane.add(sinoButton, gbc);
        sinoButton.addActionListener(e -> {
            dispose(); // Fecha a tela de "Esqueci senha"
            Notifications notifyFrame = new Notifications("Notifications");
            notifyFrame.setVisible(true); // Reabre a tela de login
        });
        // Centralizando a logo
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png"));
        Image img = logoIcon.getImage();
        Image scaledImage = img.getScaledInstance(120, 80, Image.SCALE_SMOOTH); // Escala o ícone
        logoIcon = new ImageIcon(scaledImage);
        logoLabel.setIcon(logoIcon);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        gbc.fill = GridBagConstraints.NONE; // Não expande o componente
        contentPane.add(logoLabel, gbc);
        
        gbc.gridwidth = 1; // Define uma coluna
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinha à direita
        contentPane.add(new JLabel("Latitude:"), gbc);

        textLatitude = new JTextField(15);
        textLatitude.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte personalizada
        textLatitude.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borda leve

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        contentPane.add(textLatitude, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Alinha à direita
        contentPane.add(new JLabel("Longitude:"), gbc);

        textLongitude = new JTextField(15);
        textLongitude.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte personalizada
        textLongitude.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // Borda leve

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; // Alinha à esquerda
        contentPane.add(textLongitude, gbc);
        
        JButton btnSubmmit = new JButton("Submmit");
        btnSubmmit.setFocusPainted(false);
        btnSubmmit.setBackground(new Color(51, 153, 255));
        btnSubmmit.setForeground(Color.WHITE);
        btnSubmmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmmit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(btnSubmmit, gbc);
        
        ImageIcon appIcon = new ImageIcon(getClass().getResource("/icons/eagle-logo.png")); // Caminho do ícone
        setIconImage(appIcon.getImage());
        
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoordScreen frame = new CoordScreen("User");
            frame.setVisible(true);
        });
    }
}
