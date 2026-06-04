package views;

import Time.TimeManagement;
import views.utils.Style;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Romain
 * Permet de gerer l'interface de confirmation
 * lors du pointage
 */
public class ConfirmView {
    private JFrame frame;
    private JButton buttonMenu;

    /**
     * Fonction principale permettant
     * d'appeler la methode buildant la page de confirmation
     */
    public ConfirmView(LocalDate currentDate, LocalTime currentTime, String employeeName) {
        buildUI(currentDate, currentTime, employeeName);
    }

    /**
     * Fonction initialisant tous les aspects graphiques
     */
    private void buildUI(LocalDate currentDate, LocalTime currentTime, String employeeName) {
        // Frame
        frame = new JFrame("TimeChecker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Style.pastelPeach);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Label "vous avez pointé"
        JLabel labelEnd = new JLabel("You have clocked in");
        labelEnd.setFont(new Font("Serif", Font.BOLD, 30));
        labelEnd.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nom employé
        JLabel labelEmployee = new JLabel(employeeName);
        labelEmployee.setFont(new Font("Serif", Font.BOLD, 25));
        labelEmployee.setForeground(new Color(100, 80, 120));
        labelEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Date
        JLabel labelDate = new JLabel("on " + TimeManagement.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.ENGLISH)));
        labelDate.setFont(new Font("Serif", Font.BOLD, 30));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Heure
        JLabel labelHour = new JLabel(
                "at " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        );
        labelHour.setFont(new Font("Serif", Font.BOLD, 30));
        labelHour.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Bouton retour
        buttonMenu = new JButton("Back to menu");
        buttonMenu.setFont(new Font("Serif", Font.BOLD, 30));
        buttonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenu.setPreferredSize(new Dimension(300, 70));
        buttonMenu.setBackground(Style.pastelPurple);
        buttonMenu.setFocusPainted(false);

        // Ajout au panel
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(labelEmployee);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelEnd);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelDate);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelHour);
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