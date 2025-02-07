/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aryan
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultgetAllCourses;
    private static ResultSet resultgetAllCourseCodes;
    private static ResultSet resultgetCourseSeats;
    private static ResultSet resultgetAllSchedules;
    private static int schedulecount;
    
    
    
    public static ArrayList<CourseEntry> getAllCourses(String Semester){
    connection = DBConnection.getConnection();
    ArrayList<CourseEntry> allCourses = new ArrayList<>();
        try
        {
            getAllCourses = connection.prepareStatement("select Semester, CourseCode,Description,Seats from app.course "+"  where semester = ?  order by CourseCode");
            getAllCourses.setString(1, Semester);
            
            resultgetAllCourses = getAllCourses.executeQuery();
            while(resultgetAllCourses.next()){
                CourseEntry course = new CourseEntry(resultgetAllCourses.getString(1), resultgetAllCourses.getString(2), resultgetAllCourses.getString(3), resultgetAllCourses.getInt(4));
                allCourses.add(course);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allCourses;
}   
    
    public static  void addCourse(CourseEntry course){
    connection = DBConnection.getConnection();
    
        try
        {
            addCourse = connection.prepareStatement("insert into app.course(semester,courseCode,description,seats) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getCourseDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
}
    public static ArrayList<String> getAllCourseCodes(String Semester){
    connection = DBConnection.getConnection();
    ArrayList<String> allCourseCodes = new ArrayList<>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select CourseCode from app.course where Semester = ? ");
            getAllCourseCodes.setString(1, Semester);
            
            resultgetAllCourseCodes = getAllCourseCodes.executeQuery();
            while(resultgetAllCourseCodes.next()){
                
                allCourseCodes.add(resultgetAllCourseCodes.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allCourseCodes;
        
    
}
    public static int getCourseSeats(String Semester, String allCourseCodes){
    connection = DBConnection.getConnection();
    int seats =0;
    
        try
        {
            getCourseSeats = connection.prepareStatement("select Seats from app.course where semester=(?) and CourseCode = (?)");
            getCourseSeats.setString(1, Semester);
            getCourseSeats.setString(2, allCourseCodes);
            resultgetCourseSeats=getCourseSeats.executeQuery();
            
            while(resultgetCourseSeats.next()){
                
                seats=resultgetCourseSeats.getInt(1);
            }
            
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
    
}
    
    public static  void dropCourse(String Semester,String courseCode){
    connection = DBConnection.getConnection();
    
        try
        {  
            
            
            
           
            dropCourse = connection.prepareStatement("delete from app.course where semester = (?) and courseCode = (?) " );
            dropCourse.setString(1,Semester);
            dropCourse.setString(2, courseCode);
            dropCourse.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
}}
