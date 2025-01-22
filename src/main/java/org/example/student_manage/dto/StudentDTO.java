package org.example.student_manage.dto;

public class StudentDTO {
   String firstName;
   String lastName;

    public StudentDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

}
