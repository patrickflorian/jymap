package jymap.com.jymap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

public class Localisation extends AppCompatActivity {

    EditText txtCity, txtNamepoint;
    Button btnRegisterpoint, btnShowmap;
    Double Latitude, Longitude;
    static String URL = "https://pfem1uds.herokuapp.com/api/location";

    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localisation);

        txtCity=(EditText)findViewById(R.id.txtCity);
        txtNamepoint=(EditText)findViewById(R.id.txtNamepoint);
        btnRegisterpoint=(Button)findViewById(R.id.btnRegisterpoint);
        /*btnShowmap=(Button)findViewById(R.id.btnShowmap);*/

        btnRegisterpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location loc = getPosition();
                if (loc == null) {
                    Toast.makeText(Localisation.this,"erreur de recuperaton",Toast.LENGTH_SHORT).show();
                    Log.d("LocalisationActivity", "onClick: impossible de recuperer votre position");
                }else{
                    Toast.makeText(getApplicationContext(),"Successfully Register",Toast.LENGTH_LONG).show();
                AttemptRegister attemptRegister = new AttemptRegister();
                attemptRegister.execute(txtCity.getText().toString(),txtNamepoint.getText().toString(),String.valueOf(loc.getLongitude()),String.valueOf(loc.getLatitude()));
            }
                txtCity.setText("");
                txtCity.requestFocus();

                txtNamepoint.setText("");
                txtNamepoint.requestFocus();

        }

        });



    }

    public Location getPosition(){
        LocationManager lm;

        Location loc = null;
        try {
            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates("gps", 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
            loc = lm.getLastKnownLocation("gps");
            if (loc == null) {
                    loc = lm.getLastKnownLocation("network");
            }
            return  loc;
        }catch(SecurityException e){
            Toast.makeText(this,
                    "Erreur de Securite ...(onclick)" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            Log.e("securityerror",e.getMessage());
        }catch(Exception e){
            Toast.makeText(this,
                    "Impossible",
                    Toast.LENGTH_SHORT).show();
        }

        return null;
    }
    private class AttemptRegister extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {
            String txtCity = args[0];
            String txtNamepoint= args[1];
            String Longitude = args[2];
            String Latitude = args[3];


            ArrayList params = new ArrayList();
            JSONObject location = new  JSONObject();

            params.add(new BasicNameValuePair("longitude", Longitude));
            params.add(new BasicNameValuePair("latitude", Latitude));
            params.add(new BasicNameValuePair("description", txtNamepoint));
            params.add(new BasicNameValuePair("classroom", txtCity));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

            Log.e("error 1",json.toString());

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Log.e("ERROR", result.getString("message"));
                     Toast.makeText(getApplicationContext(),"Enregistrement Reussis",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
