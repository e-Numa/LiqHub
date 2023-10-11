package com.enuma.liqhub.service;

import com.enuma.liqhub.model.UserModel;
import com.enuma.liqhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel createUser(String name, String email, String password){

        if (name == null || password==null){
            return null;
        }else {
            if (userRepository.findFirstByName(name).isPresent()){
                System.out.println("User Already Onboard");
                return null;
            }
            UserModel user = new UserModel();

            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            userRepository.save(user);

            return user;
        }

    }

    public UserModel authentication(String name, String password){
        return userRepository.findByNameAndPassword(name, password).orElse(null);

    }

    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }



}
