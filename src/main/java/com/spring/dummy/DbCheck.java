package com.spring.dummy;

import com.spring.entity.Role;
import com.spring.entity.RoleName;
import com.spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 10/06/17.
 */
@Component
public class DbCheck {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        Role admin = new Role(null, RoleName.ADMIN, "Admin Role");
        Role projectLead = new Role(null, RoleName.PROJECT_LEAD, "Project Lead Role");
        Role developer = new Role(null, RoleName.DEVELOPER, "Developer Role");
        roles.add(admin);
        roles.add(projectLead);
        roles.add(developer);

        System.out.println("Saving roles to DB :::: " + roles);
        setRoles(roles);

        List<Role> persistedRoles = roleRepository.findAll();
        System.out.println("Persisted roles in DB :::: " + persistedRoles);
    }

    public void setRoles(List<Role> roles) {
        roleRepository.save(roles);
    }

}
