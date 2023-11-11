package View;

import Helper.Helper;
import Model.Employee;
import Model.Reservation;
import Model.Room;

import javax.swing.*;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JTable tbl_reservation_list;
    private JTextField fld_reservation_id;
    private JButton btn_reservation_delete;
    private JTextField fld_room_no;
    private JTextField fld_user_name;
    private JButton btn_reservation_add;
    private JTextField fld_start_date;
    private JTextField fld_finish_date;

    public ReservationGUI(Room room, Employee employee) {
        wrapper = new JPanel();
        wrapper.add(new JLabel("Reservation for " + room.getName() + " by " + employee.getName()));
        setSize(600, 600);
        setTitle("Rezervasyon Ekrani");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_reservation_delete.addActionListener(e -> {
            if (fld_reservation_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rezervasyon ID bos olamaz!");
            } else {
                int reservation_id = Integer.parseInt(fld_reservation_id.getText());
                if(Helper.confirm("Rezervasyonu silmek istediginizden emin misiniz?", "UYARI",2)) {
                    if (Reservation.delete(reservation_id)) {
                        JOptionPane.showMessageDialog(null, "Rezervasyon silindi!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Rezervasyon silinemedi!");
                    }
                }
            }

        });
    }
}
