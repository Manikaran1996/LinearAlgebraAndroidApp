package com.har_asha.linearalgebra;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by manikaran on 17/2/17.
 */

public class OperationsList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operations_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff045676)); // red 0xffcc2936
        Resources resources = getResources();
        String[] operations = resources.getStringArray(R.array.operations);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListViewAdapter adapter = new ListViewAdapter(this , R.layout.listview_item1 , R.id.title , operations);
        ListView listView = (ListView) findViewById(R.id.operationsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    class ListViewAdapter extends ArrayAdapter<String> {

        private String[] titles;
        public ListViewAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);
            titles = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(position % 2 == 0)
                convertView = LayoutInflater.from(OperationsList.this).inflate(R.layout.listview_item1 , parent , false);
            else
                convertView = LayoutInflater.from(OperationsList.this).inflate(R.layout.listview_item2 , parent , false);
            ((TextView) convertView.findViewById(R.id.title)).setText(titles[position]);
            int pos = position+1;
            ((TextView) convertView.findViewById(R.id.index)).setText(String.valueOf(pos));
            return convertView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*Intent intent = new Intent(this , Order.class);
        startActivity(intent);*/
        OrderDialog orderDialog = OrderDialog.getInstance(position);
        orderDialog.show(getSupportFragmentManager() , "orderDialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.left_in_100p,R.anim.right_out);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in_100p,R.anim.right_out);
    }
}
