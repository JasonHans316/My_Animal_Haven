package com.animalhaven.hansportable.myanimalhaven.TestFactories;


import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.Schedule;
import com.animalhaven.hansportable.myanimalhaven.Factories.AnimalFactory;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.AnimalFactoryInterface;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public class TestAnimalFactory {

    @Test
    public void testCreate()
    {
        AnimalFactoryInterface factory = new AnimalFactory();
        Animal original = factory.createAnimal("Sunshine", 13, 20, 3, 1, 2, "Dog");

        Assert.assertEquals(original.getAge(), 3);
    }


    @Test
    public void testUpdate()
    {
        AnimalFactoryInterface factory = new AnimalFactory();
        Animal original = factory.createAnimal("Sunshine", 13, 20, 3, 1, 2, "Dog");
        Animal copy = new Animal.Builder().copy(original).age(4).build();

        Assert.assertEquals(original.getAge(), 3);
        Assert.assertEquals(copy.getAge(), 4);
        Assert.assertEquals(copy.getBreed(), original.getBreed());
    }

}
