package views;

import mainApplication.management.Departement;
import mainApplication.management.Staff;
import views.utils.Variables;

import javax.swing.*;
import java.awt.*;

/**
 * VIEW – Page « Edit Employee »
 * This is the page that allws us to change the pointings
 * of a selected employee.
 * We can create/update/delete a pointing using this class.
 */
public class Page6 {

    private final JPanel panel;
    private final JButton closeBtn;
    private final JButton backBtn;
    private final JButton NewPointingBtn;
    private final JButton UpdatePointingBtn;

    private final JTextField nameField;

    private Staff staffRef;

    /**
     * This method create page6
     *
     * Page 6 is the page that manages poointings, basically it
     * can create a new pointer or it can modify one.
     * In a little bit this function will also be able to
     * delete a pointing, but not for now
     * Penser a enlever avant rendu pour juste dire que on a fait la partie delete!
     */
    public Page6() {
        panel = new JPanel(null);
        panel.setBackground(Variables.PASTEL_YELLOW);

        closeBtn = Variables.createCloseBtn();
        backBtn = Variables.createBackBtn();


        Font customFont = new Font("Lucida Handwriting", Font.PLAIN, 14);
        Font labelFont = new Font("Lucida Handwriting", Font.BOLD, 13);

        int centerX = Variables.WIDTH / 2;
        int topY = Variables.HEIGHT / 3;

        JLabel nameLabel = new JLabel("Poiting : ");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.DARK_GRAY);

        nameField = new JTextField();
        nameField.setFont(customFont);
        nameField.setBackground(Color.WHITE);


        nameLabel.setBounds(centerX - 180, topY, 130, 30);
        nameField.setBounds(centerX - 30, topY, 210, 30);


        int buttonsY = topY + 170;
        int btnWidth = 250;
        int btnHeight = 40;

        NewPointingBtn = new JButton("New Pointing");
        NewPointingBtn.setFont(customFont);
        NewPointingBtn.setBackground(Variables.PASTEL_PEACH);
        NewPointingBtn.setForeground(Color.DARK_GRAY);
        NewPointingBtn.setFocusPainted(false);
        NewPointingBtn.setBounds(centerX - 275, buttonsY, btnWidth, btnHeight);


        UpdatePointingBtn = new JButton("Update Pointing");
        UpdatePointingBtn.setFont(customFont);
        UpdatePointingBtn.setBackground(Variables.PASTEL_PEACH);
        UpdatePointingBtn.setForeground(Color.DARK_GRAY);
        UpdatePointingBtn.setFocusPainted(false);
        UpdatePointingBtn.setBounds(centerX + 25, buttonsY, btnWidth, btnHeight);


        panel.add(Variables.createTitle("Manage Pointing"));
        panel.add(closeBtn);
        panel.add(backBtn);
        panel.add(NewPointingBtn);
        panel.add(UpdatePointingBtn);

        panel.add(nameLabel);
        panel.add(nameField);
    }


    //getters
    public JPanel getPanel() {
        return panel;
    }

    public JButton getCloseBtn() {
        return closeBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public JButton getNewPointingBtn() {
        return NewPointingBtn;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public Staff getStaffRef() {
        return staffRef;
    }

    public void loadStaff(Staff s) {
        this.staffRef = s;
    }
    public JButton getUpdatePointingBtn(){return UpdatePointingBtn;}
}