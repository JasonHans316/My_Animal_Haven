package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.User;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public interface IUserRole {
    Long getUserRoleId();
    String getName();
    String getCode();
    boolean isActive();
    List<User> getUsers();
}
