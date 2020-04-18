package ru.ifmo.galkin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.galkin.model.Product;

@Repository
public interface ProductCrudRepository extends ReactiveMongoRepository<Product, Long> {
}
