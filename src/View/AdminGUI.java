package View;

import Helper.Helper;
import Model.Admin;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
    private JButton btn_exit;
    private JLabel lbl_title;
    private JTextField fld_username;
    private JTextField fld_password;
    private JComboBox cmb_user_type;
    private JTextField fld_name_surname;
    private JButton btn_user_add;
    private JTable tbl_user_list;
    private JTextField fld_delete_username;
    private JButton btn_user_delete;
    private final Admin admin;
    private DefaultTableModel model_user_list;
    private Object[] row_user_list;


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

        loadComboBoxUserList();
        model_user_list = new DefaultTableModel();
        Object[] col_user_list = {"Username", "Password", "Name Surname", "User Type"};
        model_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        tbl_user_list.setModel(model_user_list);
        loadTableUserList();

        btn_user_add.addActionListener(e -> {
            if (fld_username.getText().isEmpty() || fld_password.getText().isEmpty() || fld_name_surname.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in the blanks.");
            } else {
                if (admin.addUser(fld_username.getText(), fld_password.getText(), cmb_user_type.getSelectedItem().toString(), fld_name_surname.getText())) {
                    JOptionPane.showMessageDialog(null, "User added.");
                    loadTableUserList();
                } else {
                    JOptionPane.showMessageDialog(null, "User could not be added.");
                }
                fld_username.setText("");
                fld_password.setText("");
                fld_name_surname.setText("");
            }
        });
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tbl_user_list.getSelectedRow();
            if (selectedRow != -1) {
                fld_delete_username.setText(tbl_user_list.getValueAt(selectedRow, 0).toString());
            }
        });
        tbl_user_list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tbl_user_list_update();
                }
            }
        });
        btn_user_delete.addActionListener(e -> {
            if (fld_delete_username.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in the blanks.");
            } else {
                if(Helper.confirm("Are you sure?", "Kullanici Sil", JOptionPane.YES_NO_OPTION)) {
                    if (admin.deleteUser(fld_delete_username.getText())) {
                        JOptionPane.showMessageDialog(null, "User deleted.");
                        loadTableUserList();
                    } else {
                        JOptionPane.showMessageDialog(null, "User could not be deleted.");
                    }
                }

                fld_delete_username.setText("");
            }
        });
    }

    private void tbl_user_list_update() {
        int selectedRow = tbl_user_list.getSelectedRow();
        if (selectedRow != -1) {
            String username = tbl_user_list.getValueAt(selectedRow, 0).toString();
            String password = tbl_user_list.getValueAt(selectedRow, 1).toString();
            String name = tbl_user_list.getValueAt(selectedRow, 2).toString();
            String type = tbl_user_list.getValueAt(selectedRow, 3).toString();

            if (admin.updateUser(username, password, type, name)) {
                JOptionPane.showMessageDialog(null, "User updated.");
                loadTableUserList();
            } else {
                JOptionPane.showMessageDialog(null, "User could not be updated.");
            }
        } else {
            // Handle the case where no row is selected
            JOptionPane.showMessageDialog(null, "Please select a user from the table to update.");
        }
    }


    private void loadTableUserList() {
        DefaultTableModel model_user_clear = (DefaultTableModel) tbl_user_list.getModel();
        model_user_clear.setRowCount(0);
        ArrayList<User> userList = admin.getUserList();
        for (User user : userList) {
            row_user_list[0] = user.getUsername();
            row_user_list[1] = user.getPassword();
            row_user_list[2] = user.getName();
            row_user_list[3] = user.getType();
            model_user_list.addRow(row_user_list);
        }

    }

    private void loadComboBoxUserList() {
        cmb_user_type.removeAllItems();
        String[] userTypes = {"admin", "employee"};
        for (String userType : userTypes) {
            cmb_user_type.addItem(userType);
        }
    }

}
