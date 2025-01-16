package org.LibraryMS.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Timestamp registrationDate;
    private List<Integer> borrowedBooksIds = new ArrayList<>();

    public Member(int id, String name, String email, String phoneNumber, Timestamp registrationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public Member(String name, String email, String phoneNumber, Timestamp registrationDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Integer> getBorrowedBooksIds() {
        return borrowedBooksIds;
    }

    public void setBorrowedBooksIds(List<Integer> borrowedBooksIds) {
        this.borrowedBooksIds = borrowedBooksIds;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate + '}';
    }
}
