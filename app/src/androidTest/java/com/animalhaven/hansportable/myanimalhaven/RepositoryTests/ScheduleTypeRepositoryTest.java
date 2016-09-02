package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.ScheduleType;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.ScheduleTypeRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleTypeRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class ScheduleTypeRepositoryTest extends AndroidTestCase {
    private static final String TAG="SCHEDULE TYPES TEST";
    private Long id;

    public void testScheduleTypeCRUD() throws Exception{
        ScheduleTypeRepository repo = new ScheduleTypeRepositoryImpl(this.getContext());

        ScheduleType scheduleType = new ScheduleType.Builder()
                .id(id)
                .code("T001")
                .name("Feeding")
                .scheduleId(1)
                .build();
        ScheduleType insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getScheduleTypeId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<ScheduleType> types = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",types.size()>0);

        //READ ENTITY
        ScheduleType entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        ScheduleType updateEntity = new ScheduleType.Builder()
                .copy(entity)
                .code("T002")
                .build();
        repo.update(updateEntity);
        ScheduleType newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","T002",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        ScheduleType deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
