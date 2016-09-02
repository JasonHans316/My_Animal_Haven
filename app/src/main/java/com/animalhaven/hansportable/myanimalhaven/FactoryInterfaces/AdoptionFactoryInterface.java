package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;

import java.util.Date;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface AdoptionFactoryInterface {
    Adoption createAdoption(String comment, Date adoptionDate);
}
