package Helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private Connection connection= null;

    public Connection connection(){
        try{
         this.connection= DriverManager.getConnection(Contanct.DB_URL,Contanct.DB_USERNAME,Contanct.DB_PASSWORD);
        }catch(Exception e){
            System.out.println(e);
        }
        return this.connection;
    }
    public static Connection getConnection(){
        DBConnector connector = new DBConnector();
        return connector.connection();
    }
}
