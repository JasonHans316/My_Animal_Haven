package com.animalhaven.hansportable.myanimalhaven.Services.Interfaces;

import com.animalhaven.hansportable.myanimalhaven.Domain.Donation;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/05/08.
 */
public interface DonationServiceInterface {
    Donation makeDonation(Donation newDonation);
    ArrayList<Donation> donationsAbove(int amount);
    ArrayList<Donation> donationsBelow(int amount);
    ArrayList<Donation> donationsEqualTo(int amount);
}
