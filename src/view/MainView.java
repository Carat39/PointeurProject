package view;

import Utils.Style;
import javax.swing.*;
import java.awt.*;

public class MainView {

    private JFrame frame;
    private JLabel labelDate;
    private JLabel labelHour;
    private JLabel labelRounded;
    private JComboBox<String> comboEmployees;
    private JButton checkButton;

    public MainView() {
        buildUI();
    }

    private void buildUI() {
        // JFrame
        frame = new JFrame("Pointeuse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Panneau principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Style.pastelPeach);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // TOP : titre
        JLabel titre = new JLabel("Pointeuse - Time Tracker", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 22));
        titre.setForeground(new Color(100, 80, 120));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titre, BorderLayout.NORTH);

        // CENTER : date + heure + arrondi
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
        // rajout dans le panel
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(labelDate);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(labelHour);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(labelRounded);

        // BOTTOM : combobox + bouton
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
    }

    // Getters pour le controller
    public JComboBox<String> getComboEmployees() { return comboEmployees; }
    public JButton getCheckButton() { return checkButton; }
    public void setDate(String date) { labelDate.setText(date); }
    public void setHour(String hour) { labelHour.setText(hour); }
    public void setRounded(String rounded) { labelRounded.setText(rounded); }
}