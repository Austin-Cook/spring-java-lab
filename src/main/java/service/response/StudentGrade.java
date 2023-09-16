package service.response;

import java.util.Objects;

public class StudentGrade extends Report {
    private String professor;

    public StudentGrade() {
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String gradeToString() {
        return "       {" + "className='" + className + '\'' +
                ", professor='" + professor + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentGrade that = (StudentGrade) o;
        return professor.equals(that.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), professor);
    }
}
