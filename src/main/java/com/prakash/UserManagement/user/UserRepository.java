package com.prakash.UserManagement.user;

import com.prakash.UserManagement.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    public Long countById(Integer id);

}
