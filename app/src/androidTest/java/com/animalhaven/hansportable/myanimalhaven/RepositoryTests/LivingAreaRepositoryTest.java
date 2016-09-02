package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.LivingAreaRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.LivingAreaRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class LivingAreaRepositoryTest extends AndroidTestCase {
    private static final String TAG="LIVING AREA TEST";
    private Long id;

    public void testLivingAreaCRUD() throws Exception{
        LivingAreaRepository repo = new LivingAreaRepositoryImpl(this.getContext());

        LivingArea scheduleType = new LivingArea.Builder()
                .id(id)
                .code("L001")
                .name("Big Cat Enclosure")
                .animalId(1)
                .spaceAvailable(300)
                .build();
        LivingArea insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getLivingAreaId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<LivingArea> areas = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",areas.size()>0);

        //READ ENTITY
        LivingArea entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        LivingArea updateEntity = new LivingArea.Builder()
                .copy(entity)
                .code("area01")
                .build();
        repo.update(updateEntity);
        LivingArea newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","area01",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        LivingArea deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
