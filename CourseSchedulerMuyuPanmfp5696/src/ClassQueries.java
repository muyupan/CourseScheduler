/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author muyupan
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static ResultSet resultSet;
    
    public static void addClass(ClassEntry classes)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class (semester, coursecode, seats) values (?, ?, ?)");
            addClass.setString(1, classes.getSemester());
            addClass.setString(2, classes.getCourseCode());
            addClass.setInt(3, classes.getSeats());
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> codes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.course order by coursecode");
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                codes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return codes;
        
    }
    
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int seats = -1;
        try
        {
            getClassSeats = connection.prepareStatement("select seats from app.class where semester = ? and coursecode = ?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
               if (resultSet.next()) {
            seats = resultSet.getInt("seats");  // Correctly retrieve the integer value from the ResultSet
        }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
        
    }
    
    
}
