
package com.model;


public class MembershipPlans {
    private String name;
    private Float amount;
    private Integer duration;

    public MembershipPlans(String name, Float amount, Integer duration) {
        this.name = name;
        this.amount = amount;
        this.duration = duration;
    }

    public MembershipPlans() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    
}
