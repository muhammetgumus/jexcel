package model;

public class Employee {
    @Column(name="Employee Name",columnNumber = 2)
    public String employeeName;

    @Column(name = "Id", columnNumber = 3)
    public String id;

    @Column(name = "Surname", columnNumber = 1)
    public String surname;

    public Employee(String name, String surname, String id) {
    this.employeeName=name;
    this.id=id;
    this.surname=surname;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getId() {
        return id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
