package com.animalhaven.hansportable.myanimalhaven.Services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.AnimalRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.AnimalRepository;

import java.util.ArrayList;
import java.util.Set;

import com.animalhaven.hansportable.myanimalhaven.Services.Interfaces.AnimalServiceInterface;


/**
 * Created by Admin on 2016/05/08.
 */
public class AnimalServiceImpl extends Service implements AnimalServiceInterface {

    final private AnimalRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static AnimalServiceImpl service = null;

    public static AnimalServiceImpl getInstance()
    {
        if(service == null)
            service = new AnimalServiceImpl();
        return service;
    }

    private AnimalServiceImpl()
    {
        repository = new AnimalRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public AnimalServiceImpl getService() {
            return AnimalServiceImpl.this;
        }
    }

    /*
    *Used to add an animal to the database
    *
     */
    @Override
    public Animal storeAnimal(Animal animal) {
        try{
            return repository.save(animal);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *Used to update details of an animal such as weight
    * or space required
     */
    @Override
    public boolean updateAnimalDetails(Animal animal) {
            try{

                Animal found = repository.findById(animal.getAnimalId());
                Animal updatedAnimal = new Animal.Builder()
                        .adoption(found.getAdoption())
                        .age(found.getAge())
                        .breed(found.getBreed())
                        .id(found.getAnimalId())
                        .name(found.getName())
                        .schedules(found.getSchedules())
                        .spaceRequired(found.getSpaceRequired())
                        .weight(found.getWeight())
                        .build();
                return repository.update(updatedAnimal).getAnimalId() == animal.getAnimalId();
            }
            catch(Exception x)
            {
                x.printStackTrace();
            }
        return false;
        }

    /*
    *Used when an employee needs to find details about an animal and not sure what the name is
    * Returns a list of animals containing the name searched for
     */
    @Override
    public ArrayList<Animal> findByName(String name) {
        try{
            ArrayList<Animal> myList = new ArrayList<>();
            ArrayList<Animal> result = new ArrayList<>();
            Set<Animal> mySet = repository.findAll();

            if(!myList.addAll(mySet))
                return null;

            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getName().equalsIgnoreCase(name))
                    result.add(myList.get(i));

            if(result.size() > 1)
                return result;
            else
                return new ArrayList<Animal>();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }
}
