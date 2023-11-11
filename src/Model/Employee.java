package Model;

import Helper.DBConnector;

import java.sql.Statement;

public class Employee extends User {
    public static Room getFetch(String userName) {
        Room room = null;
        String query = "SELECT * FROM tourism_agency_user WHERE username = " + "'" + userName + "'";
        try {
            Statement statement= DBConnector.getConnection().createStatement();
            statement.executeQuery(query);
            return room;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
