package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IAnimal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class Animal implements Serializable{
    private String name;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long animalId;
    private int spaceRequired;
    private int weight;
    private int age;
    private String breed;
    private int adoption;
    private int schedules;

    public Animal(Builder value)
    {
        this.animalId = value.animalId;
        this.name = value.name;
        this.age = value.age;
        this.spaceRequired = value.spaceRequired;
        this.weight = value.weight;
        this.breed = value.breed;
        this.adoption = value.adoption;
        this.schedules = value.schedules;
    }

    public String getName() {
        return name;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public int getSpaceRequired() {
        return spaceRequired;
    }

    public int getWeight() {
        return weight;
    }

    public int getSchedules() {
        return schedules;
    }

    public int getAdoption() {
        return adoption;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public static class Builder{
        private Long animalId;
        private String name;
        private int spaceRequired;
        private int weight;
        private int age;
        private String breed;
        private int adoption;
        private int schedules;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(Long id) {
            this.animalId = id;
            return this;
        }

        public Builder spaceRequired(int spaceRequired) {
            this.spaceRequired = spaceRequired;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder adoption(int adoption) {
            this.adoption = adoption;
            return this;
        }

        public Builder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder schedules(int schedules) {
            this.schedules = schedules;
            return this;
        }

        public Builder copy(Animal value)
        {
            this.animalId = value.animalId;
            this.name = value.name;
            this.age = value.age;
            this.spaceRequired = value.spaceRequired;
            this.weight = value.weight;
            this.breed = value.breed;
            this.adoption = value.adoption;
            this.schedules = value.schedules;
            return this;
        }

        public Animal build()
        {
            return new Animal(this);
        }

    }
}
