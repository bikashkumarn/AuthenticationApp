package com.spring.repository;

import com.spring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by apple on 10/06/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
