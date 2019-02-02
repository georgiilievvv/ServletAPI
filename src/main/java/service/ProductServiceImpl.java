package service;

import domain.entities.Product;
import domain.entities.Type;
import domain.models.service.ProductServiceModel;
import repository.ProductRepository;
import util.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductServiceModel> allProducts() {
        return this.productRepository.findAll()
                .stream()
                .map(pr -> this.modelMapper.map(pr, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findByName(String name) {

        return this.modelMapper.map(
                this.productRepository.findByName(name), ProductServiceModel.class);
    }
}
