package org.example.student_manage.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Adress {
    @SequenceGenerator(
            name="adrees_sequence",
            sequenceName="adress_squence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "adrees_sequence"
    )
    @Id
    Long Id;
    @Column(name="city_name")
    String city;
    @Column(name="street_name")
    String street;

    @OneToOne(
            mappedBy = "adress",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )

    private Student student;
}
