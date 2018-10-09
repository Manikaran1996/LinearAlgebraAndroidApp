package com.har_asha.linearalgebra;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by manikaran on 16/2/17.
 */
public class MatrixInput extends AppCompatActivity implements View.OnClickListener {

    private GridLayout gridLayout;
    private int cols , rows , operation;
    private GridLayout gridLayout2;
    private CardView input1 , input2;
    private int rows2 , cols2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Fade enterTransition = new Fade(Fade.IN);
            Slide enterTransition = new Slide();
            enterTransition.setDuration(400);
            getWindow().setEnterTransition(enterTransition);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix_input);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView matrix2Header = (TextView) findViewById(R.id.matrix2Header);
        View matrix2Line = findViewById(R.id.matrix2Line);
        gridLayout2 = (GridLayout) findViewById(R.id.container2);

        input1 = (CardView) findViewById(R.id.input1);
        input2 = (CardView) findViewById(R.id.input2);
        input1.setCardElevation(10f);
        input1.setRadius(5f);

        ImageButton imageButton1 = (ImageButton) findViewById(R.id.remove1);
        imageButton1.setOnClickListener(this);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.remove2);
        imageButton2.setOnClickListener(this);

        Button button = (Button) findViewById(R.id.compute);
        Intent intent = getIntent();
        rows = intent.getIntExtra("Rows" , 3);
        cols = intent.getIntExtra("Cols" , 3);
        operation = intent.getIntExtra("operation" , 1);
        rows2=cols2=0;

        if(operation <= 1 ) {
            rows2 = intent.getIntExtra("Rows2" ,  rows);
            cols2 = intent.getIntExtra("Cols2" , cols);
            imageButton2.setVisibility(View.VISIBLE);
            input2.setVisibility(View.VISIBLE);
            input2.setCardElevation(10f);
            input2.setRadius(5f);
            matrix2Header.setVisibility(View.VISIBLE);
            matrix2Line.setVisibility(View.VISIBLE);
            gridLayout2.setVisibility(View.VISIBLE);
            gridLayout2.setColumnCount(cols2);
            for(int i=0;i<rows2;i++) {
                for(int j=0;j<cols2;j++) {
                    View view = LayoutInflater.from(this).inflate(R.layout.elements , null);
                    gridLayout2.addView(view);
                }
            }
        }
        gridLayout = (GridLayout) findViewById(R.id.container1);
        gridLayout.setColumnCount(cols);
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++) {
                View view = LayoutInflater.from(this).inflate(R.layout.elements , null);
                gridLayout.addView(view);
            }
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] opTitle = new String[] {"\tSum " , "\tProduct " , "\tAdjoint " , "\tInverse " , "\tDeterminant " ,
                    "\tTranspose " , "\tTranspose " , "\tTranspose " , "\tTranspose " , "\tTranspose " , "\tTranspose " , "\tTranspose "};
                String matrix = "";
                if(operation <= 1)
                    matrix = compute2();
                else {
                    matrix = compute1();
                }
                if(matrix != null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.result_dialog , null , false);

                    WebView webView = (WebView) view.findViewById(R.id.webView);
                    ProgressBar pb = (ProgressBar) view.findViewById(R.id.wheel);
                    String path="file:///android_asset/";
                    String data = "<html><head> " +
                            "<link rel='stylesheet' href='file:///android_asset/jqmath-0.4.3.css'>" +
                            "<script src=\"" + path + "jquery-1.4.3.min.js\"></script>"
                            + "<script src=\"" + path + "jqmath-etc-0.4.6.min.js\" charset=\"utf-8\"></script>"
                            + "</head><body><p>"+ opTitle[operation] +" of the matrix is <br>$$\\[\\table " + matrix + "\\]$$</p> "
                            + "</body></html>";
                    webView.setWebViewClient(new MyWebViewClient(pb));
                    webView.loadDataWithBaseURL(path , data , "text/html" ,"UTF-8" , null);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MatrixInput.this);
                    builder.setView(view);
                    builder.setTitle("Result");
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.remove1) {
            int row = gridLayout.getRowCount();
            int col = gridLayout.getColumnCount();
            int ind = 0;
            for(int i=0;i<row;i++) {
                for(int j=0;j<col;j++) {
                    View view = gridLayout.getChildAt(ind);
                    ind++;
                    ((EditText)view.findViewById(R.id.cell)).setText("");
                }
            }
        }
        else {
            int row = gridLayout2.getRowCount();
            int col = gridLayout2.getColumnCount();
            int ind = 0;
            for(int i=0;i<row;i++) {
                for(int j=0;j<col;j++) {
                    View view = gridLayout2.getChildAt(ind);
                    ind++;
                    ((EditText)view.findViewById(R.id.cell)).setText("");
                }
            }
        }
    }

    class MyWebViewClient extends WebViewClient {
        ProgressBar pb;
        public MyWebViewClient(ProgressBar progressBar) {
            pb = progressBar;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            pb.setVisibility(View.GONE);
            super.onPageFinished(view, url);
            view.setVisibility(View.VISIBLE);
        }

    }
    String compute2() {
        double[][] matrix1 , matrix2;
        matrix1 = new double[rows][cols];
        matrix2 = new double[rows2][cols2];
        for(int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                View view1 = gridLayout.getChildAt(i*cols + j);
                Log.d("i and j" , i+" " + j);
                EditText cell1 = (EditText) view1.findViewById(R.id.cell);
                try {
                    matrix1[i][j] = Double.parseDouble(cell1.getText().toString().trim());
                }
                catch (NumberFormatException e) {
                    int r = i+1 , c = j+1;
                    Toast.makeText(this,"Enter valid value at ( " + r + "," + c + " ) element of matrix 1 " , Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        for(int i=0;i<rows2;i++) {
            for(int j=0;j<cols2;j++) {
                View view2 = gridLayout2.getChildAt(i*cols2 + j);
                EditText cell2 = (EditText) view2.findViewById(R.id.cell);
                try {
                    matrix2[i][j] = Double.parseDouble(cell2.getText().toString().trim());
                }
                catch (NumberFormatException e) {
                    int r = i+1 , c = j+1;
                    Toast.makeText(this,"Enter valid value at ( " + r + "," + c + " ) element of matrix 2 " , Toast.LENGTH_SHORT).show();
                    return null;
                }

            }
        }
        Matrix Matrix1 = new Matrix(matrix1 , rows , cols);
        Matrix Matrix2 = new Matrix(matrix2 , rows2 , cols2);
        Matrix result = null;
        switch (operation) {
            case 0:
                result = Matrix1.addMatrix(Matrix2);
                break;
            case 1:
                result = Matrix1.multiplication(Matrix2);
            default:
        }
        if(result == null)
            return null;
        return result.toString();
    }

    String compute1 () {
        double[][] matrix = new double[rows][cols];
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                View view1 = gridLayout.getChildAt(i * rows + j);
                EditText cell1 = (EditText) view1.findViewById(R.id.cell);
                try {
                    matrix[i][j] = Double.parseDouble(cell1.getText().toString().trim());
                } catch (NumberFormatException e) {
                    int r = i + 1, c = j + 1;
                    Toast.makeText(this, "Enter valid value at ( " + r + "," + c + " ) element of matrix 1 ", Toast.LENGTH_SHORT).show();
                    return null;
                }
            }
        }
        Matrix matrix1 = new Matrix(matrix , rows ,cols);
        Matrix result = null;
        switch(operation) {
            case 2:
                result = matrix1.getAdjoint();
                return result.toString();
            case 3: //inverse of matrix
                result = matrix1.inverse();
                if(result == null) {
                    Toast.makeText(MatrixInput.this, "Inverse of matrix does not exist.", Toast.LENGTH_SHORT).show();
                    return null;
                }
                return result.toString();
            case 4:
                double det = matrix1.determinant();
                return String.valueOf(det);
            default:
                result = matrix1.transpose();
                return result.toString();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return true;
    }
}
