package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.Domain.UserRole;
import com.animalhaven.hansportable.myanimalhaven.Factories.UserRoleFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.UserRoleFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestUserRoleFactory {
    @Test
    public void testCreate()
    {
        UserRoleFactoryInterface factory = new UserRoleFactory();
        UserRole original = factory.createUserRole("Jason", "JH", 1, true);

        Assert.assertEquals("JH", original.getCode());
    }

    @Test
    public void testUpdate()
    {
        UserRoleFactoryInterface factory = new UserRoleFactory();
        UserRole original = factory.createUserRole("Jason", "JH", 1, true);
        UserRole copy  = new UserRole.Builder().copy(original).code("SR").build();

        Assert.assertEquals("JH", original.getCode());
        Assert.assertEquals("SR", copy.getCode());
        Assert.assertEquals(original.isActive(), copy.isActive());
    }
}
