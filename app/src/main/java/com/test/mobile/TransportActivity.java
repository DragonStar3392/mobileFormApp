package com.test.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.test.mobile.data.Item;
import com.test.mobile.data.itemComparator;
import com.test.mobile.data.itemTableDataAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransportActivity extends Activity {

    private TextView mDisplayDate;
    private Button mDisplayDateB;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Spinner pGroupSP;
    private Spinner pNoSP;

    private SortableTableView table;
    private List<Item> itemList = new ArrayList<Item>();
    private itemTableDataAdapter itemTableDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_transport);
        mDisplayDate = (TextView) findViewById(R.id.dateDisplay);
        mDisplayDateB = (Button) findViewById(R.id.dateB);

        pGroupSP = (Spinner) findViewById(R.id.projGspinner);
        pNoSP = (Spinner) findViewById(R.id.projNospinner);

        table = (SortableTableView<String>) findViewById(R.id.transportTable);
        initTable();

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        //init projNo
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<String> projNo = new ArrayList<String>();

                try {
                    //converting the string to json array object
                    JSONArray array = new JSONArray(response);

                    if(!array.isNull(0)){
//                        AlertDialog.Builder builder = new AlertDialog.Builder(TransportActivity.this);
//                        builder.setMessage("update success")
//                                .setNegativeButton("dunno", null)
//                                .create()
//                                .show();

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject product = array.getJSONObject(i);

                            //populate projNo spinner,  if contain same group name
                            if(!projNo.contains(product.getString("projNo")))
                                projNo.add(product.getString("projNo"));
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransportActivity.this, android.R.layout.simple_spinner_item, projNo);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        pNoSP.setAdapter(adapter);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TransportActivity.this);
                        builder.setMessage("no project")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        transportInstallRequest projNoRequest = new transportInstallRequest(username,responseListener);
        RequestQueue queue = Volley.newRequestQueue(TransportActivity.this);
        queue.add(projNoRequest);

        //Date selection---------------------------------------------------------------------------
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
        //-----------------------------------------------------------------------------------------
//        pGroupSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayAdapter groupA = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames);
//                groupA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                //Setting the ArrayAdapter data on the Spinner
//                pGroupSP.setAdapter(groupA);
//            }
//        });
//        //-----------------------------------------------------------------------------------------
        pNoSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //init initial data
                Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println(response);
                            resetTable();
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            if(!array.isNull(0)){
                                AlertDialog.Builder builder = new AlertDialog.Builder(TransportActivity.this);
                                builder.setMessage("data get success")
                                        .setNegativeButton("dunno", null)
                                        .create()
                                        .show();

                                //traversing through all the object
                                //System.out.println(array.length()); total items
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject product = array.getJSONObject(i);
                                    //populate projNo spinner
                                    itemList.add(new Item(product.getInt("ID"),
                                            product.getString("item"),
                                            product.getInt("qty"),
                                            product.getString("site"),
                                            product.getString("warehouse"),
                                            product.getString("location"),
                                            product.getString("batch"),
                                            product.getString("serial")));
                                }
                                builder.setMessage(itemList.toString())
                                        .create()
                                        .show();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(TransportActivity.this);
                                builder.setMessage("no data")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                transportInstallRequest projDataRequest = new transportInstallRequest(username,pNoSP.getSelectedItem().toString(),responseListener2);
                RequestQueue queue = Volley.newRequestQueue(TransportActivity.this);
                queue.add(projDataRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * Initialize the Sortable table view with data from list
     */
    private void initTable() {

        //Set the width of the table.
        TableColumnWeightModel columnModel = new TableColumnWeightModel(8);
        columnModel.setColumnWeight(0, 2);//id
        columnModel.setColumnWeight(1, 5);//item
        columnModel.setColumnWeight(2, 3);//qty
        columnModel.setColumnWeight(3, 3);//site
        columnModel.setColumnWeight(4, 3);//warehouse
        columnModel.setColumnWeight(5, 3);//location
        columnModel.setColumnWeight(6, 3);//batch
        columnModel.setColumnWeight(7, 4);//serial
        table.setColumnModel(columnModel);

        //Add option to table.
        //itemList.add(new Item(1,"Test1",3,"Bangkok","01","1","1","111"));

        itemTableDataAdapter = new itemTableDataAdapter(this, itemList, table);
        table.setHeaderAdapter(new SimpleTableHeaderAdapter(this, itemTableDataAdapter.getHeaderData()));
        table.setDataAdapter(itemTableDataAdapter);
        table.setColumnComparator(0, itemComparator.idComparator);
        table.setColumnComparator(1, itemComparator.nameComparator);
        table.setColumnComparator(2, itemComparator.qtyComparator);
        table.setColumnComparator(3, itemComparator.siteComparator);
        table.setColumnComparator(6, itemComparator.batchComparator);

    }

    /**
     * Reset table for all row.
     */
    private void resetTable() {

        for (int i = 0; i < itemList.size(); i++) {
            itemList.clear();
        }
        itemTableDataAdapter.notifyDataSetChanged();
    }
}

    /**
     * Update the table by getting itemList from the server.
     */
//    private void updateTableMenu() {
//        MenuDownloader menuDownloader = new MenuDownloader();
//        menuDownloader.execute(ipVal);
//    }
