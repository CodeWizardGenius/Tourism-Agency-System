package View;

import javax.swing.*;
import java.awt.*;

public class RoomFeaturesGUI extends JFrame {
    private JPanel wrapper;
    private JTable table1;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public RoomFeaturesGUI(JTextField jTextField) throws HeadlessException {
        add(wrapper);
        setSize(500, 500);
        setTitle("Otel Ozellikler");
        setLocationRelativeTo(null); //ekranda ortada açılması için
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
