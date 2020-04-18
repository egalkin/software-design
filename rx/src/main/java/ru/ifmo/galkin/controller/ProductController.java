package ru.ifmo.galkin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.ifmo.galkin.model.Product;
import ru.ifmo.galkin.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product")
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("product/{id}")
    public Mono<Product> getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("product/{id}/delete")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
