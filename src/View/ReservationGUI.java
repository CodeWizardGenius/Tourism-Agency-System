package View;

import Model.Employee;
import Model.Room;

import javax.swing.*;

public class ReservationGUI {
    private JPanel wrapper;
    private JTable table1;
    private JTextField fld_reservation_id;
    private JButton btn_reservation_delete;

    public ReservationGUI(Room room, Employee employee) {
        wrapper = new JPanel();
        wrapper.add(new JLabel("Reservation for " + room.getName() + " by " + employee.getName()));
    }
}
