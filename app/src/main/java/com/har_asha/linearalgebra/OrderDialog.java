package com.har_asha.linearalgebra;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;


/**
 * Created by manikaran on 17/2/17.
 */

public class OrderDialog extends DialogFragment implements View.OnClickListener {
    private Button next , cancel;
    private Dialog dialog;
    EditText rows , cols;
    private NumberPicker rowPicker , rowPicker2;
    private NumberPicker colPicker , colPicker2;
    private int operation;

    public static OrderDialog getInstance(int pos) {
        OrderDialog dialog = new OrderDialog();
        Bundle args = new Bundle();
        args.putInt("operation" , pos);
        dialog.setArguments(args);
        return dialog;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = null;
        rowPicker2 = colPicker2 = null;
        Bundle args = getArguments();
        operation = args.getInt("operation");
        if(operation == 1) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.order2, null);
            rowPicker2 = (NumberPicker) view.findViewById(R.id.picker_rows2);
            colPicker2 = (NumberPicker) view.findViewById(R.id.picker_cols2);
            rowPicker2.setMinValue(1);
            rowPicker2.setMaxValue(5);
            colPicker2.setMaxValue(5);
            colPicker2.setMinValue(1);
        }
        else
            view = LayoutInflater.from(getActivity()).inflate(R.layout.order_dialog , null);
        next = (Button) view.findViewById(R.id.next);
        cancel = (Button) view.findViewById(R.id.cancel);
        //rows = (EditText) view.findViewById(R.id.rows);
        //cols = (EditText) view.findViewById(R.id.cols);
        rowPicker = (NumberPicker) view.findViewById(R.id.picker_rows);
        colPicker = (NumberPicker) view.findViewById(R.id.picker_cols);
        rowPicker.setMinValue(1);
        rowPicker.setMaxValue(5);
        colPicker.setMaxValue(5);
        colPicker.setMinValue(1);
        final int count = colPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = colPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = colPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(colPicker)).setColor(Color.WHITE);
                    ((EditText)child).setTextColor(Color.WHITE);
                    colPicker.invalidate();
                    break;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextCol", e);
                }
            }
        }
        for(int i = 0; i < count; i++){
            View child = rowPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = rowPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(rowPicker)).setColor(Color.WHITE);
                    ((EditText)child).setTextColor(Color.WHITE);
                    rowPicker.invalidate();
                    break;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextCol", e);
                }
            }
        }
        for(int i = 0; i < count; i++){
            View child = rowPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field dividerPaintField = rowPicker.getClass()
                            .getDeclaredField("mSelectionDivider");
                    dividerPaintField.setAccessible(true);
                    dividerPaintField.set(rowPicker,new ColorDrawable(0xffefefef));
                    rowPicker.invalidate();
                    break;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextCol", e);
                }
            }
        }
        for(int i = 0; i < count; i++){
            View child = colPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectionDividerPaintField = colPicker.getClass()
                            .getDeclaredField("mSelectionDivider");
                    selectionDividerPaintField.setAccessible(true);
                    selectionDividerPaintField.set(colPicker,new ColorDrawable(0xffefefef));
                    colPicker.invalidate();
                    break;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextCol", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextCol", e);
                }
            }
        }
        if(operation == 1) {
            colPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    rowPicker2.setValue(newVal);
                }
            });

            rowPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    colPicker.setValue(newVal);
                }
            });
            for(int i = 0; i < count; i++){
                View child = colPicker2.getChildAt(i);
                if(child instanceof EditText){
                    try{
                        Field selectorWheelPaintField = colPicker2.getClass()
                                .getDeclaredField("mSelectorWheelPaint");
                        selectorWheelPaintField.setAccessible(true);
                        ((Paint)selectorWheelPaintField.get(colPicker2)).setColor(Color.WHITE);
                        ((EditText)child).setTextColor(Color.WHITE);
                        colPicker2.invalidate();
                        break;
                    }
                    catch(NoSuchFieldException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalAccessException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalArgumentException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                }
            }
            for(int i = 0; i < count; i++){
                View child = rowPicker2.getChildAt(i);
                if(child instanceof EditText){
                    try{
                        Field selectorWheelPaintField = rowPicker2.getClass()
                                .getDeclaredField("mSelectorWheelPaint");
                        selectorWheelPaintField.setAccessible(true);
                        ((Paint)selectorWheelPaintField.get(rowPicker2)).setColor(Color.WHITE);
                        ((EditText)child).setTextColor(Color.WHITE);
                        rowPicker2.invalidate();
                        break;
                    }
                    catch(NoSuchFieldException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalAccessException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalArgumentException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                }
            }
            for(int i = 0; i < count; i++){
                View child = rowPicker2.getChildAt(i);
                if(child instanceof EditText){
                    try{
                        Field dividerPaintField = rowPicker2.getClass()
                                .getDeclaredField("mSelectionDivider");
                        dividerPaintField.setAccessible(true);
                        dividerPaintField.set(rowPicker2,new ColorDrawable(0xffefefef));
                        rowPicker2.invalidate();
                        break;
                    }
                    catch(NoSuchFieldException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalAccessException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalArgumentException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                }
            }
            for(int i = 0; i < count; i++){
                View child = colPicker2.getChildAt(i);
                if(child instanceof EditText){
                    try{
                        Field selectionDividerPaintField = colPicker2.getClass()
                                .getDeclaredField("mSelectionDivider");
                        selectionDividerPaintField.setAccessible(true);
                        selectionDividerPaintField.set(colPicker2,new ColorDrawable(0xffefefef));
                        colPicker2.invalidate();
                        break;
                    }
                    catch(NoSuchFieldException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalAccessException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                    catch(IllegalArgumentException e){
                        Log.w("setNumberPickerTextCol", e);
                    }
                }
            }
        }
        next.setOnClickListener(this);
        cancel.setOnClickListener(this);
        builder.setView(view);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.next:
                int row = rowPicker.getValue();
                int col = colPicker.getValue();
                Intent i = new Intent(getActivity() , MatrixInput.class );
                i.putExtra("Rows" , row);
                i.putExtra("Cols" , col);
                if(operation == 1) {
                    i.putExtra("Rows2" , rowPicker2.getValue());
                    i.putExtra("Cols2" , colPicker2.getValue());
                }
                i.putExtra("operation" , operation);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity() , null);
                startActivity(i , options.toBundle());
                dialog.dismiss();
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;
        }
    }
}
