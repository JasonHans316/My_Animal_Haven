package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public interface IAnimal {
    String getName();
    Long  getAnimalId();
    int getSpaceRequired();
    int getWeight();
    int getAge();
    String getBreed();
    List<Schedule> getSchedules();
    Adoption getAdoption();
}
