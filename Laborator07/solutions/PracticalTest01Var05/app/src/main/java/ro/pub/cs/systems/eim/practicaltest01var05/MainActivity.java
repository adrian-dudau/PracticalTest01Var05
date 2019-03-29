package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private int field1_value = 0;
    private int field2_value = 0;
    private int field3_value = 0;

    private TextView text1;
    private TextView text2;
    private TextView text3;

    private Button play_button;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;

    Random r = new Random();
    int my_number = 0;

    int number_of_checks = 0;
    private int num1 = 0;
    private int score = 0;

    private View.OnClickListener my_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(Constants.LOG_TAG, "------------>> onCreate method was invoked");

        text1 = (TextView)findViewById(R.id.textView1);
        text2 = (TextView)findViewById(R.id.textView2);
        text3 = (TextView)findViewById(R.id.textView3);

        play_button = (Button)findViewById(R.id.playButton);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);


        field1_value = r.nextInt(5);
        text1.setText(Integer.toString(field1_value));
        field2_value = r.nextInt(5);
        text2.setText(Integer.toString(field2_value));
        field3_value = r.nextInt(5);
        text3.setText(Integer.toString(field3_value));

        my_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.playButton:
                        if(!checkBox1.isChecked()) {
                            field1_value = r.nextInt(5);
                            text1.setText(Integer.toString(field1_value));
                        }
                        if(!checkBox2.isChecked()) {
                            field2_value = r.nextInt(5);
                            text2.setText(Integer.toString(field2_value));
                        }
                        if(!checkBox3.isChecked()) {
                            field3_value = r.nextInt(5);
                            text3.setText(Integer.toString(field3_value));
                        }

                        Intent my_intent = new Intent(MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
                        my_intent.putExtra(Constants.INTENT_KEY_1, field1_value);
                        my_intent.putExtra(Constants.INTENT_KEY_2, field2_value);
                        my_intent.putExtra(Constants.INTENT_KEY_3, field3_value);
                        my_intent.putExtra(Constants.INTENT_KEY_4, number_of_checks);
                        startActivityForResult(my_intent, Constants.SECOND_ACTIVITY_REQUESR_CODE);
                        break;

                    case R.id.checkBox1:
                        if(checkBox1.isChecked()) {
                            number_of_checks++;
//                            Toast.makeText(getApplicationContext(),"User checked 1", Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(getApplicationContext(),"User unchecked 1", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.checkBox2:
                        if(checkBox3.isChecked()) {
                            number_of_checks++;
//                            Toast.makeText(getApplicationContext(),"User checked 2", Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(getApplicationContext(),"User unchecked 2", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.checkBox3:
                        if(checkBox2.isChecked()) {
                            number_of_checks++;
//                            Toast.makeText(getApplicationContext(),"User checked 3", Toast.LENGTH_LONG).show();
                        } else {
//                            Toast.makeText(getApplicationContext(),"User unchecked 3", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };

        play_button.setOnClickListener(my_listener);
        checkBox1.setOnClickListener(my_listener);
        checkBox2.setOnClickListener(my_listener);
        checkBox3.setOnClickListener(my_listener);

        if(checkBox2.isChecked()) {
            Toast.makeText(getApplicationContext(),"User checked 2", Toast.LENGTH_LONG).show();
        }

        if(checkBox3.isChecked()) {
            Toast.makeText(getApplicationContext(),"User checked 3", Toast.LENGTH_LONG).show();
        }
    }

    /* Aici revin in activitatea principala cu rezultatele rularii*/
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch(requestCode) {
            case Constants.SECOND_ACTIVITY_REQUESR_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle data = intent.getExtras();
//                    Log.d(Constants.LOG_TAG, data.toString());
                    num1 = data.getInt(Constants.INTENT_KEY_1);
                    score += num1;
                    Toast.makeText(getApplicationContext(),"User got " + Integer.toString(score), Toast.LENGTH_LONG).show();
//                    but1_value = but2_value = 0;
//                    text1.setText("0");
//                    text2.setText("0");
                } else if (resultCode == Activity.RESULT_CANCELED) {
//                    Toast.makeText(getApplicationContext(),"User preased cancel", Toast.LENGTH_LONG).show();
                    Bundle data = intent.getExtras();
//                    Log.d(Constants.LOG_TAG, data.toString());
                }
                break;
        }
    }

    /* Astea sunt pentru a salava starea daca android elibereaza memoria aplicatiei */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.d(Constants.LOG_TAG, "------------>> onSaveInstanceState method was invoked");

        savedInstanceState.putInt(Constants.VALUE_1, score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(Constants.LOG_TAG, "------------>> onRestoreInstanceState method was invoked");

        score = savedInstanceState.getInt(Constants.VALUE_1);
        Log.d(Constants.LOG_TAG, "------------>> SAVED " + Integer.toString(score));
    }
}
