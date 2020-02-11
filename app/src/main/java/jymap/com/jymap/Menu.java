package jymap.com.jymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
ImageView txtaddmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtaddmap=(ImageView)findViewById(R.id.txtaddmap);

        txtaddmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activitemap = new Intent(Menu.this,Localisation.class);
                startActivity(activitemap);

            }
        });

    }
}
