package org.jmd.model;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@Data
public class States {
    // this entity  class have
    // foreign key so , this is owner/parent class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String stateName;

     // here cascade type means,
     // that we will save the states object
     // and utomtically country object persis in db
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Country country;

    public States() {
    }

    public  States(String stateName){
        this.stateName = stateName;
    }
}
