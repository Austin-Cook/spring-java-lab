package service.response;

import model.Student;

import java.util.Objects;

public abstract class Report {
    int studentID;
    String name;
    String address;
    String phone;
    String className;
    String grade;

    public Report() {
    }

    public Report(int studentID, String name, String address, String phone, String className, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.className = className;
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        return "Report {" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return studentID == report.studentID &&
                name.equals(report.name) &&
                Objects.equals(address, report.address) &&
                Objects.equals(phone, report.phone) &&
                Objects.equals(className, report.className) &&
                Objects.equals(grade, report.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, address, phone, className, grade);
    }
}
