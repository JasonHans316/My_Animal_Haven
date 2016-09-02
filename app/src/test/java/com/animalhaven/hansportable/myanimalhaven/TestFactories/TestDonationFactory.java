package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Factories.DonationFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.DonationFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestDonationFactory {
    @Test
    public void testCreate()
    {
        DonationFactoryInterface factory = new DonationFactory();
        Donation original = factory.createDonation( new Date(2016,2,5),500, "For Food");

        Assert.assertEquals("For Food", original.getComment());
    }

    @Test
    public void testUpdate()
    {
        DonationFactoryInterface factory = new DonationFactory();
        Donation original = factory.createDonation( new Date(2016,2,5),500, "For Food");
        Donation copy = new Donation.Builder().copy(original).comment("For Medicine").build();
        Assert.assertEquals("For Medicine", copy.getComment());
        Assert.assertEquals("For Food", original.getComment());
    }
}
