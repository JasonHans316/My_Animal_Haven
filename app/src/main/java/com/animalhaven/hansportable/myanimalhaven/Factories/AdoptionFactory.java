package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.Adoption;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.AdoptionFactoryInterface;

import java.util.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class AdoptionFactory implements AdoptionFactoryInterface {
    public Adoption createAdoption(String comment,
                                   Date adoptionDate)
    {
        return new Adoption.Builder()
                .comment(comment)
                .adoptionDate(adoptionDate)
                .build();
    }
}
