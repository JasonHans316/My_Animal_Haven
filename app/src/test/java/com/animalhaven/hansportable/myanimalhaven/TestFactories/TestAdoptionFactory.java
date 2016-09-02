package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Factories.AdoptionFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.AdoptionFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestAdoptionFactory {
    @Test
    public void testCreate()
    {
        AdoptionFactoryInterface factory = new AdoptionFactory();
        Adoption original = factory.createAdoption("First Dog", new Date(2016,02,24));

        Assert.assertEquals(new Date(2016,02,24), original.getAdoptionDate());
    }

    @Test
    public void testUpdate()
    {
        AdoptionFactoryInterface factory = new AdoptionFactory();
        Adoption original = factory.createAdoption("First Dog", new Date(2016,02,24));
        Adoption copy = new Adoption.Builder().copy(original).adoptionDate(new Date(2016,04,06)).build();

        Assert.assertEquals(new Date(2016,02,24), original.getAdoptionDate());
        Assert.assertEquals(new Date(2016,04,06), copy.getAdoptionDate());
        Assert.assertNotEquals(original.getAdoptionDate(), copy.getAdoptionDate());
    }
}
