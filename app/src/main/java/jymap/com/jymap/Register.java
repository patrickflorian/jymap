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

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Register extends AppCompatActivity {

    EditText editEmail, editPassword, editName;
    Button btnRegister, btnSignin ;

    static String URL= "http://10.0.2.2/test_android/index.php";
    /*static String URL= "http://1ocalhost/test_android/index.php";**/

    JSONParser jsonParser=new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editEmail=findViewById(R.id.editEmail);
        editName=findViewById(R.id.editName);
        editPassword=findViewById(R.id.editPassword);

        btnRegister=(Button)findViewById(R.id.btnRegister);
        btnSignin=(Button)findViewById(R.id.btnSignin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttemptRegister attemptRegister= new AttemptRegister();
                attemptRegister.execute(editName.getText().toString(),editPassword.getText().toString(),
                        editEmail.getText().toString());
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activiteacceil = new Intent(Register.this,Signin.class);
                startActivity(activiteacceil);
            }
        });

    }
    private class AttemptRegister extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {

            String email = args[2];
            String password = args[1];
            String username= args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {
           /* try {
                if (result != null) {
                    Log.e("ERROR", result.getString("message"));
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
             try {
                if (result != null) {
                    Log.e("ERROR", result.getString("message"));

            Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
            if(result.getString("message").equals("Successfully register")){

                Intent activitesign = new Intent(Register.this,Signin.class);
                startActivity(activitesign) ;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

        }

    }
}
