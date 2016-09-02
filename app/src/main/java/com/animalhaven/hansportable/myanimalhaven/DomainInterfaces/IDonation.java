package com.animalhaven.hansportable.myanimalhaven.DomainInterfaces;

import java.sql.Date;

/**
 * Created by Admin on 2016/04/03.
 */
public interface IDonation {
    Date getDonationDate();
    Long getDonationId();
    int getAmount();
    String getComment();
}
