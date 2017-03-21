package com.example.healthcare.models;

import java.util.List;

import com.example.healthcare.entity.TimelineDO;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karangarg on 21/03/17.
 */

public class TimeLine {

    @SerializedName("timelines")
    List<TimelineDO> timelineDOs;

    public List<TimelineDO> getTimelineDOs() {
        return timelineDOs;
    }

    public void setTimelineDOs(List<TimelineDO> timelineDOs) {
        this.timelineDOs = timelineDOs;
    }
}
