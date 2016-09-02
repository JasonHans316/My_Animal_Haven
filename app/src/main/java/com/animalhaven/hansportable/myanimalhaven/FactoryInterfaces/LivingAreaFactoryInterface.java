package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;

import java.util.List;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface LivingAreaFactoryInterface {
    LivingArea createLivingArea(
            String name,
            String code,
            boolean active,
            int spaceAvailable,
            int animals);
}
