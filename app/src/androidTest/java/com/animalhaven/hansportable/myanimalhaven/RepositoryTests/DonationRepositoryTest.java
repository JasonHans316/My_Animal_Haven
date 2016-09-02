package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import android.test.AndroidTestCase;

import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.DonationRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.DonationRepository;

import org.junit.Assert;

import java.sql.Date;
import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public class DonationRepositoryTest extends AndroidTestCase {
    private static final String TAG="DONATION TEST";
    private Long id;

    public void testDonationCRUD() throws Exception{
        DonationRepository repo = new DonationRepositoryImpl(this.getContext());

        Donation donation = new Donation.Builder()
                .id(id)
                .comment("Donation Test")
                .donationDate(new Date(2016,01,02))
                .amount(15)
                .build();
        Donation insertedEntity = repo.save(donation);
        id = insertedEntity.getDonationId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Donation> donations = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",donations.size()>0);

        //READ ENTITY
        Donation entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Donation updateEntity = new Donation.Builder()
                .copy(entity)
                .comment("TEST_UPDATE")
                .build();
        repo.update(updateEntity);
        Donation newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST_UPDATE",newEntity.getComment());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        Donation deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
