package com.spring.repository;

import com.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by apple on 10/06/17.
 */
public interface UserRepository extends JpaRepository<User, Long>{

}
