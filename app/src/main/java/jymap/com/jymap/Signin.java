package jymap.com.jymap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Signin extends AppCompatActivity {
    EditText editPassword, editName;
    Button btnSignin, btnRegister;

    static String URL= "https://pfem1uds.herokuapp.com/api/login";

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editName=(EditText) findViewById(R.id.editName);
        editPassword=(EditText) findViewById(R.id.editPassword);

        btnSignin=(Button) findViewById(R.id.btnSignin);
        /*btnRegister=(Button) findViewById(R.id.btnRegister);*/


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(editName.getText().toString(),editPassword.getText().toString());

                editPassword.setText("");
                editPassword.requestFocus();
            }
        });

        /*btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent activiteregister = new Intent(Signin.this,Register.class);
                 startActivity(activiteregister);
             }
         });*/
    }


    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {
            String password = args[1];
            String username= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("email", username));
            params.add(new BasicNameValuePair("password", password));
            Log.e("params",params.toString());
            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

                Log.e("error 1",json==null?"OUI":"NON");

            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result!=null) {
                    /*Log.e("ERROR", result.getString("message"));
                   *//* Toast.makeText(getApplicationContext(),"Successfully logged in",Toast.LENGTH_LONG);**//*
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    if(result.getString("message").equals("Successfully logged in")){

                        Intent registerpoint = new Intent(Signin.this,Localisation.class);
                        startActivity(registerpoint);
                    }*/
                    Log.d("Sign IN : ", "onPostExecute: "+result);
                    Toast.makeText(getApplicationContext(),"Succssfully logged in",Toast.LENGTH_LONG).show();
                    if (result.getString("id") != null){
                        Intent registerpoint = new Intent(Signin.this,Localisation.class);
                        startActivity(registerpoint);
                    }else Toast.makeText(getApplicationContext(), "Uncorrect username or password", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}

