package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    private long id;
    private long userId;
    private TYPE type;
    private Date creationTime;

    public Event(){

    }
    public Event(long userId, TYPE type) {
        this.userId = userId;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getType(){
        return String.valueOf(type);
    }

    public void setType(TYPE type){
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public enum TYPE {
        ENTER,
        LOGOUT
    }
}
