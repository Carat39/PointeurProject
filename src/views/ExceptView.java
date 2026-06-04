package views;

import views.utils.Style;
import javax.swing.*;
import java.awt.*;


/**
 * @author Romain
 * Permet de gerer l'interface d'exception
 * lors du pointage si un utilisateur
 * a deja pointe 2 fois dans une meme journee
 */
public class ExceptView {
    private static JFrame frame;
    private JButton buttonMenu;

    /**
     * Fonction principale permettant
     * d'appeler la methode buildant la page d'exception
     * @param employeeName
     */
    public ExceptView(String employeeName) {
        buildUI(employeeName);
    }

    /**
     * Fonction initalisant tous les aspects
     * graphiques
     * @param employeeName
     */
    private void buildUI(String employeeName) {
        //Frame
        frame = new JFrame("Pointeuse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 425);
        frame.setLocationRelativeTo(null);

        //Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Style.pastelPeach);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Panel pour le haut
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Style.pastelPeach);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Label text
        JLabel labelError = new JLabel("Attention ");
        labelError.setFont(new Font("Serif", Font.BOLD, 30));
        labelError.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nom employé
        JLabel labelEmployee = new JLabel(employeeName);
        labelEmployee.setFont(new Font("Serif", Font.BOLD, 30));
        labelEmployee.setForeground(new Color(100, 80, 120));
        labelEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);

        // on les rajoute dans le meme panel
        topPanel.add(labelError);
        topPanel.add(labelEmployee);

        // Label error 2*
        JLabel labelError2 = new JLabel("Vous ne pouvez plus pointé");
        labelError2.setFont(new Font("Serif", Font.BOLD, 30));
        labelError2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label cause
        JLabel labelReason = new JLabel("Car vous avez pointé");
        labelReason.setFont(new Font("Serif", Font.BOLD, 30));
        labelReason.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Label suite cause
        JLabel labelReason2 = new JLabel("2 fois cette journée");
        labelReason2.setFont(new Font("Serif", Font.BOLD, 30));
        labelReason2.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Bouton retour
        buttonMenu = new JButton("Retour au menu");
        buttonMenu.setFont(new Font("Serif", Font.BOLD, 30));
        buttonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenu.setPreferredSize(new Dimension(300, 70));
        buttonMenu.setBackground(Style.pastelPurple);
        buttonMenu.setFocusPainted(false);

        // Tout ajouter au panel au bon endroit
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelError2);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelReason);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelReason2);
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonMenu);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    // Getter pour le controller
    public JButton getButtonMenu() { return buttonMenu; }
    public void close() { frame.dispose(); }
}