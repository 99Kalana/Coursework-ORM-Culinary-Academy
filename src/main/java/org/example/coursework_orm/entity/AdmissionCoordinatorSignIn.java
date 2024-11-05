package org.example.coursework_orm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data

@Entity
public class AdmissionCoordinatorSignIn {
    @Id
    private String admissionCoUsername;

    private String admissionCoPassword;
}
