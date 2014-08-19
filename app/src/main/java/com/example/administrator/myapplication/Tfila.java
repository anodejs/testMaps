package com.example.administrator.myapplication;


import android.text.format.Time;

public class Tfila {

    public Type get_type() {
        return _type;
    }

    public Time get_startTime() {
        return _startTime;
    }

    public Time get_endTime() {
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

    public int get_active() {
        return _active;
    }

    public enum Type {
        Saharit,
        Minha,
        Aravit
    }

    private Type _type;
    private Time _startTime;
    private Time _endTime;
    private String _location;
    private int _radius;
    private int _day;
    private int _active;

    public Tfila(Type type, Time startTime, Time endTime, String location, int radius, int day, int active) {

        this._type = type;
        this._startTime = startTime;
        this._endTime = endTime;
        this._location = location;
        this._radius = radius;
        this._day = day;
        this._active = active;
    }






}
