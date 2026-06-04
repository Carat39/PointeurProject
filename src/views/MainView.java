package views;

import views.utils.Style;
import javax.swing.*;
import java.awt.*;

/**
 * @autor Romain
 * Permet de gerer l'interface du menu principale
 * de la pointeuse.
 */
public class MainView {
    private JFrame frame;
    private JLabel labelDate;
    private JLabel labelHour;
    private JLabel labelRounded;
    private JComboBox<String> comboEmployees;
    private JButton checkButton;
    private JTextField textFieldIp;
    private JTextField textFieldPort;

    /**
     * Fonction principale permettant
     * d'appeler la methode buildant la page
     */
    public MainView() {
        buildUI();
    }

    /**
     *  Fonction initalisant tous les aspects
     *  graphique
     */
    private void buildUI() {
        // JFrame
        frame = new JFrame("Pointeuse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Panneau principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Style.pastelPeach);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 30));

        // Top : titre
        JLabel titre = new JLabel("Pointeuse - Time Tracker", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 22));
        titre.setForeground(new Color(100, 80, 120));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titre, BorderLayout.NORTH);

        // Centre : date + heure + arrondi
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Style.pastelPeach);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        labelDate = new JLabel();
        labelDate.setFont(new Font("Serif", Font.BOLD, 40));
        labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelHour = new JLabel();
        labelHour.setFont(new Font("Serif", Font.BOLD, 40));
        labelHour.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelRounded = new JLabel();
        labelRounded.setFont(new Font("Serif", Font.ITALIC, 25));
        labelRounded.setForeground(new Color(130, 100, 160));
        labelRounded.setAlignmentX(Component.CENTER_ALIGNMENT);

        // IP et port
        JPanel ipPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ipPanel.setBackground(Style.pastelPeach);

        JLabel labelIp = new JLabel("IP :");
        labelIp.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel labelPort = new JLabel("Port :");
        labelPort.setFont(new Font("Serif", Font.BOLD, 20));

        textFieldIp = new JTextField(15);
        textFieldIp.setFont(new Font("Serif", Font.PLAIN, 20));
        textFieldPort = new JTextField(15);
        textFieldPort.setFont(new Font("Serif", Font.PLAIN, 20));

        ipPanel.add(labelIp);
        ipPanel.add(textFieldIp);
        ipPanel.add(labelPort);
        ipPanel.add(textFieldPort);

        // rajout dans le panel
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(labelDate);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(labelHour);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(labelRounded);
        centerPanel.add(Box.createVerticalStrut(100));
        centerPanel.add(ipPanel);

        // Bottom : combobox + bouton
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Style.pastelPeach);
        // creation combobox
        comboEmployees = new JComboBox<>();
        comboEmployees.setFont(new Font("Serif", Font.PLAIN, 25));
        comboEmployees.setPreferredSize(new Dimension(450, 60));
        comboEmployees.setBackground(Style.pastelBlue);
        bottomPanel.add(comboEmployees);

        bottomPanel.add(Box.createHorizontalStrut(12));
        // creation bouton check
        checkButton = new JButton("Check in/out");
        checkButton.setPreferredSize(new Dimension(250, 70));
        checkButton.setFont(new Font("Serif", Font.BOLD, 30));
        checkButton.setBackground(Style.pastelPurple);
        checkButton.setFocusPainted(false);
        bottomPanel.add(checkButton);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    // Getters pour le controller
    public JComboBox<String> getComboEmployees() { return comboEmployees; }
    public JButton getCheckButton() { return checkButton; }
    public void setDate(String date) { labelDate.setText(date); }
    public void setHour(String hour) { labelHour.setText(hour); }
    public void setRounded(String rounded) { labelRounded.setText(rounded); }
    public String getTextFieldIp(){return textFieldIp.getText();}
    public String getTextFieldPort(){return textFieldPort.getText();}
    public void close() {frame.dispose();}
}