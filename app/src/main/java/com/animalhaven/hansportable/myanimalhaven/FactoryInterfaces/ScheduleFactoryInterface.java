package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.DateTimeRange;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface ScheduleFactoryInterface {
    Schedule createSchedule(String activity,
                            int dtr);
}
