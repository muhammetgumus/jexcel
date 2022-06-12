package model;

public class Student implements Comparable<Student> {
    @Column(name = "Name", columnNumber = 2)
    public String name;

    @Column(name = "Student Id", columnNumber = 1)
    public Integer studentId;

    @Column(name = "Surname", columnNumber = 3)
    public String surname;

    @Column(name = "School", columnNumber = 5)
    public String school;

    @Column(name = "GPA", columnNumber = 4)
    public Double gpa = 3.5;

    public Student(Integer studentId, String name, String surname, String school) {
        this.name = name;
        this.studentId = studentId;
        this.surname = surname;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public int compareTo(Student student) {
        return student.getStudentId().compareTo(this.studentId);
    }
}
