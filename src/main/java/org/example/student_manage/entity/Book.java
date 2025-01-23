package org.example.student_manage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @SequenceGenerator(
            name="book_name",
            sequenceName = "book_name",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_name"
    )

    @Id
    Long id;
    @Column(
            name="book_title"
    )
    String title;
    @Column(
            name="book_author"
    )
    String author;

    @ManyToOne
    @JoinColumn(
            name="student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name="student_fk"
            )

    )
    private Student student;
}
