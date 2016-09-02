package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.DateTimeRange;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Factories.ScheduleFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.ScheduleFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestScheduleFactory {

    @Test
    public void testCreate()
    {
        ScheduleFactoryInterface factory = new ScheduleFactory();
        Schedule testObject = factory.createSchedule("Bathing", 5);

        Assert.assertEquals("Bathing", testObject.getActivity());
    }

    @Test
    public void testUpdate()
    {
        ScheduleFactoryInterface factory = new ScheduleFactory();
        Schedule original = factory.createSchedule("Bathing", 5);
        Schedule copy = new Schedule.Builder().copy(original).activity("Feeding").build();

        Assert.assertEquals("Bathing", original.getActivity());
        Assert.assertEquals("Feeding", copy.getActivity());

    }
}
