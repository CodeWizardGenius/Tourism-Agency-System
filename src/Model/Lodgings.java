package Model;

import Helper.Contanct;
import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Lodgings {
    private int id;
    private int otel_id;
    private Hotel hotel;
    private String type;

    public Lodgings(int id, int otel_id, String type) {
        this.id = id;
        this.otel_id = otel_id;
        this.type = type;
    }

    public static ArrayList<Lodgings> getList(int otel_id) {
        ArrayList<Lodgings> lodgingsArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(Contanct.LIST_QUERY_PARAMETRE("lodgings","otel_id", otel_id));
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Lodgings lodgings = new Lodgings(
                        resultSet.getInt("id"),
                        resultSet.getInt("otel_id"),
                        resultSet.getString("type")
                );
                lodgingsArrayList.add(lodgings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lodgingsArrayList;
    }

    public static boolean add(String otel_id, String feature) {
          String query = "INSERT INTO lodgings (otel_id, type) VALUES (" +
                 otel_id + ", '" +
                 feature + "')";
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int lodging_id) {
        String query = Contanct.DELETE_QUERY("lodgings", lodging_id);
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Lodgings getFetch(int lodgingsId) {
        String query = Contanct.FETCH_QUERY("lodgings", lodgingsId);
        Lodgings lodgings = null;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                lodgings = new Lodgings(
                        resultSet.getInt("id"),
                        resultSet.getInt("otel_id"),
                        resultSet.getString("type")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lodgings;
    }
    public static ArrayList<Lodgings> getFetch(String lodgingsType) {
        String query = Contanct.FETCH_QUERY("lodgings", "type", lodgingsType);
        Lodgings lodgings = null;
        ArrayList<Lodgings> lodgingsArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                lodgings = new Lodgings(
                        resultSet.getInt("id"),
                        resultSet.getInt("otel_id"),
                        resultSet.getString("type")
                );
                lodgingsArrayList.add(lodgings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lodgingsArrayList;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
