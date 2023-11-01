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

    public static ArrayList<Lodgings> getList(String otel_id) {
        ArrayList<Lodgings> lodgingsArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.execute(Contanct.LIST_QUERY_PARAMETRE("lodgings","otel_id", Integer.parseInt(otel_id)));
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


        return null;
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
