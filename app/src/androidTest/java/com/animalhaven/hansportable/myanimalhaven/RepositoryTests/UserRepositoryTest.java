package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.UserRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.UserRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class UserRepositoryTest extends AndroidTestCase {
    private static final String TAG="USER TEST";
    private Long id;

    public void testUserCRUD() throws Exception{
        UserRepository repo = new UserRepositoryImpl(this.getContext());

        User scheduleType = new User.Builder()
                .id(id)
                .name("Jason")
                .surname("Hans")
                .idNumber("6655214877325")
                .donationId(2)
                .adoptionId(1)
                .scheduleId(6)
                .build();
        User insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getUserId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<User> users = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",users.size()>0);

        //READ ENTITY
        User entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        User updateEntity = new User.Builder()
                .copy(entity)
                .surname("HanSolo")
                .build();
        repo.update(updateEntity);
        User newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","HanSolo",newEntity.getSurname());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        User deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
