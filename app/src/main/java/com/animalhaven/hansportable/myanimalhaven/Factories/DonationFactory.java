package com.animalhaven.hansportable.myanimalhaven.Factories;

import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;
import com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces.DonationFactoryInterface;

import java.sql.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public class DonationFactory implements DonationFactoryInterface {
    public Donation createDonation(
            Date donationDate,
            int amount,
            String comment)
    {
        return new Donation.Builder()
                .donationDate(donationDate)
                .amount(amount)
                .comment(comment)
                .build();
    }
}
