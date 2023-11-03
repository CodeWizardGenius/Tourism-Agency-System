package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Season {
    private int id;
    private int otel_id;
    private Hotel hotel;
    private String start_date;
    private String end_date;

    public Season(int id, int otel_id, String start_date, String end_date) {
        this.id = id;
        this.otel_id = otel_id;
        this.start_date = start_date;
        this.end_date = end_date;
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
        String query = Contanct.SELECT_QUERY("season", seasonId);
        Season season = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            season = new Season(
                    resultSet.getInt("id"),
                    resultSet.getInt("otel_id"),
                    resultSet.getString("start_date"),
                    resultSet.getString("end_date")
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return season;
    }
}
