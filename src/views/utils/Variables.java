package views.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * VIEW (composants partagés) – Fabrique les éléments Swing réutilisables.
 * Aucune logique métier, aucun listener : tout ça appartient au Controller.
 */
public class Variables {

    static final Color PASTEL_PINK   = new Color(255, 182, 193);
    static final Color PASTEL_PURPLE = new Color(216, 191, 216);
    public static final Color PASTEL_YELLOW = new Color(255, 255, 204);
    public static final Color PASTEL_PEACH  = new Color(255, 218, 185);

    public static final int WIDTH  = 800;
    public static final int HEIGHT = 600;

    public static JButton createCloseBtn() {
        JButton btn = new JButton("❌");
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setBounds(WIDTH - 40, 10, 30, 30);
        btn.setBackground(PASTEL_PINK);
        return btn;
    }

    public static JButton createBackBtn() {
        JButton btn = new JButton("\uD83D\uDD19");
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setBounds(WIDTH - 40, HEIGHT - 40, 30, 30);
        btn.setBackground(PASTEL_PINK);
        return btn;
    }

    public static JLabel createTitle(String text) {
        JLabel label = new JLabel(text);
        label.setBounds((WIDTH - 300) / 2, 5, 300, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));
        return label;
    }

    public static JComboBox<String> createEmployeeList(ArrayList<String> employees) {
        JComboBox<String> box = new JComboBox<>();
        box.setBounds((WIDTH - 300) / 2, HEIGHT - 7 * (HEIGHT / 10), 300, 30);
        box.setBackground(PASTEL_PURPLE);
        box.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));
        for (String e : employees) box.addItem(e);
        return box;
    }

    public static JComboBox<String> createYearList(ArrayList<String> years) {
        JComboBox<String> box = new JComboBox<>();
        box.setBounds((WIDTH - 300) / 2, HEIGHT - 12 * (HEIGHT / 20), 300, 30);
        box.setBackground(PASTEL_PURPLE);
        box.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));
        for (String y : years) box.addItem(y);
        return box;
    }

    public static JButton createNavBtn(String label, int y) {
        JButton btn = new JButton(label);
        btn.setBounds((WIDTH - 300) / 2, y, 300, 30);
        btn.setBackground(PASTEL_PURPLE);
        btn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));
        return btn;
    }
}
