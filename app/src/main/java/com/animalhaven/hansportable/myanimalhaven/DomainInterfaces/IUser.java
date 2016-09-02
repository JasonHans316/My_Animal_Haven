package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public interface IUser {
    Long getUserId();
    String getName();
    String getSurname();
    String getIdNumber();
    List<Donation> getDonations();
    List<Schedule> getSchedules();
    List<Adoption> getAdoptions();
}
