package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.DateTimeRange;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.ScheduleFactoryInterface;

/**
 * Created by Admin on 2016/04/03.
 */
public class ScheduleFactory implements ScheduleFactoryInterface{
    public Schedule createSchedule(String activity,
                                   int dtr)
    {
        return new Schedule.Builder()
                .activity(activity)
                .timeRequired(dtr)
                .build();
    }

}
