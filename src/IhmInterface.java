import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class IhmInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createUI());
    }

    private static void createUI() {
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

        JLabel label1 = new JLabel("April 4th 2026");
        label1.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("14:32:10");
        label2.setFont(new Font("Serif", Font.BOLD, 40));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 1. heure arrondie
        JLabel labelRounded = new JLabel("→ let's say 14:30");
        labelRounded.setFont(new Font("Serif", Font.ITALIC, 25));
        labelRounded.setForeground(new Color(130, 100, 160));
        labelRounded.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(label1);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(label2);
        centerPanel.add(Box.createVerticalStrut(8));
        centerPanel.add(labelRounded);

        //  Bottom : combobox + bouton
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Style.pastelPeach);

        // 2. ComboBox employés
        JComboBox<String> comboEmployees = getStringJComboBox();
        bottomPanel.add(comboEmployees);

        bottomPanel.add(Box.createHorizontalStrut(12));

        // Bouton check
        JButton button_check = new JButton("Check in/out");
        button_check.setPreferredSize(new Dimension(250, 70));
        button_check.setFont(new Font("Serif", Font.BOLD, 30));
        button_check.setBackground(Style.pastelPurple);
        button_check.setFocusPainted(false);


        bottomPanel.add(button_check);

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
}