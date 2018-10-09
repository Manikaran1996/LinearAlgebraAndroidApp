package com.har_asha.linearalgebra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by manikaran on 16/2/17.
 */

public class Order extends AppCompatActivity {
    private EditText et1 , et2;
    private int rows , cols;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        Button button = (Button) findViewById(R.id.next);
        et1 = (EditText) findViewById(R.id.rows);
        et2 = (EditText) findViewById(R.id.cols);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    rows = Integer.parseInt((et1.getText()).toString().trim());
                }
                catch (NumberFormatException e) {
                    Toast.makeText(Order.this , "" , Toast.LENGTH_SHORT).show();
                }
                try {
                    cols = Integer.parseInt((et2.getText()).toString().trim());
                    Intent i = new Intent(Order.this , MatrixInput.class);
                    i.putExtra("rows" , rows);
                    i.putExtra("cols" , cols);
                    startActivity(i);
                }
                catch (NumberFormatException e) {

                }
            }
        });
    }
}
