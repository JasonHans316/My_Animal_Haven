package com.animalhaven.hansportable.myanimalhaven.Services.Interfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;

/**
 * Created by Admin on 2016/05/08.
 */
public interface ScheduleServiceInterface {
    Schedule createSchedule(Schedule newSchedule);
    boolean removeAllSchedules();
}
