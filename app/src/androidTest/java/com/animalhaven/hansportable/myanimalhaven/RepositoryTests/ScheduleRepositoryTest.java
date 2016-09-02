package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.ScheduleRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class ScheduleRepositoryTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testScheduleTypeCRUD() throws Exception{
        ScheduleRepository repo = new ScheduleRepositoryImpl(this.getContext());

        Schedule scheduleType = new Schedule.Builder()
                .id(id)
                .activity("Feeding")
                .timeRequired(1)
                .build();
        Schedule insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getScheduleId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Schedule> types = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",types.size()>0);

        //READ ENTITY
        Schedule entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Schedule updateEntity = new Schedule.Builder()
                .copy(entity)
                .activity("Bathing")
                .build();
        repo.update(updateEntity);
        Schedule newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Bathing",newEntity.getActivity());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        //repo.delete(insertedEntity);
        //Schedule deletedEntity = repo.findById(id);
        //Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
