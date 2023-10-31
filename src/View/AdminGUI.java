package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
    private JButton btn_exit;
    private JLabel lbl_title;

    public AdminGUI() {
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
