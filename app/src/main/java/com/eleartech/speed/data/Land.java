package com.eleartech.speed.data;

/**
 * Created by junverarcayna on 21/10/2017.
 */

public class Land {

    public String season;

    public float irrigatedArea;
    public float rainfedArea;
    public float totalArea;

    public String ownership;

    public Land() {}

    public Land(String season, float irrigatedArea, float rainfedArea, float totalArea, String ownership) {
        this.season = season;
        this.irrigatedArea = irrigatedArea;
        this.rainfedArea = rainfedArea;
        this.totalArea = totalArea;
        this.ownership = ownership;
    }
}
