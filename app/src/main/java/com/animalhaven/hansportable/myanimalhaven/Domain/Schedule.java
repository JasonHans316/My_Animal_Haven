package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.ISchedule;

import java.io.Serializable;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class Schedule implements Serializable{
    private String activity;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;
    private int timeRequired;

    public Schedule() {
    }

    public Schedule(Builder value)
    {
        this.scheduleId = value.id;
        this.activity = value.activity;
        this.timeRequired = value.timeRequired;
    }
    
    public String getActivity() {
        return activity;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public static class Builder{
        String activity;
        int timeRequired;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder activity(String activity) {
            this.activity = activity;
            return this;
        }


        public Builder timeRequired(int timeRequired) {
            this.timeRequired = timeRequired;
            return this;
        }

        public Builder copy(Schedule value)
        {
            this.id = value.scheduleId;
            this.activity = value.activity;
            this.timeRequired = value.timeRequired;
            return this;
        }

        public Schedule build(){
            return new Schedule(this);
        }
    }
}
