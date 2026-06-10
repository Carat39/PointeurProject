package views;
import views.utils.Style;
import javax.swing.*;
import java.awt.*;
/**
 *  Allow to manage exception handling
 *  during clock-in if a user
 *  has already clocked in twice on the same day
 */
public class ExceptView {
    /*** main frame used to display things on it*/
    private static JFrame frame;
    /** Button allowing the user to return to the main menu */
    private JButton buttonMenu;
    /**Constructs the ExceptView and builds the interface
     * @param employeeName  the name of the employee
     */
    public ExceptView(String employeeName) {
        buildUI(employeeName);
    }
    /**
     * Main method to build the
     * graphical interface of the ExceptView
     * @param employeeName the full name of the employee who pointed
     */
    private void buildUI(String employeeName) {
        //Frame
        frame = new JFrame("TimeChecker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 425);
        frame.setLocationRelativeTo(null);
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Style.pastelPeach);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // Top Pannel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Style.pastelPeach);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        // Text Label
        JLabel labelError = new JLabel("Warning ");
        labelError.setFont(new Font("Serif", Font.BOLD, 30));
        labelError.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Employee names
        JLabel labelEmployee = new JLabel(employeeName);
        labelEmployee.setFont(new Font("Serif", Font.BOLD, 30));
        labelEmployee.setForeground(new Color(100, 80, 120));
        labelEmployee.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add in the same panel
        topPanel.add(labelError);
        topPanel.add(labelEmployee);

        // Label error 2*
        JLabel labelError2 = new JLabel("You cannot check in anymore");
        labelError2.setFont(new Font("Serif", Font.BOLD, 30));
        labelError2.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Label cause
        JLabel labelReason = new JLabel("You have already checked in");
        labelReason.setFont(new Font("Serif", Font.BOLD, 30));
        labelReason.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Label cause 2nd
        JLabel labelReason2 = new JLabel("2 times today");
        labelReason2.setFont(new Font("Serif", Font.BOLD, 30));
        labelReason2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Backbutton
        buttonMenu = new JButton("Back to menu");
        buttonMenu.setFont(new Font("Serif", Font.BOLD, 30));
        buttonMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonMenu.setPreferredSize(new Dimension(300, 70));
        buttonMenu.setBackground(Style.pastelPurple);
        buttonMenu.setFocusPainted(false);

        // Add in the panel at the same place
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelError2);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelReason);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(labelReason2);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonMenu);

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setResizable(true);
    }
    // Getter for le controller
    /**@return the menu button*/
    public JButton getButtonMenu() { return buttonMenu; }
    /** close the frame of ExeceptView*/
    public void close() { frame.dispose(); }
}