package repository;

import domain.entities.Product;
import domain.models.service.ProductServiceModel;
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
                .createQuery("SELECT p FROM products p WHERE p.id = " + id, Product.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return entity;
    }

    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Product findByName(String name) {

        this.entityManager.getTransaction().begin();
        Product products = this.entityManager
                .createQuery("" +
                        "SELECT p " +
                        "FROM products p " +
                        "WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return products;
    }
}
