/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom;

/**
 *
 * @author dale
 */
public class Student {
    private String pawprint = "";
    private String firstName = "";
    private String lastName = "";
    private double grade  = 0.0;
    private int id = 0;
    
    public Student() {
        
    }
    
    public Student(int id, String pawprint, String firstName, String lastName, double grade) {
        this.id = id;
        this.pawprint = pawprint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPawprint(String pawprint) {
        this.pawprint = pawprint;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    public int getId() {
        return id;
    }
    
    public String getPawprint() {
        return pawprint;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public double getGrade() {
        return grade;
    }
}
