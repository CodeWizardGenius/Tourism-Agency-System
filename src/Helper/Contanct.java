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
    public static String FETCH_QUERY(String table,String variable, String value) {
       return "SELECT * FROM " + table + " WHERE " + variable + " = '" + value + "'";
    }

    public static String SELECT_QUERY_WHERE(String season, int seasonId) {
        return "SELECT * FROM " + season + " WHERE id = " + seasonId;
    }

    public static String SELECT_QUERY(String season) {
        return "SELECT * FROM " + season;
    }

    public static String INSERT_QUERY(String season, int id, String string, String text, String text1) {
        return "INSERT INTO " + season + " ( `otel_id`, `start_date`, `end_date`, `name`) VALUES ('" + id + "', '" + string + "', '" + text + "', '" + text1 + "')";
    }
    public static String INSERT_QUERY(String room, int id, String string, String string1, String string2, String string3, String text, String text1, String text2, String text3, String text4, String text5) {
        return "INSERT INTO " + room + " ( `otel_id`, `lodgings_id`, `season_id`, `features`, `name`, `stock`, `bed_number`, `sqr_meter`, `price_adult`, `price_child`) VALUES ('" + id + "', '" + string + "', '" + string1 + "', '" + string2 + "', '" + string3 + "', '" + text + "', '" + text1 + "', '" + text2 + "', '" + text3 + "', '" + text4 + "', '" + text5 + "')";
    }


    public static String DELETE_QUERY_WHERE(String season, String otelId, int hotelId) {
        return "DELETE FROM " + season + " WHERE " + otelId + " = " + hotelId;
    }

    public static String INSERT_QUERY(String room, int id, int id1, int id2, String text, String string, String text1, String text2, String text3, String string1, String text4, String text5) {
        return "INSERT INTO " + room + " ( `otel_id`, `lodgings_id`, `season_id`, `features`, `name`, `stock`, `bed_number`, `sqr_meter`, `price_adult`, `price_child`) VALUES ('" + id + "', '" + id1 + "', '" + id2 + "', '" + text + "', '" + string + "', '" + text1 + "', '" + text2 + "', '" + text3 + "', '" + string1 + "', '" + text4 + "', '" + text5 + "')";

    }
}
