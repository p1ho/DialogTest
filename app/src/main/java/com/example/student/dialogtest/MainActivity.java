package com.example.student.dialogtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    TextView tv, tv2, tv3;
    int sel = -1;
    boolean chk[] = new boolean[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText("Your name goes here");
        tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText("Your fruit goes here");
    }
    public void click1 (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Dialog Title");
        builder.setMessage("Dialog Message");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv.setText("You Clicked OK");
                    }
                }
        );
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("You Clicked Cancel");
            }
        }
        );
        builder.setNeutralButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv.setText("You Fucking Skipped");
            }
        }
        );
        builder.show();
    }
    public void click2 (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please enter your name:");
        final EditText editText = new EditText(MainActivity.this);
        //InputFilter[] filterArray = new InputFilter[1];
        //filterArray[0] = new InputFilter.LengthFilter(8);
        //editText.setFilters(filterArray);

        builder.setView(editText);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv2.setText(editText.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void click3 (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please Choose a Fruit");
        final String fruits[] = {"Apple", "Banana", "Papaya", "Orange"};
        final int temp_sel = sel;
        builder.setSingleChoiceItems(fruits, sel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sel = which;
                Toast.makeText(MainActivity.this, fruits[sel], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv3.setText(fruits[sel]);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                sel = temp_sel;
            }
        });
        builder.show();
    }
    public void click4 (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Please Choose a Fruit");
        final String fruits[] = {"Apple", "Banana", "Papaya", "Orange"};
        builder.setItems(fruits, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, fruits[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void click5(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("這是對話框");

        final String drinks[] = {"汽水", "可樂", "果汁", "紅茶"};

        builder.setMultiChoiceItems(drinks, chk, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = "";
                for (int i=0;i<=3;i++)
                {
                    if (chk[i] == true)
                    {
                        s = s + drinks[i];
                    }
                }
                tv.setText(s);
            }
        });

        builder.show();
    }
    public void click6 (View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("這是自訂對話框");
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialog_view = layoutInflater.inflate(R.layout.dialog_view,null);
        builder.setView(dialog_view);
        final TextView textView = (TextView) dialog_view.findViewById(R.id.textView4);
        Button button = (Button) dialog_view.findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("what's up");
            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
    public void click7 (View v){
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("Loading...");
        pd.setMessage("Please be patient...");
        pd.setCancelable(false);
        pd.show();

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                    }
                });

            }
        }.start();
    }
}
