package org.jmd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "aadhar_number")
    private Aadhar aadhar;
}


/**
 * SELECT p.id, p.name, p.email, a.aadharNumber, a.address, a.dob
 * FROM Person p
 * LEFT JOIN Aadhar a
 * ON p.aadhar_number = a.aadharNumber;
 */