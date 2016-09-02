package com.animalhaven.hansportable.myanimalhaven.Domain;

import com.animalhaven.hansportable.myanimalhaven.DomainInterfaces.IScheduleType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 2016/04/03.
 */
//@Entity
public class ScheduleType implements Serializable{
    private String code;
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleTypeId;
    private String name;
    private boolean active;
    private int scheduleId;

    public ScheduleType(Builder value)
    {
        this.scheduleTypeId = value.id;
        this.code = value.code;
        this.name = value.name;
        this.active = value.active;
        this.scheduleId = value.scheduleId;
    }

    public int getSchedules() {
        return scheduleId;
    }

    public String getCode() {
        return code;
    }

    public Long getScheduleTypeId() {
        return scheduleTypeId;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder{
        String code;
        int scheduleTyeId;
        String name;
        boolean active;
        int scheduleId;
        private Long id;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder scheduleId(int scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder copy(ScheduleType value)
        {
            this.id = value.scheduleTypeId;
            this.code = value.code;
            this.name = value.name;
            this.active = value.active;

            return this;
        }

        public ScheduleType build()
        {
            return new ScheduleType(this);
        }
    }
}
