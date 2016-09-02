package com.animalhaven.hansportable.myanimalhaven.Services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.ScheduleRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleRepository;
import com.animalhaven.hansportable.myanimalhaven.Services.Interfaces.ScheduleServiceInterface;

/**
 * Created by Admin on 2016/05/08.
 */
public class ScheduleServiceImpl extends Service implements ScheduleServiceInterface {

    final private ScheduleRepository repo;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static ScheduleServiceImpl service = null;

    public static ScheduleServiceImpl getInstance()
    {
        if(service == null)
            service = new ScheduleServiceImpl();
        return service;
    }

    private ScheduleServiceImpl()
    {
        repo = new ScheduleRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ScheduleServiceImpl getService() {
            return ScheduleServiceImpl.this;
        }
    }

/**
 * Used to create new schedule
 * */
    @Override
    public Schedule createSchedule(Schedule value) {
        try{
            return repo.save(value);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /**
     * Remove All schedules at the start of every month to assign task to new staff
     * */
    @Override
    public boolean removeAllSchedules() {
        try{
            repo.deleteAll();
            return repo.findAll().size() == 0;
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return false;
    }
}
