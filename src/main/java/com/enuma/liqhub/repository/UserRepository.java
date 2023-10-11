package com.enuma.liqhub.repository;

import com.enuma.liqhub.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel>findByNameAndPassword(String name, String password);
    Optional<UserModel>findFirstByName(String name);

}
