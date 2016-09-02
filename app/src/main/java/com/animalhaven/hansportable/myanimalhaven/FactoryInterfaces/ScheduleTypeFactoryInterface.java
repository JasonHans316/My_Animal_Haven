package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.ScheduleType;

import java.util.List;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface ScheduleTypeFactoryInterface {
    ScheduleType createScheduleType(String code,
                                    String name,
                                    int schedules,
                                    boolean active);
}
