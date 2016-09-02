package com.animalhaven.hansportable.myanimalhaven.RepositoryTests;

import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.ScheduleTypeRepository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Admin on 2016/04/24.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdoptionRepositoryTest.class,
        AnimalRepositoryTest.class,
        DonationRepositoryTest.class,
        LivingAreaRepositoryTest.class,
        ScheduleTypeRepository.class,
        //ScheduleRepositoryTest.class,
        UserRepositoryTest.class,
        UserRoleRepositoryTest.class
})

public class RepositoryTestSuite {
}
