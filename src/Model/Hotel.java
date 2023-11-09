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
        Hotel hotel = Hotel.getFetchOtel(data[4]);
        if (hotel != null) {
            Helper.Helper.showMessage("Bu otel zaten var", "Hata", 2);
            return false;
        }
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            isSuccess = statement.execute(Contanct.ADD_QUERY("otel", data));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccess;
    }

    private static Hotel getFetchOtel(String email) {
        String query = "SELECT * FROM otel WHERE `e-mail` = '" + email + "'";
        Hotel hotel = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                hotel = new Hotel(
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotel;
    }

    public static boolean delete(int otel_id) {
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(Contanct.DELETE_QUERY("otel", otel_id));
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static Hotel getFetch(int otelId) {
        String query = Contanct.FETCH_QUERY("otel", otelId);
        Hotel hotel = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                hotel = new Hotel(
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotel;
    }

    public static Hotel getFetch(String otel_name) {
        String query = "SELECT * FROM otel WHERE name = '" + otel_name + "'";
        Hotel hotel = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                hotel = new Hotel(
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotel;
    }

    public static ArrayList<Hotel> search(String search, String searchValue) {
        String query = "";
        if (searchValue.equals("Otel")) {
            query = "SELECT * FROM otel WHERE name = '" + search + "'";
        } else if (searchValue.equals("Sehir")) {
            query = "SELECT * FROM otel WHERE city = '" + search + "'";
        } else if (searchValue.equals("Bolge")) {
            query = "SELECT * FROM otel WHERE region = '" + search + "'";
        }

        Hotel hotel = null;
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                hotel = new Hotel(
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
                hotelArrayList.add(hotel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelArrayList;
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
