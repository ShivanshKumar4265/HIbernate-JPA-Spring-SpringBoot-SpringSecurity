package org.jmd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String departmentName;
    /**
     * for this one  more tabel create create wiht two foreing key and
     *  of departmetn and employee wiht table name Dompany_Employee
     */
    // the side which have forengin key is owning or owner unidirectinal
    // and first phrase will for the class it is present (owning or owner class)
    // and second will represnt for emplyerr class
    @OneToMany
    private List<Employee> employees;

    public Department() {

    }
}
