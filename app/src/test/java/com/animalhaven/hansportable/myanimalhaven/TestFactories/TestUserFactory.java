package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.Factories.UserFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.UserFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestUserFactory {

    @Test
    public void testCreate()
    {

        UserFactoryInterface factory = new UserFactory();

        User original = factory.createUser("Jason", "Hans", "9405142354788", 1, 0, 2);

        Assert.assertEquals("Hans", original.getSurname());
    }

    @Test
    public void testUpdate()
    {

        UserFactoryInterface factory = new UserFactory();

        User original = factory.createUser("Jason", "Hans", "9405142354788", 1, 0, 2);
        User copy = new User.Builder().copy(original).surname("Stevens").build();

        Assert.assertEquals("Hans", original.getSurname());
        Assert.assertEquals("Stevens", copy.getSurname());
        Assert.assertEquals(copy.getIdNumber(), original.getIdNumber());

    }
}
