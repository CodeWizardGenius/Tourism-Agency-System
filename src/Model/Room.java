package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private int id;
    private int otel_id;
    private Hotel hotel;
    private int lodgings_id;
    private Lodgings lodgings;
    private int season_id;
    private Season season;
    private int price;
    private String features;
    private String name;
    private int stock;
    private int bed_number;
    private int sqr_meter;

    public Room(int id, int otel_id, int lodgings_id, int season_id, int price, String features, String name, int stock, int bed_number, int sqr_meter) {
        this.id = id;
        this.otel_id = otel_id;
        this.hotel = Hotel.getFetch(otel_id);
        this.lodgings_id = lodgings_id;
        this.lodgings = Lodgings.getFetch(lodgings_id);
        this.season_id = season_id;
        this.season = Season.getFetch(season_id);
        this.price = price;
        this.features = features;
        this.name = name;
        this.stock = stock;
        this.bed_number = bed_number;
        this.sqr_meter = sqr_meter;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Lodgings getLodgings() {
        return lodgings;
    }

    public void setLodgings(Lodgings lodgings) {
        this.lodgings = lodgings;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public static ArrayList<Room> getList() {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        Room roomObject = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(Contanct.LIST_QUERY("room"));
            while (resultSet.next()) {
                roomObject = new Room(
                        resultSet.getInt("id"),
                        resultSet.getInt("otel_id"),
                        resultSet.getInt("lodgings_id"),
                        resultSet.getInt("season_id"),
                        resultSet.getInt("price"),
                        resultSet.getString("features"),
                        resultSet.getString("name"),
                        resultSet.getInt("stock"),
                        resultSet.getInt("bed_number"),
                        resultSet.getInt("sqr_meter")
                );
                roomArrayList.add(roomObject);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return roomArrayList;
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

    public int getLodgings_id() {
        return lodgings_id;
    }

    public void setLodgings_id(int lodgings_id) {
        this.lodgings_id = lodgings_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBed_number() {
        return bed_number;
    }

    public void setBed_number(int bed_number) {
        this.bed_number = bed_number;
    }

    public int getSqr_meter() {
        return sqr_meter;
    }

    public void setSqr_meter(int sqr_meter) {
        this.sqr_meter = sqr_meter;
    }
}
