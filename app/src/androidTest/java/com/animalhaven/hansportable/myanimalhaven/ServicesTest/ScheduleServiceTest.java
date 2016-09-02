package com.animalhaven.hansportable.myanimalhaven.ServicesTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;

import junit.framework.Assert;

import org.junit.Test;

import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.ScheduleServiceImpl;

/**
 * Created by Admin on 2016/05/08.
 */
public class ScheduleServiceTest extends AndroidTestCase {
    private ScheduleServiceImpl scheduleService;
    private boolean isBound;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Context temp = App.getAppContext();
        Intent intent = new Intent(temp, ScheduleServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ScheduleServiceImpl.ActivateServiceLocalBinder binder
                    = (ScheduleServiceImpl.ActivateServiceLocalBinder) service;
            scheduleService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @Test
    public void testCreateSchedule(){
        Schedule record = new Schedule.Builder().timeRequired(25).activity("Feeding").build();
        Schedule result = scheduleService.createSchedule(record);
        Assert.assertNotNull(result.getScheduleId());
    }

    @Test
    public void testDeleteAll()
    {
        Assert.assertTrue(scheduleService.removeAllSchedules());
    }
}
