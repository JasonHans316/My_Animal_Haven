package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;

import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
public interface ILivingArea {
    Long getLivingAreaId();
    String getName();
    String getCode();
    boolean isActive();
    int getSpaceAvailable();
    List<Animal> getAnimals();

}
