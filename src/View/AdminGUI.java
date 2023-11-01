package View;

import Model.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
    private JButton btn_exit;
    private JLabel lbl_title;
    private final Admin admin;

    public AdminGUI(Admin admin) {
        this.admin = admin;
        lbl_title.setText("Admin Panel");
        add(wrapper);
        setSize(1000, 500);
        setTitle("Login");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_exit.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });
    }

}
