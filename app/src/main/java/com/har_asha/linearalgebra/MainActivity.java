package com.har_asha.linearalgebra;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(options);
        setContentView(R.layout.activity_main);
        TextView learn = (TextView) findViewById(R.id.learn);
        TextView practice = (TextView) findViewById(R.id.practice);
        TextView solve = (TextView) findViewById(R.id.solve);
        TextView exit = (TextView) findViewById(R.id.exit);
        TextView title = (TextView) findViewById(R.id.title);
        Typeface tf = Typeface.createFromAsset(getAssets() , "Roboto-Bold.ttf");
        title.setTypeface(tf);
        Animation in1 = AnimationUtils.loadAnimation(this , R.anim.left_in);
        in1.setInterpolator(new AccelerateInterpolator());
        //Animation in2 = AnimationUtils.makeInAnimation(this , false);
        //in2.setInterpolator(new AccelerateInterpolator());
        Animation bottom = AnimationUtils.loadAnimation(this , R.anim.right_in);
        bottom.setDuration(700);
        learn.setAnimation(in1);
        solve.setAnimation(in1);
        //in2.setDuration(500);
        practice.setAnimation(bottom);
        exit.setAnimation(bottom);

    }

    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.practice:
                i = new Intent(this , Practice.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in_100p , R.anim.left_out);
                break;
            case R.id.solve:
                i = new Intent(this , OperationsList.class);
                startActivity(i);
                overridePendingTransition(R.anim.right_in_100p , R.anim.left_out);
                break;
            case R.id.exit:
                finish();
                break;
            default:
                Intent in = new Intent(this , Topics.class);
                startActivity(in);
                overridePendingTransition(R.anim.right_in_100p , R.anim.left_out);
                break;
        }


    }
}
