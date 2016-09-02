package com.animalhaven.hansportable.myanimalhaven.ServicesTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;

import junit.framework.Assert;

import org.junit.Test;

import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.LivingAreaServiceImpl;

/**
 * Created by Admin on 2016/05/08.
 */
public class LivingAreaServiceTest extends AndroidTestCase {
    private LivingAreaServiceImpl myService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), LivingAreaServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LivingAreaServiceImpl.ActivateServiceLocalBinder binder
                    = (LivingAreaServiceImpl.ActivateServiceLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testCreateArea() {
        LivingArea record = new LivingArea.Builder().animalId(-1).code("T001").name("Kennel").spaceAvailable(500).active(true).build();
        LivingArea result = myService.createLivingArea(record);
        Assert.assertNotNull(result.getLivingAreaId());
    }

    @Test
    public void testHouseAnimal() {
        Animal animal = new Animal.Builder().adoption(0).age(2).breed("Dog").name("Bingo").schedules(0).spaceRequired(400).weight(25).build();
        LivingArea record = new LivingArea.Builder().animalId(animal.getAnimalId().intValue()).code("T001").name("Kennel").spaceAvailable(500).active(true).build();
        Assert.assertTrue(myService.houseAnimal(record, animal));
    }

    @Test
    public void testFindAvailability() {
        LivingArea result = myService.findAvailability(350);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetLivingAreas()
    {
        Assert.assertNotNull(myService.getLivingAreas());
    }

    @Test
    public void testRelocateAnimal()
    {
        Animal animal = new Animal.Builder().adoption(0).age(2).breed("Dog").name("Bingo").schedules(0).spaceRequired(400).weight(25).build();
        Assert.assertNotNull(myService.relocateAnimal(animal));
    }
}
