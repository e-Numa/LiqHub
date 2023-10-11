package com.enuma.liqhub.repository;

import com.enuma.liqhub.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository <AdminModel, Long> {
    Optional<AdminModel> findByNameAndPassword(String name, String password);
    Optional<AdminModel>findFirstByName(String name);
}
