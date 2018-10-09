package com.har_asha.linearalgebra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
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
 * Created by manikaran on 13/2/17.
 */
public class Topics extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView lv = (ListView) findViewById(R.id.index);
        lv.setAdapter(new MyArrayAdapter(this , R.layout.learn_listview_item1 , R.id.title , getResources().getStringArray(R.array.topics)));
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Topics.this , Details.class);
                i.putExtra("option" , position);
                startActivity(i);
                overridePendingTransition(R.anim.right_in_100p , R.anim.left_out);
            }
        });
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

    class MyArrayAdapter extends ArrayAdapter<String> {
        String[] titles;
        public MyArrayAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);
            titles = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(position % 2 == 1)
                convertView = LayoutInflater.from(Topics.this).inflate(R.layout.learn_listview_item1 , parent , false);
            else
                convertView = LayoutInflater.from(Topics.this).inflate(R.layout.learn_listview_item2 , parent , false);
            TextView index = (TextView) convertView.findViewById(R.id.index);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            int pos = position+1;
            index.setText(String.valueOf(pos));
            title.setText(titles[position]);
            return convertView;
        }
    }
}
