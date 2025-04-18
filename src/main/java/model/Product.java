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
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "LABEL")
    private String label;


    @Enumerated(EnumType.STRING)
    private ProdType type;

    @Column(name = "PRICE")
    private double price;


    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petStoreSet = new HashSet<>();

    public Product(String code, ProdType type, String label, double price) {
        this.code = code;
        this.type = type;
        this.label = label;
        this.price = price;

    }
}
