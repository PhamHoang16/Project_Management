package com.example.projectmanagement.usermanagement.entity;

import com.example.projectmanagement.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
    })
public class User extends BaseEntity {
    private String username;

    private String email;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @ManyToOne
    private Role role;
}
