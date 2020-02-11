package jymap.com.jymap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Authorized extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized);
        String name = null, password = null;
        Intent activitemenu = getIntent();
       // if (activitemenu != null) {
         //   String strname = "";
           // if (activitemenu.hasExtra("name")) {
             //   strname = activitemenu.getStringExtra("name");
            //}

            //String strpassword = "";
            //strpassword = activitemenu.getStringExtra("strpassword");
        //}
        //TextView username = (TextView) findViewById(R.id.username);

        //username.setText(strname);
        name = getIntent().getExtras().getString("strname");
        password = getIntent().getExtras().getString("strpassword");
        TextView usernameTv = findViewById(R.id.username);
        usernameTv.setText(name);
        TextView passwordTv =  findViewById(R.id.password);
        passwordTv.setText(password);
    }
}
