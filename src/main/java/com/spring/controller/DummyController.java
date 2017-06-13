package com.spring.controller;

import com.spring.entity.Role;
import com.spring.entity.RoleName;
import com.spring.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 10/06/17.
 */
@RestController
public class DummyController {

    @Autowired
    private RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(DummyController.class);

    @RequestMapping(value = "/saveRoles")
    public List<Role> saveRoles() {

        logger.debug("Entered saveRoles()...");

        List<Role> roles = new ArrayList<>();
        Role admin = new Role(null, RoleName.ADMIN, "Admin Role");
        Role projectLead = new Role(null, RoleName.PROJECT_LEAD, "Project Lead Role");
        Role developer = new Role(null, RoleName.DEVELOPER, "Developer Role");
        roles.add(admin);
        roles.add(projectLead);
        roles.add(developer);

        logger.debug("Saving Roles :: " + roles);
        setRoles(roles);

        List<Role> persistedRoles = roleRepository.findAll();
        logger.debug("Persisted Roles :: " + persistedRoles);
        return persistedRoles;
    }

    public void setRoles(List<Role> roles) {
        roleRepository.save(roles);
    }

}
