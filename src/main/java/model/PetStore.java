package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PETSTORE")
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "MANAGERNAME")
    private String managerName;





    @OneToOne
    @JoinColumn(name = "ID_ADD")
    private  Adress adress;

    @ManyToMany()
    @JoinTable(name="PRODUCT_SOLD",
            joinColumns = @JoinColumn(name="ID_PETS", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PRO",referencedColumnName = "ID")
    )
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    public PetStore(String name, String managerName, Adress adress) {
        this.name = name;
        this.managerName = managerName;
        this.adress = adress;
    }
}
