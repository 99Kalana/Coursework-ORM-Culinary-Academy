package org.example.coursework_orm.model.tm;

import java.util.Date;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

public class StudentsTM {

    private String studentID;

    private Date date;

    private String firstName;

    private String lastName;

    private String email;

    private int mobileNumber;

    private String address;

    private String selectedCourse;

}
