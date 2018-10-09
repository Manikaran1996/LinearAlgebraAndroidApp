package com.har_asha.linearalgebra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by manikaran on 4/3/17.
 */
public class Question extends AppCompatActivity {
    private int questionNumber , questionSet;
    private final int MAX_QUEST = 5;
    private TextView prev , next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        questionSet=questionNumber=1;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prev = (TextView) findViewById(R.id.prev);
        next = (TextView) findViewById(R.id.next);
        QuestionFragment questionFragment = QuestionFragment.getInstance(questionSet,questionNumber);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.questionFragment , questionFragment);
        fragmentTransaction.commit();
    }

    public void previous(View view) {
        if (questionNumber == 1)
            return;
        questionNumber--;
        QuestionFragment questionFragment = QuestionFragment.getInstance(questionSet,questionNumber);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.questionFragment ,questionFragment);
        fragmentTransaction.commit();
        next.setVisibility(View.VISIBLE);
        if (questionNumber == 1)
            prev.setVisibility(View.GONE);
    }

    public void next(View view) {
        if (questionNumber == MAX_QUEST)
            return;
        questionNumber++;
        QuestionFragment questionFragment = QuestionFragment.getInstance(questionSet,questionNumber);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.questionFragment ,questionFragment);
        fragmentTransaction.commit();
        prev.setVisibility(View.VISIBLE);
        if (questionNumber == MAX_QUEST)
            next.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
