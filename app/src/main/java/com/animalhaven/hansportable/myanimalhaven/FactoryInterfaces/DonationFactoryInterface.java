package com.animalhaven.hansportable.myanimalhaven.FactoryInterfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;

import java.sql.Date;

/**
 * Created by Hans Portable on 4/7/2016.
 */
public interface DonationFactoryInterface {
    Donation createDonation(
            Date donationDate,
            int amount,
            String comment);
}
