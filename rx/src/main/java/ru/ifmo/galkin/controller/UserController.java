package ru.ifmo.galkin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.ifmo.galkin.model.Product;
import ru.ifmo.galkin.model.User;
import ru.ifmo.galkin.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public Mono<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(value = "/user/{id}")
    public Mono<User> getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(value = "/user/{id}/delete")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/user/{id}/products")
    public Flux<Product> getProducts(@PathVariable long id) {
        return userService.getProducts(id);
    }


}
