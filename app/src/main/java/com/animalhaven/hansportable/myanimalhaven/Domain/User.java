package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class User implements Serializable{
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String surname;
    private String idNumber;
    private int adoptionId;
    private int donationId;
    private int scheduleId;

    public User(Builder value)
    {
        this.userId = value.id;
        this.name = value.name;
        this.idNumber = value.idNumber;
        this.surname = value.surname;
        this.adoptionId = value.adoptionId;
        this.donationId = value.donationId;
        this.scheduleId = value.scheduleId;
    }

    public int getAdoption() {
        return adoptionId;
    }

    public int getDonation() {
        return donationId;
    }

    public int getSchedule() {
        return scheduleId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdNumber() {
        return idNumber;
    }


    public static class Builder {
        String name;
        String surname;
        String idNumber;
        int adoptionId;
        int donationId;
        int scheduleId;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder adoptionId(int adoptionId) {
            this.adoptionId = adoptionId;
            return this;
        }

        public Builder donationId(int donationId) {
            this.donationId = donationId;
            return this;
        }

        public Builder scheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder idNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder copy(User value)
        {
            this.id = value.userId;
            this.name = value.name;
            this.idNumber = value.idNumber;
            this.surname = value.surname;
            this.adoptionId = value.adoptionId;
            this.donationId = value.donationId;
            this.scheduleId = value.scheduleId;
            return this;
        }

        public User build()
        {
            return new User(this);
        }
    }
}
