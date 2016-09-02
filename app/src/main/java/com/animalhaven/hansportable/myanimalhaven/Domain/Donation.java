package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IDonation;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class Donation implements Serializable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long donationId;
    private Date donationDate;
    private int amount;
    private String comment;

    public Donation(Builder value)
    {
        this.donationId = value.id;
        this.amount = value.amount;
        this.comment = value.comment;
        this.donationDate = value.donationDate;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public Long getDonationId() {
        return donationId;
    }

    public int getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public static class Builder {

        Date donationDate;
        int amount;
        String comment;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder donationDate(Date donationDate) {
            this.donationDate = donationDate;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder copy(Donation value){
            this.id = value.donationId;
            this.amount = value.amount;
            this.comment = value.comment;
            this.donationDate = value.donationDate;
            return this;
        }

        public Donation build()
        {
            return new Donation(this);
        }
    }
}
