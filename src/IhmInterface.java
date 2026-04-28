import javax.swing.*;
import java.awt.*;
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

        // Partie date + heure
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Style.pastelPeach);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("April 4th 2026");
        label1.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("14:32:10");
        label2.setFont(new Font("Serif", Font.BOLD, 40));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(label1);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(label2);

        // Panel du bouton check
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Style.pastelPeach);

        // Creation bouton check
        JButton button_check = new JButton("Check in/out");
        button_check.setPreferredSize(new Dimension(250, 70));
        button_check.setFont(new Font("Serif", Font.BOLD, 30));
        button_check.setBackground(Style.pastelPurple);
        button_check.setBorder(new RoundBtn(15));
        bottomPanel.add(button_check);
        // enleve les effets moches
        button_check.setFocusPainted(false);
        button_check.addActionListener(e -> fen.dispose());

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);


        fen.setContentPane(panel);
        fen.setVisible(true);
    }
    static class RoundBtn implements Border
    {
        private int r;
        RoundBtn(int r) {
            this.r = r;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.r+1, this.r+1, this.r+2, this.r);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, r, r);
        }
    }
}