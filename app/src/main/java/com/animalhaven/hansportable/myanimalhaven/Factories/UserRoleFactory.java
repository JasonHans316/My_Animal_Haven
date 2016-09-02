package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.UserRoleFactoryInterface;
import com.animalhaven.hansportable.myanimalhaven.Domain.UserRole;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class UserRoleFactory implements UserRoleFactoryInterface {
    public UserRole createUserRole(
            String name,
            String code,
            int users,
            boolean active)
    {
        return new UserRole.Builder()
                .name(name)
                .code(code)
                .active(active)
                .userId(users)
                .build();
    }
}
