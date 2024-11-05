package org.example.coursework_orm.dto;

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
public class ResetUsernamePasswordAdminDTO {
    @Id
    private String adminUserID;

    private String adminUsername;

    private String adminPassword;
}
