package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.Domain.UserRole;

import java.util.List;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface UserRoleFactoryInterface {
    UserRole createUserRole(
            String name,
            String code,
            int users,
            boolean active);
}
