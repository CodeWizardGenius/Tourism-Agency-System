package View;

import Helper.Helper;
import Model.Employee;
import Model.Reservation;
import Model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    private JTable tbl_reservation_list;
    private JTextField fld_reservation_id;
    private JButton btn_reservation_delete;
    private JTextField fld_room_no;
    private JTextField fld_user_name;
    private JButton btn_reservation_add;
    private JTextField fld_start_date;
    private JTextField fld_finish_date;
    private JTextField fld_days_numbers;
    private DefaultTableModel model_reservation_list;
    private Object[] row_reservation_list;

    public ReservationGUI(Room room, Employee employee) {
        add(wrapper);
        setSize(800, 800);
        setTitle("Rezervasyon ");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        fld_user_name.setText(employee.getName());
        fld_room_no.setText(String.valueOf(room.getBed_number()));
        fld_start_date.setText(room.getSeason().getStart_date());
        fld_finish_date.setText(room.getSeason().getEnd_date());

        model_reservation_list = new DefaultTableModel();
        Object[] col_reservation_list = {"ID","Otel ID", "Calisan ID", "Baslangic Tarihi", "Bitis Tarihi", "Oda No"};
        model_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];
        tbl_reservation_list.setModel(model_reservation_list);
        loadTableReservationList();
        tbl_reservation_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                int selected_row = tbl_reservation_list.getSelectedRow();
                fld_reservation_id.setText(model_reservation_list.getValueAt(selected_row, 0).toString());
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        });

        btn_reservation_delete.addActionListener(e -> {
            if (fld_reservation_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rezervasyon ID bos olamaz!");
            } else {
                int reservation_id = Integer.parseInt(fld_reservation_id.getText());
                if (Helper.confirm("Rezervasyonu silmek istediginizden emin misiniz?", "UYARI", 2)) {
                    if (Reservation.delete(reservation_id)) {
                        JOptionPane.showMessageDialog(null, "Rezervasyon silindi!");
                        if (Room.update(room.getId(), room.getStock() + 1)) {
                            JOptionPane.showMessageDialog(null, "Oda stogu guncellendi!", "UYARI", 2);
                            loadTableReservationList();
                        } else {
                            JOptionPane.showMessageDialog(null, "Oda stogu guncellenemedi!", "UYARI", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Rezervasyon silinemedi!");
                    }
                }
            }

        });
        btn_reservation_add.addActionListener(e -> {
            if (fld_room_no.getText().isEmpty() && fld_user_name.getText().isEmpty() && fld_start_date.getText().isEmpty() && fld_finish_date.getText().isEmpty() && fld_days_numbers.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tum alanlar dolu olmalidir!");
            } else {
                int room_no = Integer.parseInt(fld_room_no.getText());
                String user_name = fld_user_name.getText();
                String start_date = fld_start_date.getText();
                String finish_date = fld_finish_date.getText();
                if (Helper.confirm("Rezervasyonu ucretini odemek istediginizden emin misiniz?", "UYARI", 2)) {
                    int days_numbers = Integer.parseInt(fld_days_numbers.getText());
                    int priceAdult = days_numbers * room.getPrice_adult();
                    int priceChild = days_numbers * room.getPrice_child();
                    int price = priceAdult + priceChild;
                    if (Helper.confirm("Odenmesi gereken ucret: " + price + " TL", "UYARI", 2)) {
                        if (Reservation.add(room.getOtel_id(), employee.getId(), start_date, finish_date, room_no)) {
                            int stock = room.getStock() - 1;
                            if (Room.update(room_no, stock)) {
                                JOptionPane.showMessageDialog(null, "Oda stogu guncellendi!", "UYARI", 2);
                                loadTableReservationList();
                            } else {
                                JOptionPane.showMessageDialog(null, "Oda stogu guncellenemedi!", "UYARI", 2);
                            }
                            JOptionPane.showMessageDialog(null, "Rezervasyon eklendi!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Rezervasyon eklenemedi!");
                        }
                    }
                }

            }
        });
    }

    private void loadTableReservationList() {
        model_reservation_list.setRowCount(0);
        for (Reservation reservation : Reservation.getList()) {
            row_reservation_list[0] = reservation.getId();
            row_reservation_list[1] = reservation.getEmployeeId();
            row_reservation_list[2] = reservation.getRoomId();
            row_reservation_list[3] = reservation.getStartDate();
            row_reservation_list[4] = reservation.getEndDate();
            row_reservation_list[5] = reservation.getRoom_number();
            model_reservation_list.addRow(row_reservation_list);
        }
    }
}
