package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public interface IScheduleType {
    String getCode();
    Long getScheduleTypeId();
    String getName();
    boolean isActive();
    List<Schedule> getSchedules();
}
