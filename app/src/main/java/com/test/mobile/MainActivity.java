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
        setContentView(R.layout.main);

        b1 = (Button)findViewById(R.id.transportB);;
        b2 = (Button)findViewById(R.id.installationB);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toTransport();
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                toInstallation();
            }
        });
    }
    public void toTransport(){
        Intent intent = new Intent(MainActivity.this, TransportActivity.class);
        startActivity(intent);
    }
    public void toInstallation(){
        Intent intent = new Intent(MainActivity.this, InstallmentActivity.class);
        startActivity(intent);
    }
}
