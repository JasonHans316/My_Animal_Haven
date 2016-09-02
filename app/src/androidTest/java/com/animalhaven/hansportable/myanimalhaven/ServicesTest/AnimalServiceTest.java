package com.animalhaven.hansportable.myanimalhaven.ServicesTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;

import junit.framework.Assert;

import org.junit.Test;

import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.AnimalServiceImpl;


/**
 * Created by Admin on 2016/05/08.
 */
public class AnimalServiceTest  extends AndroidTestCase {
    private AnimalServiceImpl myService;
    private boolean isBound;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context temp = App.getAppContext();
        Intent intent = new Intent(temp, AnimalServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AnimalServiceImpl.ActivateServiceLocalBinder binder
                    = (AnimalServiceImpl.ActivateServiceLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testCreateAnimal()
    {
        Animal original = new Animal.Builder()
                .adoption(-1)
                .age(2)
                .breed("Cat")
                .name("Tiger")
                .schedules(-1)
                .spaceRequired(300)
                .weight(9)
                .build();
        original = myService.storeAnimal(original);
        Assert.assertNotNull(original.getAnimalId());
    }

    @Test
    public void testUpdateAnimal()
    {
        Animal original = new Animal.Builder()
                .adoption(-1)
                .age(2)
                .breed("Cat")
                .name("Tiger")
                .schedules(-1)
                .spaceRequired(300)
                .weight(9)
                .build();
        Assert.assertTrue(myService.updateAnimalDetails(original));
    }

    @Test
    public void testFindByName()
    {
        Assert.assertNotNull(myService.findByName("iger"));
    }
}
