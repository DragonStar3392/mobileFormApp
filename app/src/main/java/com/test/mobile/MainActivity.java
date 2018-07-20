package com.test.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.transportB);
        b2 = (Button)findViewById(R.id.installationB);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        //passing on username for next use
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransportActivity.class);
                intent.putExtra("username", username);
                MainActivity.this.startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InstallmentActivity.class);
                intent.putExtra("username", username);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
