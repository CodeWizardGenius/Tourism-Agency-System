package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String region;
    private String city;
    private String address;
    private String email;
    private String phone;
    private int star;
    private String features;


    public Hotel(int id, String name, String region, String city, String address, String email, String phone, int star, String features) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.features = features;
    }

    public static ArrayList<Hotel> getList() {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        Hotel patikaObject;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Contanct.LIST_QUERY("otel"));
            while (resultSet.next()) {
                patikaObject = new Hotel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("region"),
                        resultSet.getString("city"),
                        resultSet.getString("address"),
                        resultSet.getString("e-mail"),
                        resultSet.getString("phone"),
                        resultSet.getInt("star"),
                        resultSet.getString("features")
                );
                hotelArrayList.add(patikaObject);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelArrayList;
    }

    public static boolean add(String... data) {
        boolean isSuccess = false;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            isSuccess = statement.execute(Contanct.ADD_QUERY("otel", data));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccess;
    }

    public static void delete(int otel_id) {
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(Contanct.DELETE_QUERY("otel", otel_id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
