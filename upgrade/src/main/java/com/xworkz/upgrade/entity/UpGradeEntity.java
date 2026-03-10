package com.xworkz.upgrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "upgrade_tb")

@AllArgsConstructor
@NoArgsConstructor


@NamedQueries({
        @NamedQuery(
                name = "findByEmail",
                query = "SELECT u FROM UpGradeEntity u WHERE u.email = :email"
        ),
        @NamedQuery(
                name = "countByEmail",
                query = "SELECT entity FROM UpGradeEntity entity WHERE entity.email = :email"
        )
})



public class UpGradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
