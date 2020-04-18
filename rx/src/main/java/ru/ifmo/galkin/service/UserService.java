package ru.ifmo.galkin.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ifmo.galkin.model.Product;
import ru.ifmo.galkin.model.User;
import ru.ifmo.galkin.repository.ProductCrudRepository;
import ru.ifmo.galkin.repository.UserCrudRepository;
import ru.ifmo.galkin.utils.CurrencyConverter;

@Component
public class UserService {
    private final UserCrudRepository userRepository;
    private final ProductCrudRepository productRepository;


    public UserService(UserCrudRepository userRepository,
                       ProductCrudRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    public Mono<User> addUser(User user) {
        return userRepository.insert(user);
    }

    public Mono<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public Flux<Product> getProducts(long id) {
        return userRepository.findById(id)
                .flatMapMany(user ->productRepository.findAll()
                        .map(product -> CurrencyConverter.convert(product, user.getAccountCurrency())));
    }


}
