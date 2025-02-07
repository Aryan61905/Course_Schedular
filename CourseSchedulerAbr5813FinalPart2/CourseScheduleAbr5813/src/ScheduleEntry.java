
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aryan
 */
public class ScheduleEntry {
    private String Semester;
    private String Coursecode;
    private String StudentID;
    private String S;
    private Timestamp Timestamp;

    public ScheduleEntry(String Semester, String Coursecode, String StudentID, String S, Timestamp Timestamp) {
        this.Semester = Semester;
        this.Coursecode = Coursecode;
        this.StudentID = StudentID;
        this.S = S;
        this.Timestamp = Timestamp;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCoursecode() {
        return Coursecode;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getS() {
        return S;
    }

    public Timestamp getTimestamp() {
        return Timestamp;
    }
    
    
    
}
