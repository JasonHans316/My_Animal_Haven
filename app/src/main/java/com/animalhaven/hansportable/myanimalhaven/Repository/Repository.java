package com.animalhaven.hansportable.myanimalhaven.Repository;

import java.util.Set;

/**
 * Created by Admin on 2016/04/24.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
