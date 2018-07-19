package com.test.mobile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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

import com.test.mobile.data.Item;
import com.test.mobile.data.itemComparator;
import com.test.mobile.data.itemTableDataAdapter;

public class TransportActivity extends Activity {

    private TextView mDisplayDate;
    private Button mDisplayDateB;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Spinner pGroupSP;
    private Spinner pNoSP;

    private SortableTableView table;
    private List<Item> itemList;
    private itemTableDataAdapter itemTableDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_transport);
        mDisplayDate = (TextView) findViewById(R.id.dateDisplay);
        mDisplayDateB = (Button) findViewById(R.id.dateB);

        pGroupSP = (Spinner) findViewById(R.id.projGspinner);
        pNoSP = (Spinner) findViewById(R.id.projNospinner);

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
//        pNoSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //-----------------------------------------------------------------------------------------
//                ArrayAdapter pNoA = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames);
//                pNoA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                //Setting the ArrayAdapter data on the Spinner
//                pNoSP.setAdapter(pNoA);
//            }
//        });
        table = (SortableTableView<String>) findViewById(R.id.transportTable);
        initTable();
        //update table here, firebase?


    }

    /**
     * Initialize the Sortable table view with data from list
     */
    private void initTable() {

        //Set the width of the table.
        TableColumnWeightModel columnModel = new TableColumnWeightModel(7);
        columnModel.setColumnWeight(0, 4);
        columnModel.setColumnWeight(1, 3);
        columnModel.setColumnWeight(2, 6);
        columnModel.setColumnWeight(3, 4);
        columnModel.setColumnWeight(4, 3);
        columnModel.setColumnWeight(5, 3);
        columnModel.setColumnWeight(6, 3);
        table.setColumnModel(columnModel);

        //Add option to table.
        itemList = new ArrayList<Item>();
        itemList.add(new Item(1,"Test1",3,"Bangkok","01","1","1","111"));
        itemList.add(new Item(2,"Test2",1,"Samui","02","2","2","132"));
        itemList.add(new Item(3,"Test3",2,"SS","02","1","2","221"));
        itemList.add(new Item(4,"Test4",6,"S","03","4","4","512"));
        itemList.add(new Item(5,"Test5",90,"EW","04","3","3","432"));
        itemList.add(new Item(6,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(7,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(8,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(9,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(10,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(11,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(12,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(13,"Test6",43,"Jg","03","3","4","2322"));
        itemList.add(new Item(14,"Test6",43,"Jg","03","3","4","2322"));

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
     * Get the order from menu list that quantity isn't zero
     *
     * @param itemList List of Item (itemList)
     * @return ArrayList of Item that quantity isn't zero
     */
    private ArrayList<Item> getOrderedList(List<Item> itemList) {
        ArrayList<Item> orderList = new ArrayList<>();

        for (Item item : itemList) {
            if (item.getQuantity() != 0)
                orderList.add(item);
        }
        Collections.sort(orderList, itemComparator.nameComparator);
        return orderList;
    }

    /**
     * Reset table quantity to 0 for all row.
     */
    private void resetTable() {

        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).setQuantity(0);
        }
        itemTableDataAdapter.notifyDataSetChanged();
    }

    /**
     * Update the table by getting itemList from the server.
     */
//    private void updateTableMenu() {
//        MenuDownloader menuDownloader = new MenuDownloader();
//        menuDownloader.execute(ipVal);
//    }
    /**
     * This class download the itemList from the server.
     * url of the server that want to connect.
     */
//    private class MenuDownloader extends AsyncTask<String, String, String> {
//        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//        private String output;
//        private boolean isSuccess = false;
//
//        @Override
//        protected void onPreExecute() {
//            progressDialog.setMessage("Downloading menu data...");
//            progressDialog.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            HttpURLConnection connection = null;
//            BufferedReader reader = null;
//
//
//            try {
//                URL url = new URL("http://" + params[0] + ":8080/food");
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setConnectTimeout(2000);
//                connection.connect();
//
//
//                InputStream stream = connection.getInputStream();
//
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuffer buffer = new StringBuffer();
//                String line = "";
//
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//                }
//
//                //output = json from server that converted to String
//                output = buffer.toString();
//                isSuccess = true;
//                Thread.sleep(500);
//
//                return output;
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            progressDialog.dismiss();
//
//
//            //if not success, show the dialog and go back to WelcomeActivity
//            if (!isSuccess) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Error while getting menu data from server :(");
//                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//                builder.show();
//                return;
//            }
//
//            Log.d("GetJSONMenu", output);
//
//
//            //Convert String to JSONArray and use Gson Library to itemList List.
//            JSONArray array = null;
//            try {
//                array = new JSONArray(output);
//                itemList.clear();
//
//                if (array.length() > 0) {
//                    Gson gson = new Gson();
//                    int i = 0;
//                    while (i < array.length()) {
//                        itemList.add(gson.fromJson(array.getJSONObject(i).toString(), FoodItem.class));
//                        i++;
//                    }
//                } else {
//                    //if no foodItem object from server
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setMessage("There are no food in menu :(");
//                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    builder.show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Log.d("itemList", itemList.toString());
//            foodItemTableDataAdapter.notifyDataSetChanged();
//        }
//    }
}
