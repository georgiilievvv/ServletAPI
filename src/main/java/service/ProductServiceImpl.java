package service;

import domain.entities.Product;
import domain.entities.Type;
import domain.models.service.ProductServiceModel;
import repository.ProductRepository;
import util.ModelMapper;

import javax.inject.Inject;
import java.net.Proxy;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public void saveProduct(ProductServiceModel productServiceModel) {
        Product entity = this.modelMapper.map(productServiceModel, Product.class);
        entity.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepository.save(entity);
    }
}
