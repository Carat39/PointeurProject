import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    public JLabel create_title(String str){
        JLabel title = new JLabel(str);
        title.setBounds((width-300)/2, 5, 300, 40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.DARK_GRAY);
        title.setFont(new Font("Lucida Handwriting", Font.BOLD, 22));

        return title;
    }

    public JComboBox<String> create_employees_list(ArrayList<String> liste){
        JComboBox<String> employees_list = new JComboBox<>();
        employees_list.setBounds((width-300)/2, height - 7*(height/10), 300, 30);
        employees_list.setBackground(pastelPurple);
        employees_list.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        for (String employee : liste){
            employees_list.addItem(employee);
        }
        return employees_list;
    }

    public JComboBox<String> create_year_choices(){
        JComboBox<String> year_choices = new JComboBox<>();
        year_choices.setBounds((width-300)/2, height - 12*(height/20), 300, 30);
        year_choices.setBackground(pastelPurple);
        year_choices.setFont(new Font("Lucida Handwriting", Font.BOLD, 17));

        year_choices.addItem("2026");
        year_choices.addItem("2025");
        year_choices.addItem("2024");
        year_choices.addItem("2023");
        year_choices.addItem("2022");
        year_choices.addItem("2021");
        year_choices.addItem("2020");
        year_choices.addItem("2019");
        year_choices.addItem("2018");
        year_choices.addItem("2017");
        year_choices.addItem("2016");

        return year_choices;
    }

    public void first_page(JPanel menuPanel, CardLayout menu, JFrame frame){
        this.setLayout(null);

        //creation du bouton close
        JButton closeBtn1 = this.create_closebtn();
        closeBtn1.addActionListener(e -> frame.dispose());

        //creation du titre de la page
        JLabel title = this.create_title("Time Tracker v1.1");


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

        clockedBtn.addActionListener(e -> menu.show(menuPanel, "page 3"));


        //on met a jour le panel
        this.add(closeBtn1);
        this.add(checkBtn);
        this.add(clockedBtn);
        this.add(title);

    }

    public void second_page(JPanel menuPanel, CardLayout menu, JFrame frame,ArrayList<String> list_employees){
        this.setLayout(null);

        //creation du bouton fermeture
        JButton closeBtn = this.create_closebtn();
        closeBtn.addActionListener(e -> frame.dispose());

        //creation du bouton back
        JButton BackBtn = this.create_backbtn();
        BackBtn.addActionListener(e -> menu.show(menuPanel, "page 1"));

        //creation du titre de la page
        JLabel title = this.create_title("Saved work time");

        //creation du menu deroulant employee
        JComboBox<String> employees = create_employees_list(list_employees);
        //employees.addActionListener(e ->{
        //    String choix = (String) employees.getSelectedItem();});

        //creation du menu déroulant dates
        JComboBox<String> year_choices = create_year_choices();

        //on met a jour le panel
        this.add(closeBtn);
        this.add(BackBtn);
        this.add(employees);
        this.add(title);
        this.add(year_choices);
    }

    public void third_page(JPanel menuPanel, CardLayout menu, JFrame frame, ArrayList<String> list_employees){
        this.setLayout(null);

        //creation du bouton fermeture
        JButton closeBtn = this.create_closebtn();
        closeBtn.addActionListener(e -> frame.dispose());

        //creation du bouton back
        JButton BackBtn = this.create_backbtn();
        BackBtn.addActionListener(e -> menu.show(menuPanel, "page 1"));

        //creation du titre de la page
        JLabel title = this.create_title("Clocked in Now");

        //creation menu deroulant employee
        JComboBox<String> employees = create_employees_list(list_employees);

        //on met a jour le panel
        this.add(closeBtn);
        this.add(BackBtn);
        this.add(title);
        this.add(employees);

    }

    public static void main(String[] args) {
        //liste employees, a la main pour l'instant, serat récupérée via la serialization.
        ArrayList<String> list_employees = new ArrayList<>();
        list_employees.add("Nino Maigret");
        list_employees.add("Romain Blaquart");
        list_employees.add("Clément Sauger");
        list_employees.add("Carl Esswein");
        list_employees.add("Arthur Brajot");

        //couleurs
        Color pastelPink   = new Color(255, 182, 193);
        Color pastelBlue   = new Color(173, 216, 230);
        Color pastelGreen  = new Color(144, 238, 144);
        Color pastelPurple = new Color(216, 191, 216);
        Color pastelYellow = new Color(255, 255, 204);
        Color pastelPeach  = new Color(255, 218, 185);

        //dimensions
        int width = 800;
        int height = 600;

        //creation de la base
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
        panel3.setBackground(pastelYellow);

        panel.first_page(menuPanel, menu, frame);
        panel2.second_page(menuPanel, menu, frame, list_employees);
        panel3.third_page(menuPanel, menu, frame, list_employees);

        //creations des pages
        menuPanel.add(panel,"page 1");
        menuPanel.add(panel2,"page 2");
        menuPanel.add(panel3,"page 3");
        frame.setContentPane(menuPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}