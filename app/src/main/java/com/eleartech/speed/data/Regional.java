package com.eleartech.speed.data;

/**
 * Created by junverarcayna on 22/10/2017.
 */

public class Regional {

    public String province;
    public boolean isComplete;
    public float sacksToAllocate;
    public float sacksInspected;
    public float totalHectares;
    public boolean inspectionComplete;
    public boolean allocationComplete;

    public Regional() {}

    public Regional(String province, boolean isComplete, float sacksToAllocate, float sacksInspected, float totalHectares, boolean inspectionComplete, boolean allocationComplete) {
        this.province = province;
        this.isComplete = isComplete;
        this.sacksToAllocate = sacksToAllocate;
        this.sacksInspected = sacksInspected;
        this.totalHectares = totalHectares;
        this.inspectionComplete = inspectionComplete;
        this.allocationComplete = allocationComplete;
    }
}
