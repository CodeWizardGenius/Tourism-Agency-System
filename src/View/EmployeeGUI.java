package View;

import Helper.Helper;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static Helper.Helper.isEmpty;

enum RoomName {
    TekKişilik,
    ÇiftKişilik,
    ÜçKişilik,
    DörtKişilik,
    Aile,
    KralDairesi,
    Engelli,
    JuniorSuite,
    Dubleks,
    Suit

}

enum RoomType {
    single, doublee, triple, family, king, disabled, junior, duplex, suit
}

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
    private JTable tbl_room_list_new;
    private JComboBox cmb_room_otel_name;
    private JComboBox cmb_room_lodging_name;
    private JComboBox cmb_room_season_name;
    private JComboBox cbm_room_name;
    private JTextField fld_room_stock;
    private JTextField fld_room_no;
    private JTextField fld_room_area;
    private JComboBox cmb_room_type;
    private JTextField fld_room_adult_price;
    private JTextField fld_room_child_price;
    private JTextField fld_room_features;
    private JButton btn_room_features;
    private JButton btn_room_add;
    private JTextField fld_room_id;
    private JButton btn_room_delete;
    private JTextField fld_season_otel_id;
    private JComboBox cmb_season_name;
    private JTextField fld_start_date;
    private JTextField fld_end_date;
    private JButton btn_season_add;
    private JTable tbl_season_list;
    private JTextField fld_season_id;
    private JButton btn_season_delete;

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
    private ArrayList<Hotel> hotels;
    private DefaultTableModel model_season_list;
    private Object[] row_season_list;


    public EmployeeGUI(Employee employee) {
        this.employee = employee;
        lbl_title.setText("Personel Panel");
        add(wrapper);
        setSize(1500, 1000);
        setTitle("Employee Panel");
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
                        fld_season_otel_id.setText(selectedData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                });

        btn_otel_delete.addActionListener(e -> {
            if (isEmpty(fld_otel_id)) {
                JOptionPane.showMessageDialog(null, "Lütfen ID alanını doldurunuz!");
            } else {
                if (Helper.confirm("Emin misiniz?", "UYARI", 0)) {
                    if (Season.delete(Integer.parseInt(fld_otel_id.getText()))) {
                        if (Hotel.delete(Integer.parseInt(fld_otel_id.getText()))) {
                            // Otel silinince sezonları da sil
                            JOptionPane.showMessageDialog(null, "Otel silindi!", "Bilgi", 1);
                        }else {
                            JOptionPane.showMessageDialog(null, "Otel silinemedi!", "Hata", 0);
                        }
                    } else {
                        if (Helper.confirm("Otele ait sezonlar yok. Silmek istediğinize emin misiniz?", "UYARI", 0)) {
                            if (Hotel.delete(Integer.parseInt(fld_otel_id.getText()))) {
                                // Otel silinince sezonları da sil
                                JOptionPane.showMessageDialog(null, "Otel silindi!", "Bilgi", 1);
                            }else {
                                JOptionPane.showMessageDialog(null, "Otel silinemedi!", "Hata", 0);
                            }
                        }
                    }


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
        Object[] col_room_list = {"ID", "Otel Adı", "Pansiyon Turu", "Donem Adi", "Oda Adı", "Oda Özellikleri", "Yatak Sayısı", "Metre Kare", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı"};
        model_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        tbl_room_list_new.setModel(model_room_list);
        loadRoomModel();

        cmb_room_otel_name.removeAllItems();

        loadComboBoxHotelName();
        loadComboxBoxLodgingsName();

        cmb_room_otel_name.addActionListener(e -> {
            loadComboxBoxLodgingsName();
        });

        loadSeasonName();
        loadRoomName();
        loadRoomType();
        btn_room_features.addActionListener(e -> {
            new RoomFeaturesGUI(fld_room_features);
        });
        btn_room_add.addActionListener(e -> {
            if (isEmpty(fld_room_no) || isEmpty(fld_room_area) || isEmpty(fld_room_stock) || isEmpty(fld_room_adult_price) || isEmpty(fld_room_child_price) || cmb_room_otel_name.getSelectedIndex() == -1 || cmb_room_lodging_name.getSelectedIndex() == -1 || cmb_room_season_name.getSelectedIndex() == -1 || cbm_room_name.getSelectedIndex() == -1 || cmb_room_type.getSelectedIndex() == -1 || isEmpty(fld_room_features)) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
            } else {
                if (Room.add(
                        hotels.get(cmb_room_otel_name.getSelectedIndex()).getId(),
                        cmb_room_lodging_name.getSelectedItem().toString(),
                        cmb_room_season_name.getSelectedItem().toString(),
                        cbm_room_name.getSelectedItem().toString(),
                        cmb_room_type.getSelectedItem().toString(),
                        fld_room_no.getText(),
                        fld_room_features.getText(),
                        fld_room_area.getText(),
                        fld_room_stock.getText(),
                        fld_room_adult_price.getText(),
                        fld_room_child_price.getText()
                )) {
                    JOptionPane.showMessageDialog(null, "Oda eklendi!", "Bilgi", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Oda eklenemedi!", "Hata", 0);
                }
                loadRoomModel();
                Helper.clearTextField(fld_room_no, fld_room_area, fld_room_stock, fld_room_adult_price, fld_room_child_price, fld_room_features);
            }

        });
        tbl_room_list_new.getSelectionModel().addListSelectionListener(
                e -> {
                    try {
                        String selectedData = tbl_room_list_new.getValueAt(tbl_room_list_new.getSelectedRow(), 0).toString();
                        fld_room_id.setText(selectedData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                });
        btn_room_delete.addActionListener(e -> {
            if (isEmpty(fld_room_id)) {
                JOptionPane.showMessageDialog(null, "Lütfen ID alanını doldurunuz!", "UYARI", 0);
            } else {
                if (Helper.confirm("Emin misiniz?", "UYARI", 0)) {
                    if (Room.delete(Integer.parseInt(fld_room_id.getText()))) {
                        JOptionPane.showMessageDialog(null, "Oda silindi!", "Bilgi", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Oda silinemedi!", "Hata", 0);
                    }

                }
                loadRoomModel();
                Helper.clearTextField(fld_room_id);
            }
        });

        //Sezon
        model_season_list = new DefaultTableModel();
        Object[] col_season_list = {"ID", "Otel Adı", "Donem Adi", "Baslangic Tarihi", "Bitis Tarihi"};
        model_season_list.setColumnIdentifiers(col_season_list);
        row_season_list = new Object[col_season_list.length];
        tbl_season_list.setModel(model_season_list);
        loadSeasonModel();

        tbl_season_list.getSelectionModel().addListSelectionListener(
                e -> {
                    try {
                        String selectedData = tbl_season_list.getValueAt(tbl_season_list.getSelectedRow(), 0).toString();
                        fld_season_id.setText(selectedData);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                });

        loadComboxSeasonName();
        btn_season_add.addActionListener(e -> {
            if (isEmpty(fld_season_otel_id) || cmb_season_name.getSelectedIndex() == -1 || isEmpty(fld_start_date) || isEmpty(fld_end_date)) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
            } else {
                if (Season.add(
                        Integer.parseInt(fld_season_otel_id.getText()),
                        fld_start_date.getText(),
                        fld_end_date.getText(),
                        cmb_season_name.getSelectedItem().toString()
                )) {
                    JOptionPane.showMessageDialog(null, "Sezon eklendi!", "Bilgi", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Sezon eklenemedi!", "Hata", 0);
                }
                loadSeasonModel();
                Helper.clearTextField(fld_season_otel_id, fld_start_date, fld_end_date);
            }

        });
        btn_season_delete.addActionListener(e -> {
            if (isEmpty(fld_season_id)) {
                JOptionPane.showMessageDialog(null, "Lütfen ID alanını doldurunuz!", "UYARI", 0);
            } else {
                if (Helper.confirm("Emin misiniz?", "UYARI", 0)) {
                    if (Season.delete(Integer.parseInt(fld_season_id.getText()))) {
                        JOptionPane.showMessageDialog(null, "Sezon silindi!", "Bilgi", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sezon silinemedi!", "Hata", 0);
                    }

                }
                loadSeasonModel();
                Helper.clearTextField(fld_season_id);
            }

        });
    }

    private void loadComboxSeasonName() {
        cmb_season_name.removeAllItems();
        for (Feature feature : Feature.getList("sezon ozellikleri")) {
            cmb_season_name.addItem(feature.getType());
        }
    }

    private void loadSeasonModel() {
        DefaultTableModel model_season_clear = (DefaultTableModel) tbl_season_list.getModel();
        model_season_clear.setRowCount(0);
        ArrayList<Season> seasonArrayList = Season.getList();
        if (!seasonArrayList.isEmpty()) {
            for (Season obj : Season.getList()) {
                int i = 0;
                row_season_list[i++] = obj.getId();
                row_season_list[i++] = obj.getHotel().getName();
                row_season_list[i++] = obj.getName();
                row_season_list[i++] = obj.getStart_date();
                row_season_list[i++] = obj.getEnd_date();
                model_season_clear.addRow(row_season_list);
            }

        }
    }

    private void loadRoomType() {
        cmb_room_type.removeAllItems();
        for (RoomType roomType : RoomType.values()) {
            cmb_room_type.addItem(roomType);
        }
    }

    private void loadRoomName() {
        cbm_room_name.removeAllItems();
        for (RoomName roomName : RoomName.values()) {
            cbm_room_name.addItem(roomName + " Oda");
        }
    }

    private void loadSeasonName() {
        cmb_room_season_name.removeAllItems();
        for (Feature feature : Feature.getList("sezon ozellikleri")) {
            cmb_room_season_name.addItem(feature.getType());
        }
    }


    private void loadComboxBoxLodgingsName() {
        cmb_room_lodging_name.removeAllItems();
        System.out.println(cmb_room_otel_name.getSelectedIndex());
        for (Lodgings lodgings : Lodgings.getList(hotels.get(cmb_room_otel_name.getSelectedIndex()).getId())) {
            cmb_room_lodging_name.addItem(lodgings.getType());
            System.out.println(lodgings.getType());
        }
    }

    private void loadComboBoxHotelName() {
        cmb_room_otel_name.removeAllItems();
        hotels = Hotel.getList();
        for (Hotel hotel : hotels) {
            cmb_room_otel_name.addItem(hotel.getName());
        }
    }

    private void loadRoomModel() {
        DefaultTableModel model_room_clear = (DefaultTableModel) tbl_room_list_new.getModel();
        model_room_clear.setRowCount(0);
        //"ID", "Otel Adı", "Pansiyon Turu","Donem Adi", "Oda Adı", "Oda Özellikleri", "Yatak Sayısı", "Metre Kare", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı"
        ArrayList<Room> roomArrayList = Room.getList();
        if (!roomArrayList.isEmpty()) {
            for (Room obj : roomArrayList) {
                int i = 0;
                row_room_list[i++] = obj.getId();
                row_room_list[i++] = obj.getHotel().getName();
                row_room_list[i++] = obj.getLodgings().getType();
                row_room_list[i++] = obj.getSeason().getName();
                row_room_list[i++] = obj.getName();
                row_room_list[i++] = obj.getFeatures();
                row_room_list[i++] = obj.getBed_number();
                row_room_list[i++] = obj.getSqr_meter();
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = obj.getPrice_adult() + " TL";
                row_room_list[i++] = obj.getPrice_child() + " TL";
                model_room_clear.addRow(row_room_list);
            }

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

    public static void main(String[] args) {
        Helper.setLayout();
        EmployeeGUI employeeGUI = new EmployeeGUI(new Employee());
    }


}
