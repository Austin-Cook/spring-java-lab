package service.response;

import java.util.Objects;

public class StudentNewClass extends Report {
    private String prerequisiteFor;

    public StudentNewClass() {
        super();
    }

    public StudentNewClass(int studentID, String name, String address, String phone, String className, String grade,
                           String prerequisiteFor) {
        super(studentID, name, address, phone, className, grade);
        this.prerequisiteFor = prerequisiteFor;
    }

    public String getPrerequisiteFor() {
        return prerequisiteFor;
    }

    public void setPrerequisiteFor(String prerequisiteFor) {
        this.prerequisiteFor = prerequisiteFor;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentNewClass)) return false;
        if (!super.equals(o)) return false;
        StudentNewClass that = (StudentNewClass) o;
        return Objects.equals(prerequisiteFor, that.prerequisiteFor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), prerequisiteFor);
    }
}
