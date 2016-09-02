package com.animalhaven.hansportable.myanimalhaven.Domain;
//import javax.persistence.*;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IUserRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class UserRole implements Serializable {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;
    private String name;
    private String code;
    private boolean active;
    private int userId;

    public UserRole(Builder value)
    {
        userRoleId = value.id;
        this.active = value.active;
        this.name = value.name;
        this.code = value.code;
        this.userId = value.userId;
    }

    public int getUserId() {
        return userId;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder{
        String name;
        String code;
        boolean active;
        int userId;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder copy(UserRole value){
            this.id = value.userRoleId;
            this.active = value.active;
            this.name = value.name;
            this.code = value.code;
            this.userId = value.userId;
            return this;
        }

        public UserRole build()
        {
            return new UserRole(this);
        }
    }
}
