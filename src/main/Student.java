package application;

import java.time.LocalDate;

public class Student {
    private String name;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String religion;
    private String id;
    private String nationality;
    private boolean hasInsurance;
    private double payments;
    private String status;

    public Student(String name, String dateOfBirth, String gender, String address, String religion, String id,
                   String nationality, boolean hasInsurance, double payments, String status) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.religion = religion;
        this.id = id;
        this.nationality = nationality;
        this.hasInsurance = hasInsurance;
        this.payments = payments;
        this.status = status;
    }
    
    public Student() {
    	
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", religion='" + religion + '\'' +
                ", id='" + id + '\'' +
                ", nationality='" + nationality + '\'' +
                ", hasInsurance=" + hasInsurance +
                ", payments=" + payments +
                ", status='" + status + '\'' +
                '}';
    }
}

