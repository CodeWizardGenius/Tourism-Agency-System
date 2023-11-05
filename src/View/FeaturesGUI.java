package View;

import Model.Feature;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeaturesGUI extends JFrame {
    private JPanel wrapper;
    private JTable tbl_availables;
    private JButton btn_add;
    private JButton btn_delete;
    private JTable tbl_selected;
    private JPanel pnl_availables;
    private JButton btn_submit;
    private DefaultTableModel model_otel_features;
    private Object[] row_hotel_features;
    private DefaultTableModel model_otel_selected;


    public FeaturesGUI(JTextField fdl_otel_features) {
        add(wrapper);
        setSize(800, 500);
        setTitle("Otel Ozellikleri");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        model_otel_features = new DefaultTableModel();
        model_otel_selected = new DefaultTableModel();
        Object[] col_hotel_features = {"ID", "Otel Ozellikleri"};
        row_hotel_features = new Object[col_hotel_features.length];
        model_otel_features.setColumnIdentifiers(col_hotel_features);
        model_otel_selected.setColumnIdentifiers(col_hotel_features);
        tbl_availables.setModel(model_otel_features);
        tbl_selected.setModel(model_otel_selected);
        loadAvailables();

        btn_submit.addActionListener(e -> {
            for (int i = 0; i < model_otel_selected.getRowCount(); i++) {
                if (i == 0) {
                    fdl_otel_features.setText(model_otel_selected.getValueAt(i, 1).toString());
                    continue;
                }
                fdl_otel_features.setText(fdl_otel_features.getText() + ", " + model_otel_selected.getValueAt(i, 1).toString());
            }
            dispose();

        });
        btn_add.addActionListener(e -> {
            int selectedRow = tbl_availables.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            int id = (int) model_otel_features.getValueAt(selectedRow, 0);
            String features = (String) model_otel_features.getValueAt(selectedRow, 1);
            model_otel_features.removeRow(selectedRow);
            Object[] row = {id, features};
            model_otel_selected.addRow(row);
        });
        btn_delete.addActionListener(e -> {
            int selectedRow = tbl_selected.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            int id = (int) model_otel_selected.getValueAt(selectedRow, 0);
            String features = (String) model_otel_selected.getValueAt(selectedRow, 1);
            model_otel_selected.removeRow(selectedRow);
            Object[] row = {id, features};
            model_otel_features.addRow(row);

        });
    }

    private void loadAvailables() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_availables.getModel();
        clearModel.setRowCount(0);
        int i ;
        for (Feature feature : Feature.getList("tesis ozelligi")) {
            i = 0;
            row_hotel_features[i++] = feature.getId();
            row_hotel_features[i++] = feature.getType();
            model_otel_features.addRow(row_hotel_features);
        }
    }
}
