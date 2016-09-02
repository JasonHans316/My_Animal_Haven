package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.DateTimeRange;


/**
 * Created by Admin on 2016/04/03.
 */
public interface ISchedule {
    String getActivity();
    Long getScheduleId();
    DateTimeRange getDtr();
}
