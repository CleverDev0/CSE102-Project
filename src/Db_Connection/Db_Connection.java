package Db_Connection;

import javax.print.attribute.ResolutionSyntax;
import java.sql.*;

public class Db_Connection {
    //Database related variables
    private static String username = "root";
    private static String password = "12345678";

    private static String connectionString = "jdbc:mysql://localhost:3306/cse_102_project_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection connection;
    private static ResultSet data;


    public static Connection connectiondb() throws Exception {
        try {
             setConnection(DriverManager.getConnection(connectionString, username, password));
            //command.execute("INSERT INTO users (Username,Password,Name,Surname,PhoneNumber,TCNumber,SerialNumber,
            // ApartmentNumber,IsAdmin) Values ('Admin','123','Admin','Admin','5555555555','11111111111','abc123','14',1)");
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Connection Established! QUERY WORKED RIGHT! INSERT ops. Successuful!");
            return null;
        }
    }

    public static boolean ExecuteSql(String s){
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(s);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void CloseConnection() {
        try {
            getConnection().close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet executeQuery(String s){
        try{
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(s);
            return resultSet;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Db_Connection.connection = connection;
    }
}
