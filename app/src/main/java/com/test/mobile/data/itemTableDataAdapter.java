package com.test.mobile.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;

public class itemTableDataAdapter extends TableDataAdapter<Item> {
    
    private static final int TEXT_SIZE = 12;
    private static final String[] TABLE_HEADERS = {"Id","Item","Qty", "Site", "WH","Loc","Batch","Serial"};

    public itemTableDataAdapter(final Context context, final List<Item> data, final TableView<Item> tableView) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        final Item item = getRowData(rowIndex);
        View renderedView = null;

        switch (columnIndex) {
            case 0:
                renderedView = renderString(item.getStatus());
                break;
            case 1:
                renderedView = renderString(item.getName());
                break;
            case 2:
                renderedView = renderString(String.format("%.2f", item.getQuantity()));
                break;
            case 3:
                renderedView = renderString(item.getSite());
                break;
            case 4:
                renderedView = renderString(item.getWH());
                break;
            case 5:
                renderedView = renderString(item.getLocation());
                break;
            case 6:
                renderedView = renderString(item.getBatch());
                break;
            case 7:
                renderedView = renderString(item.getSerial());
                break;
        }

        return renderedView;
    }

    private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(TEXT_SIZE);
        return textView;
    }

    public String[] getHeaderData(){
        return TABLE_HEADERS;

    }
}
