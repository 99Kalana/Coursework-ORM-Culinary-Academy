package org.example.coursework_orm.entity;

import jakarta.persistence.*;

@Entity
public class Admin {

    @Id
    private String adminUserID;

    private String adminUsername;

    private String adminPassword;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

    public Admin() {
    }

    public Admin(String adminUserID, String adminUsername, String adminPassword, Students students, Culinary_Programs culinaryPrograms) {
        this.adminUserID = adminUserID;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.students = students;
        this.culinaryPrograms = culinaryPrograms;
    }

    public String getAdminUserID() {
        return adminUserID;
    }

    public void setAdminUserID(String adminUserID) {
        this.adminUserID = adminUserID;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Culinary_Programs getCulinaryPrograms() {
        return culinaryPrograms;
    }

    public void setCulinaryPrograms(Culinary_Programs culinaryPrograms) {
        this.culinaryPrograms = culinaryPrograms;
    }
}
