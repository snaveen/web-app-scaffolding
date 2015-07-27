/**
 * 
 */
package com.icode.service;

import org.springframework.stereotype.Service;

import com.icode.model.User;

/**
 * @author nadhiya
 *
 */
@Service
public class UserService {

    public User getUser(int id) {
        User user = new User();
        user.setId(1);
        user.setName("Nadhiya");
        user.setAge(16);
        return user;
    }
    
    public void createUser(User user) {
        //TODO
    }

}
