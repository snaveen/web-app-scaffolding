package com.icode.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.icode.platform.entity.AbstractEntity;
/**
 * 
 * @author nadhiya
 *
 */
@Entity
@Table (name="users")
public class User extends AbstractEntity{

	/**
     * 
     */
    private static final long serialVersionUID = -3846822646381906473L;

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