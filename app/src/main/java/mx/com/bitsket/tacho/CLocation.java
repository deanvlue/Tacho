package mx.com.bitsket.tacho;

import android.location.Location;

/**
 * Created by deanvlue on 4/10/15.
 */
public class CLocation extends Location {

    private boolean bUserMetricUnits = false;

    @Override
    public float getAccuracy() {
        float nAccuracy = super.getAccuracy();

        if(!this.getUserMetricUnits()){
            //Convertir metros a pies
            nAccuracy = nAccuracy * 3.28083989501312f;
        }
        return nAccuracy;
    }

    @Override
    public double getAltitude() {
        float nAltitude = (float) super.getAltitude();

        if(!this.getUserMetricUnits()){
            //Convertir metros a pies
            nAltitude = nAltitude * 3.28083989501312f;
        }
        return nAltitude;
    }

    @Override
    public float getSpeed() {
        float nSpeed = super.getSpeed();

        if(!this.getUserMetricUnits()){
            //Convertir metros/ segundo a pies sobre segundo
            nSpeed = nSpeed * 2.2369362920544f;
        }
        return nSpeed;
    }

    @Override
    public float distanceTo(Location dest) {
        float nDistance = super.distanceTo(dest);

        if(!this.getUserMetricUnits()){
            //Convertir metros a pies
            nDistance = nDistance * 3.28083989501312f;
        }
        return nDistance;
    }

    public CLocation(Location location){
        this(location, true);
    }

    public CLocation(Location location, boolean bUserMetricUnits){

        super(location);
        this.bUserMetricUnits = bUserMetricUnits;
    }

    public boolean getUserMetricUnits(){
        return this.bUserMetricUnits;
    }

    public void setUseMetricunits(boolean bUserMetricUnits){
        this.bUserMetricUnits = bUserMetricUnits;
    }


}
