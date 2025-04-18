package controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PetStoreQuery {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();

        et.begin();

        // Insertion de données dans chaque table

        // Creation d'une address

        Adress adress1 = new Adress("15", "Rue du Zoo", "75000", "Paris");
        Adress adress2 = new Adress( "22", "Avenue des Chats", "69000", "Lyon");
        Adress adress3 = new Adress("7", "Boulevard Canin", "13000", "Marseille");

        em.persist(adress1);
        em.persist(adress2);
        em.persist(adress3);


        // Création d'une PetStore
        PetStore store1 = new PetStore("Animaux & Co", "Desmond",adress1);

        PetStore store2 = new PetStore("PetWorld", "Hilary", adress3);

        PetStore store3 = new PetStore("PetCenter", "Peter",adress2);

        em.persist(store1);
        em.persist(store2);
        em.persist(store3);


        Date birthDate = new Date();

        // Création d'Animaux
        Fish f1 = new Fish(birthDate, "Black",  store1, FishLivEnv.Fresh_Water);
        Fish f2 = new Fish(birthDate, "White",store1,  FishLivEnv.Sea_Water);
        Cat c1 =  new Cat(birthDate, "White-black",  store2, "Cat-01");

        em.persist(f1);
        em.persist(f2);
        em.persist(c1);


        // Ajout de produits
        Product food1 = new Product("FOOD",  ProdType.Food, "Croquettes chien", 12.99 );

        Product food2 = new Product("FOOD",  ProdType.Food, "Croquettes chat", 10.49 );

        Product accessory1 = new Product("ACC", ProdType.Accessory, "Lasse Chien", 13.50 );

        em.persist(food1);
        em.persist(food2);
        em.persist(accessory1);



        // Requête pour extraire tous les produits selon un code produit
        Set<Product> products = new HashSet<>(
                em.createQuery("SELECT p FROM Product p WHERE p.code = :code", Product.class)
                        .setParameter("code", "FOOD")
                        .getResultList()
        );

        for (Product p : products) {
            System.out.println(p.getLabel() + " - " + p.getPrice() + "€");
        }



//        // Requête pour extraite tous les animaux d’un store: store1
        System.out.println("\n--- Animaux dans la boutique ---");

        Set<Animal> animals = new HashSet<>(
                em.createQuery("SELECT a FROM Animal a WHERE a.petStore.id = :id", Animal.class)
                .setParameter("id", store1.getId())
                .getResultList());


        for (Animal a : animals) {
                System.out.println(a.getClass().getSimpleName() + " - " + a.getColor());
        }

        et.commit();

        em.close();
        emf.close();
    }
}
