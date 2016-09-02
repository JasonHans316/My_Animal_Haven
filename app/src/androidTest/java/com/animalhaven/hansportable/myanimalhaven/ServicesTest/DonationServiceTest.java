package com.animalhaven.hansportable.myanimalhaven.ServicesTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;

import junit.framework.Assert;

import org.junit.Test;

import java.sql.Date;

import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.DonationServiceImpl;


/**
 * Created by Admin on 2016/05/08.
 */
public class DonationServiceTest extends AndroidTestCase {

    private DonationServiceImpl myService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context temp = App.getAppContext();
        Intent intent = new Intent(temp, DonationServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DonationServiceImpl.ActivateServiceLocalBinder binder
                    = (DonationServiceImpl.ActivateServiceLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testMakeDonation(){
        Donation record = new Donation.Builder().amount(500).comment("For Food").donationDate(new Date(2016, 05, 01)).build();
        Donation result = myService.makeDonation(record);
        Assert.assertNotNull(result.getDonationId());
    }

    @Test
    public void testDonationsAbove()
    {
        Assert.assertNotNull(myService.donationsAbove(200));
    }
    @Test
    public void testDonationsBelow()
    {
        Assert.assertNotNull(myService.donationsBelow(200));
    }
    @Test
    public void testDonationsEqualTo()
    {
        Assert.assertNotNull(myService.donationsEqualTo(200));
    }
}
