package com.test.mobile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

public class TransportActivity extends Activity {

    private TextView mDisplayDate;
    private Button mDisplayDateB;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TableLayout tl1;
    private TableRow tr1;
    private TableRow trInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport);
        mDisplayDate = (TextView) findViewById(R.id.dateDisplay);
        mDisplayDateB = (Button) findViewById(R.id.dateB);
        tl1 = (TableLayout) findViewById(R.id.table);
        tr1 = (TableRow) findViewById(R.id.labelRow);
        trInit = (TableRow) findViewById(R.id.object1) ;
        tl1.setColumnStretchable(0,true);
        tl1.setColumnStretchable(1,true);

        //Date selection
        mDisplayDateB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        TransportActivity.this,
                        android.R.style.Theme_Holo_Light_DarkActionBar,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        //adding object to the table

    }

    //---
}
