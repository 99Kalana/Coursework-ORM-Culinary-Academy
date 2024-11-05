package org.example.coursework_orm.dto;

import jakarta.persistence.*;
import lombok.*;
import org.example.coursework_orm.entity.Culinary_Programs;
import org.example.coursework_orm.entity.Students;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data


@Entity
public class Admission_CoordinatorDTO {

    @Id
    private String admissionCoUserID;

    private String admissionCoUsername;

    private String admissionCoPassword;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL) // owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

}
