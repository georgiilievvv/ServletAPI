package repository;

import domain.entities.Product;
import domain.models.service.ProductServiceModel;

public interface ProductRepository extends GenericRepository<Product, String> {

    Product findByName(String name);
}
