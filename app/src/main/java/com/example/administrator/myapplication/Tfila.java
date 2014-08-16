package com.example.administrator.myapplication;

import java.util.Date;

/**
 * Created by amitmach on 8/16/2014.
 */
public class Tfila {

    public Type get_type() {
        return _type;
    }

    public Date get_startTime() {
        return _startTime;
    }

    public Date get_endTime() {
        return _endTime;
    }

    public String get_location() {
        return _location;
    }

    public int get_radius() {
        return _radius;
    }

    public int get_day() {
        return _day;
    }

    public enum Type {
        Saharit,
        Minha,
        Aravit
    }

    private Type _type;
    private Date _startTime;
    private Date _endTime;
    private String _location;
    private int _radius;
    private int _day;

    public Tfila(Type type, Date startTime, Date endTime, String location, int radius, int day) {
        this._type = type;
        this._startTime = startTime;
        this._endTime = endTime;
        this._location = location;
        this._radius = radius;
        this._day = day;
    }






}
