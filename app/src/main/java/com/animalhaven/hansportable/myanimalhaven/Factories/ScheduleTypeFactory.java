package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.ScheduleType;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.ScheduleTypeFactoryInterface;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class ScheduleTypeFactory implements ScheduleTypeFactoryInterface {
    public ScheduleType createScheduleType(String code,
                                           String name,
                                           int schedules,
                                           boolean active)
    {
        return new ScheduleType.Builder()
                .code(code)
                .name(name)
                .active(active)
                .scheduleId(schedules)
                .build();
    }
}
