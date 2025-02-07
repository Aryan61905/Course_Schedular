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

public class ScheduleQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    
    private static ResultSet resultstudentID;
    private static ResultSet resultgetScheduleByStudent;
    private static ResultSet resultgetScheduleStudentCount;
    private static ResultSet resultgetScheduledStudentsByCourse;
    private static ResultSet resultgetWaitlistedStudentsByCourse;
    
    
    
    
    

    public static  void addScheduleEntry(ScheduleEntry entry){
    connection = DBConnection.getConnection();
    
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule(semester,studentid,coursecode,status,timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(3, entry.getCoursecode());
            addScheduleEntry.setString(4, entry.getS());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
}
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String Semester,String studentID){
    connection = DBConnection.getConnection();
    ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester,studentID,CourseCode,status,timestamp from app.schedule where semester=(?) and studentID=(?)");
            getScheduleByStudent.setString(1, Semester);
            getScheduleByStudent.setString(2, studentID);
            resultgetScheduleByStudent = getScheduleByStudent.executeQuery();
            while(resultgetScheduleByStudent.next()){
                
                ScheduleEntry scheduleentry = new ScheduleEntry(resultgetScheduleByStudent.getString(1),resultgetScheduleByStudent.getString(3),resultgetScheduleByStudent.getString(2),resultgetScheduleByStudent.getString(4),resultgetScheduleByStudent.getTimestamp(5));
                
             schedule.add(scheduleentry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
        
    
}
    public static int getScheduledStudentCount(String Semester,String courseCode){
    connection = DBConnection.getConnection();
    int studentcount=0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedule where semester=(?) and courseCode = (?)");
            getScheduledStudentCount.setString(1, Semester);
            getScheduledStudentCount.setString(2, courseCode);
            resultgetScheduleStudentCount=getScheduledStudentCount.executeQuery();
            
            while(resultgetScheduleStudentCount.next()){
                
                studentcount=resultgetScheduleStudentCount.getInt(1);
            }
            
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentcount;
}
    
public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String Semester,String courseCode){
    connection = DBConnection.getConnection();
    
    ArrayList<ScheduleEntry> scheduledstudents = new ArrayList<>();
        try
        {  
            getScheduledStudentsByCourse = connection.prepareStatement("select * from app.schedule where semester=(?) and coursecode=(?) and status = (?) order by timestamp ");
            getScheduledStudentsByCourse.setString(1,Semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            getScheduledStudentsByCourse.setString(3, "Scheduled");
            resultgetScheduledStudentsByCourse=getScheduledStudentsByCourse.executeQuery();
            
            while(resultgetScheduledStudentsByCourse.next()){
            
            ScheduleEntry obj = new ScheduleEntry(resultgetScheduledStudentsByCourse.getString(1),resultgetScheduledStudentsByCourse.getString(3),resultgetScheduledStudentsByCourse.getString(2),resultgetScheduledStudentsByCourse.getString(4),resultgetScheduledStudentsByCourse.getTimestamp("Timestamp"));
            
              scheduledstudents.add(obj);  
                
            }}
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return scheduledstudents;
}
  public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String Semester,String courseCode){
    connection = DBConnection.getConnection();
    
    ArrayList<ScheduleEntry> waitlistedstudents = new ArrayList<>();
        try
        {  
            getWaitlistedStudentsByCourse = connection.prepareStatement("select * from app.schedule where semester=(?) and coursecode=(?) and status = (?) order by timestamp ");
            getWaitlistedStudentsByCourse.setString(1,Semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            getWaitlistedStudentsByCourse.setString(3, "Waitlisted");
            resultgetWaitlistedStudentsByCourse=getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultgetWaitlistedStudentsByCourse.next()){
           
            ScheduleEntry obj = new ScheduleEntry(resultgetWaitlistedStudentsByCourse.getString(1),resultgetWaitlistedStudentsByCourse.getString(3),resultgetWaitlistedStudentsByCourse.getString(2),resultgetWaitlistedStudentsByCourse.getString(5),resultgetWaitlistedStudentsByCourse.getTimestamp("Timestamp"));
            
              waitlistedstudents.add(obj);  
                
            }}
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return waitlistedstudents;
}
  public static void dropStudentScheduleByCourse(String Semester,String studentID, String courseCode){
    connection = DBConnection.getConnection();
    
    
        try
        {  
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester=(?) and studentID=(?) and coursecode=(?) ");
            dropStudentScheduleByCourse.setString(1, Semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();
            }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
  }
  
  public static void dropScheduleByCourse(String Semester, String courseCode){
    connection = DBConnection.getConnection();
    
    
        try
        {  
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester=(?) and coursecode=(?)");
            dropScheduleByCourse.setString(1, Semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
            }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
  }
  
  
  public static  void updateScheduleEntry(String Semester,ScheduleEntry entry){
    connection = DBConnection.getConnection();
    
        try
        {
            updateScheduleEntry = connection.prepareStatement("update app.schedule set status = ? where semester = ? and studentID = ? and coursecode = ?");
            updateScheduleEntry.setString(1,"Scheduled");
            updateScheduleEntry.setString(2,Semester);
            updateScheduleEntry.setString(3,entry.getStudentID());
            updateScheduleEntry.setString(4,entry.getCoursecode());
            updateScheduleEntry.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
  
  
}}

