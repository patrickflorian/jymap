package jymap.com.jymap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Acceuil extends AppCompatActivity {

    Button btnRegister,btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        /*btnRegister=(Button)findViewById(R.id.btnRegister);*/
        btnSign=(Button)findViewById(R.id.btnSign);


        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activitesignin = new Intent(Acceuil.this, Signin.class);
                startActivity(activitesignin);
            }
        });

       /* btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent activiteregister = new Intent(Acceuil.this, Register.class);
                startActivity(activiteregister);
                }
        });*/
        Log.e("AFFI","joel");
    }
}
