package org.jmd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
//@ToString(exclude = "department")
public class Employee {
    public Employee() {
    }

    @Id
    private Long id;
    private String name;
    private String email;
    private String salary;


}
