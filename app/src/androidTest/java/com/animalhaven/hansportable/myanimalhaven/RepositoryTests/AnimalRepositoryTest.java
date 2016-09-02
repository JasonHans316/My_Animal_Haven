package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.AnimalRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.AnimalRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class AnimalRepositoryTest extends AndroidTestCase {
    private static final String TAG="ANIMAL TEST";
    private Long id;

    public void testAnimalCRUD() throws Exception{
        AnimalRepository repo = new AnimalRepositoryImpl(this.getContext());

        Animal scheduleType = new Animal.Builder()
                .id(id)
                .name("Jason")
                .breed("Horse")
                .spaceRequired(2)
                .weight(140)
                .age(4)
                .adoption(1)
                .schedules(6)
                .build();
        Animal insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getAnimalId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Animal> users = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",users.size()>0);

        //READ ENTITY
        Animal entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Animal updateEntity = new Animal.Builder()
                .copy(entity)
                .name("Speed")
                .build();
        repo.update(updateEntity);
        Animal newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Speed",newEntity.getName());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        Animal deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
