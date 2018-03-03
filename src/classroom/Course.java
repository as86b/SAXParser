/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom;

import java.util.ArrayList;

/**
 *
 * @author dale
 */
public class Course {
    private ArrayList<Student> students;
    
    public Course() {
        students = new ArrayList<>();
    }
    
    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
}
