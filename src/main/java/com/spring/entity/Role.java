package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by apple on 23/05/17.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long roleId;
    private RoleName rolename;
    private String roleDescription;

    public Role() { }

    public Role(Long roleId, RoleName rolename, String roleDescription) {
        this.roleId = roleId;
        this.rolename = rolename;
        this.roleDescription = roleDescription;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleName getRolename() {
        return rolename;
    }

    public void setRolename(RoleName rolename) {
        this.rolename = rolename;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
