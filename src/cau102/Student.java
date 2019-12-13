/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cau102;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class Student implements Serializable {
    int Id;
    String code, gpaLetter;
    float gpa;
    
    public Student(String code) {
        this.code = code;
    }
    
    public Student(int Id, String code, float gpa, String gpaLetter) {
        this.Id = Id;
        this.code = code;
        this.gpa = gpa;
        this.gpaLetter = gpaLetter;
    }

    public int getId() {
        return Id;
    }
    
    
    
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getCode() {
        return this.code;
    }

    public String getGpaLetter() {
        return gpaLetter;
    }

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" + "Id=" + Id + ", code=" + code + ", gpaLetter=" + gpaLetter + ", gpa=" + gpa + '}';
    }
}
