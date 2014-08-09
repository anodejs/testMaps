package com.example.administrator.myapplication;

import java.util.Date;

/**
 * Created by amitmach on 8/9/2014.
 */
public class Place {
    private Date m_date;
    private String m_name;
    private String m_address;
    private double m_latitude;
    private double m_longitude;
    private int m_numberOfPeople;

    public Place(Date time, String name, String address, double latitude , double longitude, int numberOfPeople)
    {
        m_date = time;
        m_name = name;
        m_address = address;
        m_latitude = latitude;
        m_longitude = longitude;
        m_numberOfPeople = numberOfPeople;
    }

    public String getName() {
        return m_name;
    }

    public String getAddress() {
        return m_address;
    }

    public double getLatitude() {
        return m_latitude;
    }
    public double getLongitude() {
        return m_longitude;
    }


    public Date getTime() {
        return m_date;
    }

    public int getNumberOfPeople() {return m_numberOfPeople;}

}
