package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.ILivingArea;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class LivingArea implements Serializable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long livingAreaId;
    private String name;
    private String code;
    private boolean active;
    private int spaceAvailable;
    private int animalId;

    public LivingArea(Builder value)
    {
        this.livingAreaId = value.id;
        this.name = value.name;
        this.code = value.code;
        this.active = value.active;
        this.spaceAvailable = value.spaceAvailable;
        this.animalId = value.animalId;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
    }


    public int getAnimal() {
        return animalId;
    }

    public Long getLivingAreaId() {
        return livingAreaId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name ;
    }

    public static class Builder {
        String name;
        String code;
        int spaceAvailable;
        boolean active;
        int animalId;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder spaceAvailable(int spaceAvailable) {
            this.spaceAvailable = spaceAvailable;
            return this;
        }

        public Builder animalId(int animalId) {
            this.animalId = animalId;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder copy(LivingArea value)
        {
            this.id = value.livingAreaId;
            this.name = value.name;
            this.code = value.code;
            this.active = value.active;
            this.spaceAvailable = value.spaceAvailable;
            this.animalId = value.animalId;
            return this;
        }

        public LivingArea build()
        {
            return new LivingArea(this);
        }
    }
}
