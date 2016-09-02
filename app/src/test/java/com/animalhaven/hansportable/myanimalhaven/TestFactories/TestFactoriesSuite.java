package com.animalhaven.hansportable.myanimalhaven.TestFactories;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Admin on 14/04/2016. Testing Commits
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDonationFactory.class,
        TestScheduleFactory.class,
        TestScheduleTypeFactory.class,
        TestAdoptionFactory.class,
        TestAnimalFactory.class,
        TestLivingAreaFactory.class,
        TestUserFactory.class,
        TestUserRoleFactory.class
})

public class TestFactoriesSuite{

}