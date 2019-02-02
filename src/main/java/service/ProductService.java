package service;

import domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> allProducts();

    ProductServiceModel findByName(String name);

}
