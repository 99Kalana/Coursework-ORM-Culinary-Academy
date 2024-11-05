package org.example.coursework_orm.dto;


import jakarta.persistence.*;
import lombok.*;
import org.example.coursework_orm.entity.Admin;
import org.example.coursework_orm.entity.Admission_Coordinator;
import org.example.coursework_orm.entity.Culinary_Programs;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

@Entity
public class StudentsDTO {

    @Id
    private String studentID;

    private Date date;

    private String firstName;

    private String lastName;

    private String email;

    private int mobileNumber;

    private String address;

    private String selectedCourse;

    @ManyToOne(cascade = CascadeType.ALL)// owners end
    @JoinColumn(name = "Program_ID")
    private Culinary_Programs culinaryPrograms;

    @OneToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //inverse end to Admission Coordinator Entity
    private List<Admission_Coordinator> admissionCoordinators;

    @OneToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // inverse end to Admin Entity
    private List<Admin> admins;

}
