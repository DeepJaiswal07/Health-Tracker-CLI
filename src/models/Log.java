package models;

import interfaces.Trackable;

public abstract class Log implements Trackable {

    protected String date;
    protected String type;

    public Log(String date, String type) {
        this.date = date;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getLogType() {
        return type;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + date;
    }
}