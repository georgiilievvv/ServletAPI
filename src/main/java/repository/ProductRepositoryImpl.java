package repository;

import domain.entities.Product;
import util.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    @Inject
    public ProductRepositoryImpl(ModelMapper modelmapper) {
        this.entityManager = Persistence.createEntityManagerFactory("chyshka")
                .createEntityManager();
    }

    public Product save(Product entity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    public Product findById(String id) {
        this.entityManager.getTransaction().begin();
        Product entity = this.entityManager
                .createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return entity;
    }

    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return products;
    }
}
