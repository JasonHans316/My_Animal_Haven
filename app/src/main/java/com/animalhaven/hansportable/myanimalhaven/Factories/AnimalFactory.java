package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.AnimalFactoryInterface;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class AnimalFactory implements AnimalFactoryInterface{
    public Animal createAnimal(String name,
            int spaceRequired,
            int weight,
            int age,
            int adoption,
            int schedules,
            String breed)
    {
        return new Animal.Builder()
                .name(name)
                .age(age)
                .spaceRequired(spaceRequired)
                .weight(weight)
                .breed(breed)
                .adoption(adoption)
                .schedules(schedules)
                .build();
    }
}
