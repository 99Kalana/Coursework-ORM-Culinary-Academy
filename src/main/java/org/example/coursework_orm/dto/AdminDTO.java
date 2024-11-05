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
public class AdminDTO {

    @Id
    private String adminUserID;

    private String adminUsername;

    private String adminPassword;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Students
    @JoinColumn(name = "student_ID")
    private Students students;

    @ManyToOne(cascade = CascadeType.ALL)// owners end to Culinary_Programs
    @JoinColumn(name = "program_ID")
    private Culinary_Programs culinaryPrograms;

}
