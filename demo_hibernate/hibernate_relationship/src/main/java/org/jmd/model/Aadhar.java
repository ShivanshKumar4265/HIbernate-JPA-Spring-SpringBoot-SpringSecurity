package org.jmd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Aadhar {

    @Id
    private  Long aadharNumber;

    private LocalDateTime dob;
    private  String address;
}
