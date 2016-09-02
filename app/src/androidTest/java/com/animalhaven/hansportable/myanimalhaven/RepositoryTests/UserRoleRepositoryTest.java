package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.UserRole;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.UserRoleRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.UserRoleRepository;

import org.junit.Assert;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class UserRoleRepositoryTest extends AndroidTestCase {
    private static final String TAG="USER ROLE TEST";
    private Long id;

    public void testUserRoleCRUD() throws Exception{
         UserRoleRepository repo = new UserRoleRepositoryImpl(this.getContext());

        UserRole scheduleType = new UserRole.Builder()
                .id(id)
                .code("L001")
                .name("Elephant's Lunch")
                .userId(1)
                .build();
        UserRole insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getUserRoleId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<UserRole> types = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",types.size()>0);

        //READ ENTITY
        UserRole entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        UserRole updateEntity = new UserRole.Builder()
                .copy(entity)
                .code("L002")
                .build();
        repo.update(updateEntity);
        UserRole newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","L002",newEntity.getCode());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        UserRole deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
