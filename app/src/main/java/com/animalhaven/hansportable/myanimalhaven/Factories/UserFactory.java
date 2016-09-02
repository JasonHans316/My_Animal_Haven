package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.User;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.UserFactoryInterface;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class UserFactory implements UserFactoryInterface {
    public User createUser(
            String name,
            String surname,
            String idNumber,
            int adoptions,
            int donations,
            int schedules)
    {
        return new User.Builder()
                .name(name)
                .idNumber(idNumber)
                .surname(surname)
                .adoptionId(adoptions)
                .donationId(donations)
                .scheduleId(schedules)
                .build();
    }
}
