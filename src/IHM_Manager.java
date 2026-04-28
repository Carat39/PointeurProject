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
        IHM_Manager panel2 = new IHM_Manager();

        frame.setSize(width, height);
        frame.setUndecorated(true);

        panel.setBackground(pastelPeach);
        panel2.setBackground(pastelYellow);


        // Creation d'un menu de pages de l'IHM
        CardLayout menu = new CardLayout();
        JPanel menuPanel = new JPanel(menu);

//************************** Premiere Fenetre ****************************//

        //creation du bouton close
        panel.setLayout(null);
        JButton closeBtn = new JButton("");
        closeBtn.setBounds(width - 40, 10, 30, 30);
        closeBtn.setBackground(pastelPink);


        closeBtn.addActionListener(e -> frame.dispose());

        panel.add(closeBtn);

        //creation du bouton pointage enregistré
        JButton checkBtn = new JButton("Saved Work time");
        checkBtn.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        checkBtn.setBackground(pastelPurple);
        checkBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        checkBtn.addActionListener(e -> menu.show(menuPanel, "page 2"));

        panel.add(checkBtn);



        //Creation du bouton pointage actuels
        JButton clockedBtn = new JButton ("Clocked in Now");
        clockedBtn.setBounds((width-300)/2, height - 11*(height/20), 300,30);
        clockedBtn.setBackground(pastelPurple);
        clockedBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        clockedBtn.addActionListener(e -> menu.show(menuPanel, "page 2"));

        panel.add(clockedBtn);

        //creation du titre de la page
        JLabel label = new JLabel("Time Tracker v1.1");
        label.setBounds((width-300)/2, 5, 300, 40);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        //on met a jour le panel
        panel.add(label);

//************************************** DEUXIEME FENETRE *****************************************************

        //creation du bouton close
        panel2.setLayout(null);
        panel2.add(closeBtn);

        //creation du bouton pointage enregistré
        JButton Backbtn = new JButton("Retournons page 1");
        Backbtn.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        Backbtn.setBackground(pastelPurple);
        Backbtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        Backbtn.addActionListener(e -> menu.show(menuPanel, "page 1"));

        panel2.add(Backbtn);

        //creation du titre de la page
        JLabel label2 = new JLabel("Page 2");
        label2.setBounds((width-300)/2, 5, 300, 40);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.DARK_GRAY);
        label2.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        //on met a jour le panel
        panel2.add(label2);






        //Creation de la page numéro 1
        menuPanel.add(panel,"page 1");
        menuPanel.add(panel2,"page 2");
        frame.setContentPane(menuPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}