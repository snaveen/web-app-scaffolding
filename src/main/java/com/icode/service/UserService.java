/**
 * 
 */
package com.icode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icode.dto.UserDTO;
import com.icode.entity.User;
import com.icode.platform.api.IDao;

/**
 * @author nadhiya
 *
 */
@Service
public class UserService {
    
    @Autowired
    private IDao<User, Long> genericDao;

   
    @Transactional
    public User getUser(Long pkey) {
        User user=genericDao.find(User.class, pkey);
        return user;
    }
    
    @Transactional
    public void createUser(UserDTO userDTO) {
        //create entity from TO objects we can write generic assembler to do this job - Dozer bean mapper library can be used
        User user=new User();
        user.setAge(userDTO.getAge());
        user.setName(userDTO.getName());
        
        //this will save user
        genericDao.saveOrUpdate(user);
        
        //after saving user object will have user pkey
        userDTO.setPkey(user.getPkey());
    }

}
