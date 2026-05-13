import javax.swing.*;
import java.awt.*;

public class IHM_Manager extends JPanel {

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

    public JButton create_closebtn (){
        JButton closebtn = new JButton("❌");
        closebtn.setMargin(new Insets(0, 0, 0, 0));

        closebtn.setBounds(width - 40, 10, 30, 30);
        closebtn.setBackground(pastelPink);

        return closebtn;
    }

    public JButton create_backbtn (){
        JButton backbtn = new JButton("\uD83D\uDD19");
        backbtn.setMargin(new Insets(0, 0, 0, 0));
        backbtn.setBounds(width - 40, height - 40, 30, 30);
        backbtn.setBackground(pastelPink);

        return backbtn;
    }

    public void first_page(JPanel menuPanel, CardLayout menu, JFrame frame){
        this.setLayout(null);

        //creation du bouton close
        JButton closeBtn1 = this.create_closebtn();
        closeBtn1.addActionListener(e -> frame.dispose());


        //creation du bouton pointage enregistré
        JButton checkBtn = new JButton("Saved Work time");
        checkBtn.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        checkBtn.setBackground(pastelPurple);
        checkBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        checkBtn.addActionListener(e -> menu.show(menuPanel, "page 2"));


        //Creation du bouton pointage actuels
        JButton clockedBtn = new JButton ("Clocked in Now");
        clockedBtn.setBounds((width-300)/2, height - 11*(height/20), 300,30);
        clockedBtn.setBackground(pastelPurple);
        clockedBtn.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        clockedBtn.addActionListener(e -> menu.show(menuPanel, "page 2"));


        //creation du titre de la page
        JLabel title = new JLabel("Time Tracker v1.1");
        title.setBounds((width-300)/2, 5, 300, 40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.DARK_GRAY);
        title.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        //on met a jour le panel
        this.add(closeBtn1);
        this.add(checkBtn);
        this.add(clockedBtn);
        this.add(title);

    }

    public void second_page(JPanel menuPanel, CardLayout menu, JFrame frame){
        this.setLayout(null);

        //creation du bouton fermeture
        JButton closeBtn = this.create_closebtn();
        closeBtn.addActionListener(e -> frame.dispose());

        //creation du bouton back
        JButton BackBtn = this.create_backbtn();
        BackBtn.addActionListener(e -> menu.show(menuPanel, "page 1"));

        //creation du bouton pointage enregistré
        JButton NOTDONE = new JButton("Retournons page 1");
        NOTDONE.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        NOTDONE.setBackground(pastelPurple);
        NOTDONE.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        NOTDONE.addActionListener(e -> menu.show(menuPanel, "page 1"));

        //creation du titre de la page
        JLabel title = new JLabel("Page 2");
        title.setBounds((width-300)/2, 5, 300, 40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.DARK_GRAY);
        title.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        //on met a jour le panel
        this.add(closeBtn);
        this.add(BackBtn);
        this.add(NOTDONE);
        this.add(title);
    }

    public static void main(String[] args) {

        //couleurs
        Color pastelPink   = new Color(255, 182, 193);
        Color pastelBlue   = new Color(173, 216, 230);
        Color pastelGreen  = new Color(144, 238, 144);
        Color pastelPurple = new Color(216, 191, 216);
        Color pastelYellow = new Color(255, 255, 204);
        Color pastelPeach  = new Color(255, 218, 185);

        int width = 800;
        int height = 600;

        JFrame frame = new JFrame("Pointeuse Manager");

        // Creation d'un menu de pages de l'IHM
        CardLayout menu = new CardLayout();
        JPanel menuPanel = new JPanel(menu);


        IHM_Manager panel = new IHM_Manager();
        IHM_Manager panel2 = new IHM_Manager();
        IHM_Manager panel3 = new IHM_Manager();

        frame.setSize(width, height);
        frame.setUndecorated(true);

        panel.setBackground(pastelPeach);
        panel2.setBackground(pastelYellow);

        panel.first_page(menuPanel, menu, frame);
        panel2.second_page(menuPanel, menu, frame);




//******************************** TROISIEME FENETRE ********************************
        /*
        panel3.setLayout(null);

        //creation du bouton close
        JButton closeBtn3 = new JButton("❌");
        closeBtn3.setMargin(new Insets(0, 0, 0, 0));

        closeBtn3.setBounds(width - 40, 10, 30, 30);
        closeBtn3.setBackground(pastelPink);

        closeBtn3  .addActionListener(e -> frame.dispose());

        panel2.add(closeBtn3);

        //creation du bouton back
        JButton BackBtn2 = new JButton("\uD83D\uDD19");
        BackBtn2.setMargin(new Insets(0, 0, 0, 0));
        BackBtn2.setBounds(width - 40, height - 40, 30, 30);
        BackBtn2.setBackground(pastelPink);

        BackBtn2.addActionListener(e -> menu.show(menuPanel, "page 1"));

        panel2.add(BackBtn);

        //creation du bouton pointage enregistré
        JButton NOTDONE = new JButton("Retournons page 1");
        NOTDONE.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        NOTDONE.setBackground(pastelPurple);
        NOTDONE.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        NOTDONE.addActionListener(e -> menu.show(menuPanel, "page 1"));

        panel2.add(NOTDONE);

        //creation du titre de la page
        JLabel label2 = new JLabel("Page 2");
        label2.setBounds((width-300)/2, 5, 300, 40);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setForeground(Color.DARK_GRAY);
        label2.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        //on met a jour le panel
        panel2.add(label2);

        */

        //Creation de la page numéro 1
        menuPanel.add(panel,"page 1");
        menuPanel.add(panel2,"page 2");
        frame.setContentPane(menuPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}