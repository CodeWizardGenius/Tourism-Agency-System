package View;

import Helper.Helper;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static Helper.Helper.isEmpty;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_title;
    private JButton btn_exit;
    private JTabbedPane tabbedPane1;
    private JTable tbl_otel_list;
    private JTextField fld_otel_name;
    private JTextField fld_otel_city;
    private JTextField fld_otel_region;
    private JTextField fld_otel_address;
    private JTextField fld_otel_email;
    private JTextField fld_otel_phone;
    private JTextField fld_otel_star;
    private JTextField fdl_otel_features;
    private JTextArea txtarea_ote_features;
    private JButton btn_otel_add;
    private JTextField fld_otel_id;
    private JButton btn_otel_delete;
    private JPanel pnl_lodgings_list;
    private JTable tbl_lodgings_list;
    private JButton btn_otel_features;
    private JTextField fld_otel_lodging_id;
    private JButton btn_lodgins_add;
    private JTextField fld_lodgind_id;
    private JButton btn_lodging_delete;
    private JComboBox cbm_otel_lodgings_features;
    private JTable tbl_room_list;

    private DefaultTableModel model_otel_list;
    private Object[] row_hotel_list;
    private final Employee employee;

    private DefaultTableModel model_otel_add;
    private Object[] row_hotel_add;

    private DefaultTableModel model_otel_delete;
    private Object[] row_hotel_delete;
    private DefaultTableModel model_lodgings_list;
    private Object[] row_lodgings_list;

    private DefaultTableModel model_room_list;
    private Object[] row_room_list;

    public EmployeeGUI(Employee employee) {
        this.employee = employee;
        lbl_title.setText("Personel Panel");
        add(wrapper);
        setSize(1500, 800);
        setTitle("Login");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_exit.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        // Otel Paneli

        model_otel_list = new DefaultTableModel();
        Object[] col_hotel_list = {"ID", "Otel Adı", "Şehir", "Bölge", "Tam Adres", "E-posta", "Telefon", "Yıldız", "Tesis Özellikleri"};
        model_otel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];

        loadHotelModel();
        tbl_otel_list.setModel(model_otel_list);
        tbl_otel_list.getTableHeader().setReorderingAllowed(false);
        tbl_otel_list.getColumnModel().getColumn(0).setMaxWidth(30);
        tbl_otel_list.getColumnModel().getColumn(7).setMaxWidth(50);

        btn_otel_add.addActionListener(e -> {
            if (isEmpty(fld_otel_name) || isEmpty(fld_otel_city) || isEmpty(fld_otel_region) || isEmpty(fld_otel_address) || isEmpty(fld_otel_email) || isEmpty(fld_otel_phone) || isEmpty(fld_otel_star) || isEmpty(fdl_otel_features)) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
            } else {
                Hotel.add(
                        fld_otel_name.getText(),
                        fld_otel_city.getText(),
                        fld_otel_region.getText(),
                        fld_otel_address.getText(),
                        fld_otel_email.getText(),
                        fld_otel_phone.getText(),
                        fld_otel_star.getText(),
                        fdl_otel_features.getText());

                loadHotelModel();
                Helper.clearTextField(fld_otel_name, fld_otel_city, fld_otel_region, fld_otel_address, fld_otel_email, fld_otel_phone, fld_otel_star, fdl_otel_features);
            }

        });
        tbl_otel_list.getSelectionModel().addListSelectionListener(
                e -> {
                    try {
                        String selectedData = tbl_otel_list.getValueAt(tbl_otel_list.getSelectedRow(), 0).toString();
                        fld_otel_id.setText(selectedData);
                        loadLodgingsModel(Integer.parseInt(selectedData));
                        fld_otel_lodging_id.setText(selectedData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                });

        btn_otel_delete.addActionListener(e -> {
            if (isEmpty(fld_otel_id)) {
                JOptionPane.showMessageDialog(null, "Lütfen ID alanını doldurunuz!");
            } else {
                if (Helper.confirm("Emin misiniz?", "UYARI", 0)) {
                    Hotel.delete(Integer.parseInt(fld_otel_id.getText()));

                }
                loadHotelModel();
                Helper.clearTextField(fld_otel_id);
            }

        });

        btn_otel_features.addActionListener(e -> {
            new FeaturesGUI(fdl_otel_features);
        });
        // Otel Paneli
        // Pansiyon Paneli
        model_lodgings_list = new DefaultTableModel();
        Object[] col_lodgings_list = {"ID", "Otel Adi", "Pansiyon Turu"};
        model_lodgings_list.setColumnIdentifiers(col_lodgings_list);
        row_lodgings_list = new Object[col_lodgings_list.length];
        tbl_lodgings_list.setModel(model_lodgings_list);

        loadLodgingsFeaterus();

        btn_lodgins_add.addActionListener(e -> {
            if (isEmpty(fld_otel_lodging_id) || cbm_otel_lodgings_features.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
            } else {
                if (Lodgings.add(
                        fld_otel_lodging_id.getText(),
                        cbm_otel_lodgings_features.getSelectedItem().toString())

                ) {
                    JOptionPane.showMessageDialog(null, "Pansiyon eklendi!", "Bilgi", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Pansiyon eklenemedi!", "Hata", 0);
                }
                loadLodgingsModel(Integer.parseInt(fld_otel_id.getText()));
                Helper.clearTextField(fld_otel_lodging_id);
            }

        });
        tbl_lodgings_list.getSelectionModel().addListSelectionListener(
                e -> {
                    try {
                        String selectedData = tbl_lodgings_list.getValueAt(tbl_lodgings_list.getSelectedRow(), 0).toString();
                        fld_lodgind_id.setText(selectedData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                });


        // Pansiyon Paneli
        btn_lodging_delete.addActionListener(e -> {
            if (isEmpty(fld_lodgind_id)) {
                JOptionPane.showMessageDialog(null, "Lütfen ID alanını doldurunuz!", "UYARI", 0);
            } else {
                if (Helper.confirm("Emin misiniz?", "UYARI", 0)) {
                    if (Lodgings.delete(Integer.parseInt(fld_lodgind_id.getText()))) {
                        JOptionPane.showMessageDialog(null, "Pansiyon silindi!", "Bilgi", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Pansiyon silinemedi!", "Hata", 0);
                    }

                }
                loadLodgingsModel(Integer.parseInt(fld_otel_id.getText()));
                Helper.clearTextField(fld_lodgind_id);
            }

        });

        // Oda Paneli

        model_room_list = new DefaultTableModel();
        Object[] col_room_list = {"ID", "Otel Adı", "Pansiyon Turu", "Oda Adı", "Oda Özellikleri", "Yatak Sayısı", "Metre Kare", "Stok", "Fiyat"};
        model_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomModel();
        tbl_room_list.setModel(model_room_list);

    }

    private void loadRoomModel() {
        DefaultTableModel model_room_list = (DefaultTableModel) tbl_room_list.getModel();
        model_room_list.setRowCount(0);
        //"ID", "Otel Adı", "Pansiyon Turu", "Oda Adı", "Oda Özellikleri", "Yatak Sayısı", "Metre Kare", "Stok", "Fiyat"
        ArrayList<Room> roomArrayList = Room.getList();
        if (!roomArrayList.isEmpty()) {
            for (Room obj : roomArrayList) {
                int i = 0;
                row_room_list[i++] = obj.getId();
                row_room_list[i++] = obj.getHotel().getName();
                row_room_list[i++] = obj.getLodgings().getType();
                row_room_list[i++] = obj.getName();
                row_room_list[i++] = obj.getFeatures();
                row_room_list[i++] = obj.getBed_number();
                row_room_list[i++] = obj.getSqr_meter();
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = obj.getPrice();
                model_room_list.addRow(row_room_list);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bu id'li " + fld_otel_id.getText() + " otelde pansiyon bulunmamaktadır!", "UYARI", 0);
        }
    }

    private void loadLodgingsFeaterus() {
        cbm_otel_lodgings_features.removeAllItems();
        for (Feature feature : Feature.getList("pansiyon ozelligi")) {
            cbm_otel_lodgings_features.addItem(feature.getType());
        }

    }

    private void loadLodgingsModel(int selectedData) {

        DefaultTableModel model_lodgings_list = (DefaultTableModel) tbl_lodgings_list.getModel();
        model_lodgings_list.setRowCount(0);
        ArrayList<Lodgings> lodgingsArrayList = Lodgings.getList(selectedData);
        if (!lodgingsArrayList.isEmpty()) {
            for (Lodgings obj : Lodgings.getList(selectedData)) {
                int i = 0;
                row_lodgings_list[i++] = obj.getId();
                row_lodgings_list[i++] = obj.getOtel_id();
                row_lodgings_list[i++] = obj.getType();
                model_lodgings_list.addRow(row_lodgings_list);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bu id'li " + selectedData + " otelde pansiyon bulunmamaktadır!", "UYARI", 0);
        }

    }


    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_otel_list.getModel();
        clearModel.setRowCount(0);
        //{"ID","Otel Adı","Şehir","Bölge", "Tam Adres", "E-posta", "Telefon", "Yıldız","Tesis Özellikleri"}
        int i = 0;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getCity();
            row_hotel_list[i++] = obj.getRegion();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getEmail();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getFeatures();
            model_otel_list.addRow(row_hotel_list);

        }
    }

    // Otel Paneli


}
