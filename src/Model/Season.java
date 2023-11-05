package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {
    private int id;
    private String name;
    private int otel_id;
    private Hotel hotel;
    private String start_date;
    private String end_date;

    public Season(int id, int otel_id, String start_date, String end_date, String name) {
        this.id = id;
        this.otel_id = otel_id;
        this.hotel = Hotel.getFetch(otel_id);
        this.start_date = start_date;
        this.end_date = end_date;
        this.name = name;
    }

    public static ArrayList<Season> getList() {
        String query = Contanct.SELECT_QUERY("season");
        ArrayList<Season> list = new ArrayList<>();
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Season season = new Season(
                        resultSet.getInt("id"),
                        resultSet.getInt("otel_id"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date"),
                        resultSet.getString("name")
                );
                list.add(season);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static boolean add(int id, String string, String text, String text1) {
        System.out.println(id + " " + string + " " + text + " " + text1);
        String query = Contanct.INSERT_QUERY("season", id, string, text, text1);
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int i) {
        String query = Contanct.DELETE_QUERY("season", i);
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public static boolean deleteByHotelID(int hotel_id){
        String query = Contanct.DELETE_QUERY_WHERE("season", "otel_id", hotel_id);
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOtel_id() {
        return otel_id;
    }

    public void setOtel_id(int otel_id) {
        this.otel_id = otel_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static Season getFetch(int seasonId) {
        String query = Contanct.SELECT_QUERY_WHERE("season", seasonId);
        Season season = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            season = new Season(
                    resultSet.getInt("id"),
                    resultSet.getInt("otel_id"),
                    resultSet.getString("start_date"),
                    resultSet.getString("end_date"),
                    resultSet.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return season;
    }
    public static Season getFetch(String otel_id) {
        String query = "SELECT * FROM season WHERE otel_id = " + otel_id;
        Season season = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            season = new Season(
                    resultSet.getInt("id"),
                    resultSet.getInt("otel_id"),
                    resultSet.getString("start_date"),
                    resultSet.getString("end_date"),
                    resultSet.getString("name")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return season;
    }
}
