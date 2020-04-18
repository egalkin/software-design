package ru.ifmo.galkin.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.ifmo.galkin.model.Product;
import ru.ifmo.galkin.repository.ProductCrudRepository;

@Component
public class ProductService {
    private final ProductCrudRepository productRepository;

    public ProductService(ProductCrudRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> addProduct(Product product) {
        return productRepository.insert(product);
    }

    public Mono<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

}
