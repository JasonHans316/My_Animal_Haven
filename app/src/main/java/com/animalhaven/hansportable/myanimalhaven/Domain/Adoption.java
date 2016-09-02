package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IAdoption;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class Adoption implements Serializable{
    private String comment;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long adoptionId;
    private Date adoptionDate;

    public Adoption(Builder value)
    {
        this.adoptionId = value.adoptionId;
        this.comment = value.comment;
        this.adoptionDate = value.adoptionDate;
    }

    public String getComment() {
        return comment;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public Date getAdoptionDate() {
        return adoptionDate;
    }

    public static class Builder{
        private String comment;
        private Long adoptionId;
        private Date adoptionDate;

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder id(Long id) {
            System.err.println(id);
            this.adoptionId = id;
            return this;
        }

        public Builder adoptionDate(Date adoptionDate) {
            this.adoptionDate = adoptionDate;
            return this;
        }

        public Builder copy(Adoption value)
        {
            this.adoptionId = value.adoptionId;
            this.comment = value.comment;
            this.adoptionDate = value.adoptionDate;
            return this;
        }

        public Adoption build(){
            Adoption item = new Adoption(this);
            return item;
        }
    }
}
