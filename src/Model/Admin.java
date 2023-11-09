package Model;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.Statement;
import java.util.ArrayList;

public class Admin extends User {

    public boolean addUser(String username, String password, String type, String name) {
        String query = "INSERT INTO tourism_agency_user (username, password, type, name) VALUES ('" + username + "', '" + password + "', '" + type + "', '" + name + "')";
        User user =Admin.getFetchUser(username);
        if (user != null) {
            Helper.showMessage("Bu kullanıcı adı zaten kullanılıyor.", "Hata", 2);
            return false;
        }
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static User getFetchUser(String username) {
        String query = "SELECT * FROM tourism_agency_user WHERE username = '" + username + "'";
        User obj = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            var resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                obj = new Admin();
                obj.setId(resultSet.getInt("id"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setType(resultSet.getString("type"));
                obj.setName(resultSet.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            String query = "SELECT * FROM tourism_agency_user";
            var resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Admin();
                obj.setId(resultSet.getInt("id"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setType(resultSet.getString("type"));
                obj.setName(resultSet.getString("name"));
                userList.add(obj);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public boolean updateUser(String text, String text1, String string, String text2) {
        String query = "UPDATE tourism_agency_user  SET  password = '" + text1 + "', type = '" + string + "', name = '" + text2 + "' WHERE username = '" + text + "'";
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String text) {
        String query = "DELETE FROM tourism_agency_user WHERE username = '" + text + "'";
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
