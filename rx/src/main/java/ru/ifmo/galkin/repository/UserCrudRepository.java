package ru.ifmo.galkin.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.galkin.model.User;

@Repository
public interface UserCrudRepository extends ReactiveMongoRepository<User, Long> {
}

