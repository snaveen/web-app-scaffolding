package com.icode.dto;

import com.icode.platform.dto.AbstractDTO;
/**
 * 
 * @author nadhiya
 *
 */
public class UserDTO extends AbstractDTO{

	String name;
	
	int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}