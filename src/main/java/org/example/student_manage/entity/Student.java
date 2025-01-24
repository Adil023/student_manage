package org.example.student_manage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="students")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Student {

    @SequenceGenerator(
            name="student_sequence",
            sequenceName ="student_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Id
    Long id;
    @NotBlank(message = "first name can not be empty")
    @Column(name="first_name")
    String firstName;
    @Column(name="last_name")
    @NotBlank(message = "last name can not be empty")
    String lastName;
    @NotBlank
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    String password;
    @NotBlank(message = "email can not be empty")
    @Email
    String email;

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }


    @ManyToMany
    @JoinTable(
            name="student_course",
            joinColumns = @JoinColumn(name="student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="course_id",referencedColumnName = "id")

    )
    List<Course> courses = new ArrayList<>();

    @OneToOne
    @JoinColumn(
            name="student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name="student_id_fk"
            )
    )//owning side
    private Adress adress;


    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )

    List<Book> books = new ArrayList<>();


}
