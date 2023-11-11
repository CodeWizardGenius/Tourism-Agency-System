package Model;

import Helper.DBConnector;

import java.sql.Statement;

public class Reservation {
    private int id;
    private int roomId;
    private int employeeId;
    private String startDate;
    private String endDate;
    public static boolean delete(int reservationId) {
        String query = "DELETE FROM reservations WHERE id = " + reservationId;
        try {
            Statement statement= DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static Reservation getFetch(int reservationId) {
        Reservation reservation = null;
        String query = "SELECT * FROM reservations WHERE id = " + reservationId;
        try {
            Statement statement= DBConnector.getConnection().createStatement();
            statement.executeQuery(query);
            return reservation;
        }catch (Exception e){
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
            Statement statement= DBConnector.getConnection().createStatement();
            statement.executeUpdate(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Reservation(int id, int roomId, int employeeId, String startDate, String endDate) {
        this.id = id;
        this.roomId = roomId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
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
