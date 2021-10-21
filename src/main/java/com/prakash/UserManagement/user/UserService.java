package com.prakash.UserManagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }


    public void save(User user) {

        userRepository.save(user);
    }

    public User get(Integer id)throws UserNotFountException{
        Optional<User> userById = userRepository.findById(id);
        if(userById.isPresent()){
          return userById.get();
        }
        else {
            throw new UserNotFountException("User is not found the user with id :"+id);
        }
    }
    public void deleteById(Integer id)throws UserNotFountException{
        Long count = userRepository.countById(id);
        if(count==null||count==0){
            throw new UserNotFountException("Could not found the user with id :"+id);
        }
        userRepository.deleteById(id);
    }
}
