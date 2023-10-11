package com.enuma.liqhub.service;

import com.enuma.liqhub.model.AdminModel;
import com.enuma.liqhub.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){

        this.adminRepository = adminRepository;
    }
    public AdminModel createAdmin(String name, String email, String password){

        if (name == null || password== null){
            return null;
        }else {
            if (adminRepository.findFirstByName(name).isPresent()){
                System.out.println("User Already Onboard");
                return null;
            }
            AdminModel adminModel = new AdminModel();

            adminModel.setName(name);
            adminModel.setEmail(email);
            adminModel.setPassword(password);

            adminRepository.save(adminModel);

            return adminModel;
        }
    }

    public AdminModel authentications(String name, String password){
        return adminRepository.findByNameAndPassword(name, password).orElse(null);
    }

}
