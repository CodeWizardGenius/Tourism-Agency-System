package Helper;

import javax.swing.*;

public class Helper {
    public static void showMessage(String message, String title, int type) {
        optionPageTR();
        JOptionPane.showMessageDialog(null, message, title, type);
    }
    public static void optionPageTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.cancelButtonText", "Iptal");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayir");
        UIManager.put("OptionPane.inputDialogTitle", "Giris");
    }
    public static boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }


}
