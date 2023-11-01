package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private int id;
    private String username;
    private String password;
    private String type;
    private String name;

    public User(int id, String username, String password, String type, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.name = name;
    }

    public User() {
    }

    public static User getCredentials(String username, String password) {
        User user = null;
        String query = Contanct.SELECT_USER;
        try {
            PreparedStatement pr = DBConnector.getConnection().prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                switch (rs.getString("type")) {
                    case "admin":
                        user = new Admin();
                        break;
                    case "employee":
                        user = new Employee();
                        break;
                    default:
                        System.out.println("Kullanıcı tipi hatalı!");
                        break;
                }
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getString("type"));
                user.setName(rs.getString("name"));
            }
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
