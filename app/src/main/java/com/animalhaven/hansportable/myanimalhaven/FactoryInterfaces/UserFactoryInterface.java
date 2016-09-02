package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.User;

import java.util.List;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface UserFactoryInterface {
    User createUser(
            String name,
            String surname,
            String idNumber,
            int adoptions,
            int donations,
            int schedules);
}
