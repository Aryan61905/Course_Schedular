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

public class StudentQueries {
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement getAllStudents;
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultgetAllStudents;
    private static ResultSet resultgetStudent;
    ;
   
    
    public static  void addStudent(StudentEntry student){
    connection = DBConnection.getConnection();
    
        try
        {
            addStudent = connection.prepareStatement("insert into app.student(studentID,firstName,lastName) values (?,?,?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            
            addStudent.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
    }
    public static ArrayList<StudentEntry> getAllStudents(){
    connection = DBConnection.getConnection();
    ArrayList<StudentEntry> allStudents = new ArrayList<>();
        try
        {
            getAllStudents = connection.prepareStatement("select * from app.student order by firstName" );
            
            
            resultgetAllStudents = getAllStudents.executeQuery();
            while(resultgetAllStudents.next()){
               StudentEntry student = new StudentEntry(resultgetAllStudents.getString(1), resultgetAllStudents.getString(2), resultgetAllStudents.getString(3));
                allStudents.add(student);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allStudents;
}   
    public static StudentEntry getStudent(String studentID){
    connection = DBConnection.getConnection();
    
        try
        {
         getStudent = connection.prepareStatement("select * from app.student where studentID= (?)" );
         getStudent.setString(1,studentID);
        resultgetStudent=getStudent.executeQuery();  
        while(resultgetStudent.next()){
            StudentEntry obj = new StudentEntry(resultgetStudent.getString(1),resultgetStudent.getString(2),resultgetStudent.getString(3));
            return obj;
        }
        
        }
        
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
       return null;
}
    
    public static  void dropStudent(String studentID){
    connection = DBConnection.getConnection();
    
        try
        {  
          
            dropStudent = connection.prepareStatement("delete from app.student where studentID = (?)");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
}
}