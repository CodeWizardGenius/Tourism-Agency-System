package Helper;

public class Contanct {
    public static final String DB_NAME = "u621395494_tourism_agency";
    public static final String DB_URL = "jdbc:mysql://193.203.168.8:3306/"+DB_NAME;
    public static final String DB_USERNAME = "u621395494_patika";
    public static final String DB_PASSWORD = "04603Sb312.";
    public static final String SELECT_USER = "SELECT * FROM tourism_agency_user WHERE username = ? AND password = ?";

    public static String LIST_QUERY(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String ADD_QUERY(String otel, String[] data) {
        return "INSERT INTO " + otel + " (`name`, `region`, `city`, `address`, `e-mail`, `phone`, `star`, `features`) VALUES ('" + data[0] + "', '" + data[1] + "', '" + data[2] + "', '" + data[3] + "', '" + data[4] + "', '" + data[5] + "', '" + Integer.parseInt(data[6]) + "', '" + data[7] + "')";
    }

    public static String DELETE_QUERY(String table, int id) {
        return "DELETE FROM " + table + " WHERE id = " + id;
    }

    public static String LIST_QUERY_PARAMETRE(String table, String parametre, int value) {
        return "SELECT * FROM " + table + " WHERE " + parametre + " = " + value;
    }

    public static String FETCH_QUERY(String lodgings, int lodgingsId) {
        return "SELECT * FROM " + lodgings + " WHERE id = " + lodgingsId;
    }

    public static String SELECT_QUERY(String season, int seasonId) {
        return "SELECT * FROM " + season + " WHERE id = " + seasonId;
    }
}
