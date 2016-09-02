package com.animalhaven.hansportable.myanimalhaven.Services.Implementations;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.Animal;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Repository.Implementations.LivingAreaRepositoryImpl;
import com.animalhaven.hansportable.myanimalhaven.Repository.Interfaces.LivingAreaRepository;

import java.util.ArrayList;

import com.animalhaven.hansportable.myanimalhaven.Services.Interfaces.LivingAreaServiceInterface;

/**
 * Created by Admin on 2016/05/08.
 */
public class LivingAreaServiceImpl extends Service implements LivingAreaServiceInterface {

    final private LivingAreaRepository repository;

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private static LivingAreaServiceImpl service = null;

    public static LivingAreaServiceImpl getInstance()
    {
        if(service == null)
            service = new LivingAreaServiceImpl();
        return service;
    }

    private LivingAreaServiceImpl()
    {
        repository = new LivingAreaRepositoryImpl(App.getAppContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public LivingAreaServiceImpl getService() {
            return LivingAreaServiceImpl.this;
        }
    }

    /*
    *Used to create a new living area for animals
     */
    @Override
    public LivingArea getLivingArea(long id) {
        try{
            return repository.findById(id);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *Used to create a new living area for animals
     */
    @Override
    public LivingArea createLivingArea(LivingArea value) {
        try{
            return repository.save(value);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *Used to create a new living area for animals
     */
    @Override
    public LivingArea updateLivingArea(LivingArea value) {
        try{
            LivingArea mine = repository.update(value);
            return mine;
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    *Used to create a new living area for animals
     */
    @Override
    public LivingArea deleteLivingArea(LivingArea value) {
        try{
            return repository.delete(value);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /*
    * Used to find which living area has space for the animal selected
    * */
    @Override
    public LivingArea findAvailability(int size) {
        try{
            ArrayList<LivingArea> myList = getLivingAreas();


            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getSpaceAvailable() > size)
                    return myList.get(i);
            return new LivingArea.Builder().build();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }

    /**
     * Used for returning a list of living areas
     * */
    @Override
    public ArrayList<LivingArea> getLivingAreas() {
        try{
            ArrayList<LivingArea> result = new ArrayList<>();
            if(result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<LivingArea>();
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }
/**
 * Used to house animal in the selected living area updating the animal and living area records
 * */
    @Override
    public boolean houseAnimal(LivingArea area, Animal animal) {
        try{

            area = new LivingArea.Builder().copy(area)
                    .spaceAvailable(area.getSpaceAvailable()-animal.getSpaceRequired())
                    .animalId(animal.getAnimalId().intValue())
                    .build();
            return repository.update(area) == null;
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return false;
    }
/**
 * Used to move an animal from one location to another
 * */
    @Override
    public LivingArea relocateAnimal(Animal animal) {
        try{
            ArrayList<LivingArea> myList = getLivingAreas();

            LivingArea current = null;

            for(int i=0; i<myList.size(); i++)
                if(myList.get(i).getAnimal() == animal.getAnimalId().intValue()) {
                    current = myList.get(i);
                    i = myList.size()+2;
                }
            if(houseAnimal(current, animal))
                for(int i=0; i<myList.size(); i++)
                    if(myList.get(i).getAnimal() == animal.getAnimalId().intValue()
                            && current != myList.get(i)) {
                        current = myList.get(i);
                        break;
                    }

            if(current.getAnimal() != animal.getAnimalId().intValue())
                return current;
            else
                return new LivingArea.Builder().build();

        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        return null;
    }
}
