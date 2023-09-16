package model;

import java.util.Objects;

public class Class {
    private int classID;
    private String className;
    private String room;
    private String professor;

    public Class() {

    }

    public Class(int classID, String className, String room, String professor) {
        this.classID = classID;
        this.className = className;
        this.room = room;
        this.professor = professor;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        String sb = "Class{" + "classID=" + classID +
                ", className='" + className + '\'' +
                ", room='" + room + '\'' +
                ", professor='" + professor + '\'' +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class)) return false;
        Class aClass = (Class) o;
        return classID == aClass.classID &&
                className.equals(aClass.className) &&
                Objects.equals(room, aClass.room) &&
                Objects.equals(professor, aClass.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classID, className, room, professor);
    }
}
