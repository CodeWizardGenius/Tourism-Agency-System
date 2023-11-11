package Model;

import Helper.DBConnector;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private int roomId;
    private int employeeId;
    private String startDate;
    private String endDate;
    private int room_number;

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public static boolean delete(int reservationId) {
        String query = "DELETE FROM reservations WHERE id = " + reservationId;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Reservation getFetch(int reservationId) {
        Reservation reservation = null;
        String query = "SELECT * FROM reservations WHERE id = " + reservationId;
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeQuery(query);
            return reservation;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean add(Reservation reservation) {
        String query = "INSERT INTO reservations (room_id, employee_id, start_date, end_date) VALUES (" +
                reservation.getRoomId() + ", " +
                reservation.getEmployeeId() + ", " +
                reservation.getStartDate() + ", " +
                reservation.getEndDate() + ")";
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reservation(int id ,int roomId, int employeeId, String startDate, String endDate,   int roomNumber) {
        this.id = id;
        this.roomId = roomId;
        this.employeeId = employeeId;
        this.startDate = String.valueOf(startDate);
        this.endDate = endDate;
        this.room_number = roomNumber;
    }

    public static boolean add(int hotel_id,int employeeId, String start_date, String finish_date, int room_no) {
        if (Room.getFetch(String.valueOf(room_no))!=null) {
            Helper.showMessage("Bu oda numarası zaten var", "Hata", 2);
            return false;
        }
        String query = "INSERT INTO reservations (otel_id,employee_id,start_date,end_date,room_number) VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,hotel_id );
            preparedStatement.setInt(2, employeeId);
            preparedStatement.setString(3, start_date);
            preparedStatement.setString(4, finish_date);
            preparedStatement.setInt(5, room_no);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static ArrayList<Reservation> getList() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation reservation;
        String query = "SELECT * FROM reservations";
        try {
            Statement statement = DBConnector.getConnection().createStatement();
            statement.executeQuery(query);

            while (statement.getResultSet().next()) {
                reservation = new Reservation(
                        statement.getResultSet().getInt("id"),
                        statement.getResultSet().getInt("otel_id"),
                        statement.getResultSet().getInt("employee_id"),
                        statement.getResultSet().getString("start_date"),
                        statement.getResultSet().getString("end_date"),
                        statement.getResultSet().getInt("room_number")
                );
                reservations.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
