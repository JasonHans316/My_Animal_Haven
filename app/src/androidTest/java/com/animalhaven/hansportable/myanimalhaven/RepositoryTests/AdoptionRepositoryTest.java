package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.AdoptionRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.AdoptionRepository;

import org.junit.Assert;

import java.sql.Date;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class AdoptionRepositoryTest extends AndroidTestCase {
    private static final String TAG="ADOPTION TEST";
    private Long id;

    public void testAdoptionCRUD() throws Exception{
        AdoptionRepository repo = new AdoptionRepositoryImpl(this.getContext());

        Adoption adoption = new Adoption.Builder()
                .id(id)
                .comment("First Test")
                .adoptionDate(new Date(2016,01,02))
                .build();
        Adoption insertedEntity = repo.save(adoption);
        id = insertedEntity.getAdoptionId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Adoption> adoptions = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",adoptions.size()>0);

        //READ ENTITY
        Adoption entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Adoption updateEntity = new Adoption.Builder()
                .copy(entity)
                .comment("TEST_UPDATE")
                .adoptionDate(new Date(2016,01,02))
                .build();
        repo.update(updateEntity);
        Adoption newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST_UPDATE",newEntity.getComment());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        Adoption deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
