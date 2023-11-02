package Model;

import Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Feature {
    private int id;
    private String features;
    private String type;

    public Feature(int id, String features, String type) {
        this.id = id;
        this.features = features;
        this.type = type;
    }

    public static ArrayList<Feature> getList(String type) {
        String query = "SELECT * FROM features WHERE type = '" + type + "'";
        ArrayList<Feature> featureArrayList = new ArrayList<>();
        Feature featureObject;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                featureObject = new Feature(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getString("name")
                );
                featureArrayList.add(featureObject);
            }
            return featureArrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
