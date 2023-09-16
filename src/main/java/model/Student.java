package model;

import java.util.Objects;

public class Student {
    private int studentID;
    private String name;
    private String address;
    private String phone;

    public Student() {

    }

    public Student(int studentID, String name, String address, String phone) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        String sb = "Student{" + "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return studentID == student.studentID &&
                name.equals(student.name) &&
                address.equals(student.address) &&
                phone.equals(student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, address, phone);
    }
}
