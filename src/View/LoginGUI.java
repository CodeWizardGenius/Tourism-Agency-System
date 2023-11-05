package View;

import Helper.Helper;
import Model.Admin;
import Model.Employee;
import Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JPanel pnl_bottom;
    private JPanel pnl_top;

    public LoginGUI() {
        add(wrapper);
        setSize(400, 400);
        setTitle("Login");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_login.addActionListener(e -> {
            if (Helper.isEmpty(fld_username) || Helper.isEmpty(fld_password)) {
                Helper.showMessage("Kullanıcı adı veya şifre boş olamaz!", "UYARI", JOptionPane.WARNING_MESSAGE);
            } else {
                User user = User.getCredentials(fld_username.getText(), fld_password.getText());
                if (user == null) {
                    Helper.showMessage("Kullanıcı adı veya şifre hatalı!", "UYARI", JOptionPane.WARNING_MESSAGE);
                } else {
                    switch (user.getType()) {
                        case "admin":
                            System.out.println("Admin");
                            new AdminGUI((Admin) user);
                            dispose();
                            break;
                        case "employee":
                            System.out.println("Employee");
                            new EmployeeGUI((Employee) user);
                            dispose();
                            break;
                        default:
                            Helper.showMessage("Kullanıcı tipi hatalı!", "UYARI", JOptionPane.WARNING_MESSAGE);
                            break;
                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI = new LoginGUI();

    }
}
