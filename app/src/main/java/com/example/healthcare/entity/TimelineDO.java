package com.example.healthcare.entity;

/**
 * Created by karangarg on 20/03/17.
 */

public class TimelineDO {

    public enum Type {
        BEFORE_BIRTH,
        AFTER_BIRTH
    };

    private String timeline;
    private Type type;
    private String nutrition;
    private String suppliments;
    private String vaccination;

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getSuppliments() {
        return suppliments;
    }

    public void setSuppliments(String suppliments) {
        this.suppliments = suppliments;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }
}
