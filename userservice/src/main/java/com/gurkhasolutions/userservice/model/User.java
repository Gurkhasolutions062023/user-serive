package com.gurkhasolutions.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column
    private String email;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String role;




    // policy
    // policy_id
    //policy_name
    //policy_description
    // policy price
    // added_by



}
