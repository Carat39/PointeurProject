import javax.swing.*;
import java.awt.*;

public class IHM_Manager extends JPanel {

    public static void main(String[] args) {
        //couleurs
        Color pastelPink   = new Color(255, 182, 193);
        Color pastelBlue   = new Color(173, 216, 230);
        Color pastelGreen  = new Color(144, 238, 144);
        Color pastelPurple = new Color(216, 191, 216);
        Color pastelYellow = new Color(255, 255, 204);
        Color pastelPeach  = new Color(255, 218, 185);

        //taille
        int width = 800;
        int height = 600;


        JFrame frame = new JFrame("Pointeuse Manager");
        IHM_Manager panel = new IHM_Manager();

        frame.setSize(width, height);
        frame.setUndecorated(true);

        panel.setBackground(pastelPeach);

        //creation du bouton close
        panel.setLayout(null);
        JButton closeBtn = new JButton("X");
        closeBtn.setBounds(width - 40, 10, 30, 30);
        closeBtn.setBackground(pastelPink);


        closeBtn.addActionListener(e -> frame.dispose());

        panel.add(closeBtn);
        frame.setContentPane(panel);

        //creation du bouton pointage enregistré
        JButton checkBtn = new JButton("Saved Work time");
        checkBtn.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        checkBtn.setBackground(pastelPurple);
        checkBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        //checkBtn.addActionListener(e -> frame.dispose());

        panel.add(checkBtn);



        //Creation du bouton pointage actuels
        JButton clockedBtn = new JButton ("Clocked in Now");
        clockedBtn.setBounds((width-300)/2, height - 11*(height/20), 300,30);
        clockedBtn.setBackground(pastelPurple);
        clockedBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        //clockedBtn.addActionListener(e -> frame.dispose());

        panel.add(clockedBtn);


        //jsp btn 3 or smth
        JButton A = new JButton ("JSP");
        A.setBounds((width-300)/2, height - 6*(height/5), 300,30);
        A.setBackground(pastelPurple);
        A.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        //clockedBtn.addActionListener(e -> frame.dispose());

        panel.add(A);



        //creation du titre de la page
        JLabel label = new JLabel("Time Tracker v1.0");
        label.setBounds((width-300)/2, 5, 300, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        panel.add(label);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}