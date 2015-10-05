package mx.com.bitsket.tacho;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends Activity implements IBaseGpsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        this.updateSpeed(null);

        CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
        chkUseMetricUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.this.updateSpeed(null);
            }
        });

    }

    public void finish(){
        super.finish();
        System.exit(0);
    }

    public void updateSpeed(CLocation location){
        float nCurrentSpeed = 0;
        float nSpeedKmHr = 0;

        if(location != null){
            location.setUseMetricunits(this.useMetricUnits());
            nCurrentSpeed = location.getSpeed();
            nSpeedKmHr = nCurrentSpeed * 3.6f;
        }

        Formatter fmt = new Formatter(new StringBuilder());

        fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed = strCurrentSpeed.replace(' ','0');

        Formatter fmtKH = new Formatter(new StringBuilder());
        fmtKH.format(Locale.US, "%5.1f", nSpeedKmHr);
        String strCurrentSpeedKmHr = fmtKH.toString();
        strCurrentSpeedKmHr = strCurrentSpeedKmHr.replace(' ','0');

        String strUnits = "miles/hour";
        if(this.useMetricUnits()){
            strUnits = "m/s";

        }

        TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
        txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnits);
        TextView txtCurrentSpeedKmHr = (TextView) this.findViewById(R.id.txtCurrentSpeedKmHr);
        txtCurrentSpeedKmHr.setText(strCurrentSpeedKmHr + " km/h");
    }

    private boolean useMetricUnits(){
        CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
        return chkUseMetricUnits.isChecked();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            CLocation myLocation = new CLocation(location, this.useMetricUnits());
            this.updateSpeed(myLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onGpsStatusChanged(int event) {
            // TODO Auto-generated method stub

        }

}