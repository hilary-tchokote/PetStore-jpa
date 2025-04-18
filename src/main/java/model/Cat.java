package model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "CAT")
public class Cat extends Animal {

    private String chipId;

    public Cat(Date birth, String color, PetStore petStore,  String chipId) {
        super(birth, color, petStore);
        this.chipId = chipId;
    }


}
