package IhmInterface;

import Time.TimeManagement;
import Utils.Style;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IhmInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mainUI());
    }

    private static void mainUI() {
        JFrame fen = new JFrame("Pointeuse");
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 800;
        int height = 600;
        fen.setSize(width, height);
        fen.setLocationRelativeTo(null);

        // Panneau principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Style.pastelPeach);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Top : titre
        JLabel titre = new JLabel("Pointeuse – Time Tracker", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 22));
        titre.setForeground(new Color(100, 80, 120));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titre, BorderLayout.NORTH);

        // -- CENTER : date + heure + heure arrondie
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Style.pastelPeach);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel labelDate = new JLabel();
        labelDate.setFont(new Font("Serif", Font.BOLD, 40));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelHour = new JLabel();
        labelHour.setFont(new Font("Serif", Font.BOLD, 40));
        labelHour.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 1. heure arrondie
        JLabel labelRounded = new JLabel();
        labelRounded.setFont(new Font("Serif", Font.ITALIC, 25));
        labelRounded.setForeground(new Color(130, 100, 160));
        labelRounded.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(labelDate);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(labelHour);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(labelRounded);

        // mise à jour toutes les secondes
        Timer timer = new Timer(1000, e -> {
            TimeManagement.setDate();
            TimeManagement.setHour();
            // mise à jour date
            labelDate.setText(TimeManagement.getDate().format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            // mise à jour heure
            labelHour.setText(TimeManagement.getHour().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            // mise à jour heure arrondi
            labelRounded.setText("Arrondi: " + TimeManagement.round(TimeManagement.getHour()).format(DateTimeFormatter.ofPattern("HH:mm")));
        });
        timer.start();

        //  Bottom : combobox + bouton
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Style.pastelPeach);

        // 2. ComboBox employés
        JComboBox<String> comboEmployees = getStringJComboBox();
        bottomPanel.add(comboEmployees);

        bottomPanel.add(Box.createHorizontalStrut(12));

        // Button check
        JButton checkButton = new JButton("Check in/out");
        checkButton.setPreferredSize(new Dimension(250, 70));
        checkButton.setFont(new Font("Serif", Font.BOLD, 30));
        checkButton.setBackground(Style.pastelPurple);
        checkButton.setFocusPainted(false);
        // action quand on click sur le bouton
        checkButton.addActionListener(e -> {
            LocalDate currentDate = TimeManagement.getDate();
            LocalTime currentRoundHour = TimeManagement.round(TimeManagement.getHour());
            // recuperer nom qui pointe

            // afficher fenetre de fin
            endUI(currentDate, currentRoundHour);
            // afficher date et heure arrondi
            System.out.println(currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
            System.out.println(currentRoundHour.format(DateTimeFormatter.ofPattern("HH:mm")));
        });

        bottomPanel.add(checkButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        fen.setContentPane(panel);
        fen.setVisible(true);
    }

    private static JComboBox<String> getStringJComboBox() {
        JComboBox<String> comboEmployees = new JComboBox<>(new String[]{
                "Arthur Brajot", "Clément Sauger", "Nino Maigret", "Romain Blaquart",
                "Arthur Brajot", "Clément Sauger", "Nino Maigret", "Romain Blaquart",
                "Arthur Brajot", "Clément Sauger", "Nino Maigret", "Romain Blaquart",
                "Arthur Brajot", "Clément Sauger", "Nino Maigret", "Romain Blaquart"

        });
        comboEmployees.setFont(new Font("Serif", Font.PLAIN, 25));
        comboEmployees.setPreferredSize(new Dimension(450, 60));
        comboEmployees.setBackground(Style.pastelBlue);
        return comboEmployees;
    }

    private static void endUI(LocalDate currentDate, LocalTime currentTime){
        // ceation fenetre
        JFrame fen = new JFrame("Pointeuse");
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 500;
        int height = 320;
        fen.setSize(width, height);
        fen.setLocationRelativeTo(null);

        // creation panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Style.pastelPeach);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // creation label avec les infos
        JLabel labelEnd = new JLabel();
        labelEnd.setFont(new Font("Serif", Font.BOLD, 30));
        labelEnd.setText("Vous avez pointé");
        labelEnd.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEnd.setBackground(Style.pastelPeach);

        // afficher info date et heure
        JLabel labelDate = new JLabel();
        labelDate.setFont(new Font("Serif", Font.BOLD, 30));
        labelDate.setText("le " + currentDate.format(DateTimeFormatter.ofPattern("EEEE d MMMM yyyy")));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelHour = new JLabel();
        labelHour.setFont(new Font("Serif", Font.BOLD, 30));
        labelHour.setText("à " + currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        labelHour.setAlignmentX(Component.CENTER_ALIGNMENT);


        // bouton retour menu
        JButton buttonMenu = new JButton();
        buttonMenu.setFont(new Font("Serif", Font.BOLD, 30));
        buttonMenu.setText("Retour au menu");
        buttonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenu.setPreferredSize(new Dimension(100, 70));
        buttonMenu.setBackground(Style.pastelPurple);
        buttonMenu.setFocusPainted(false);



        // regrouper les labels
        mainPanel.add(labelEnd);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelDate);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelHour);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonMenu);

        fen.setContentPane(mainPanel);
        fen.setVisible(true);

    }
}