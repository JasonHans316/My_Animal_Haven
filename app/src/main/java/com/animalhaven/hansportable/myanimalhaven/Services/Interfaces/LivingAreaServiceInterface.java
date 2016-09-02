package com.animalhaven.hansportable.myanimalhaven.Services.Interfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/05/08.
 */
public interface LivingAreaServiceInterface {
    LivingArea createLivingArea(LivingArea value);
    LivingArea updateLivingArea(LivingArea value);
    LivingArea deleteLivingArea(LivingArea value);
    LivingArea findAvailability(int size);
    boolean houseAnimal(LivingArea area, Animal animal);
    ArrayList<LivingArea> getLivingAreas();
    LivingArea getLivingArea(long id);
    LivingArea relocateAnimal(Animal animal);
}
