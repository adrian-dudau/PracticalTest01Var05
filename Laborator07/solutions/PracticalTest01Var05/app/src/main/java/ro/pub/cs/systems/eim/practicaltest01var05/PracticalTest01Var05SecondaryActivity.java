package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private TextView textScore;

    private Button buttonOk;


    private int field1_value = 0;
    private int field2_value = 0;
    private int field3_value = 0;

    private int total_pressed = 0;
    private int score = 0;

    private View.OnClickListener second_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        final Intent receivedIntent = getIntent();

        field1_value = receivedIntent.getIntExtra(Constants.INTENT_KEY_1, 1);
        field2_value = receivedIntent.getIntExtra(Constants.INTENT_KEY_2, 1);
        field3_value = receivedIntent.getIntExtra(Constants.INTENT_KEY_3, 1);
        total_pressed = receivedIntent.getIntExtra(Constants.INTENT_KEY_4, 1);

        Log.d(Constants.LOG_TAG, "------------>>1SecondActivity onCreate method was invoked" + Integer.toString(total_pressed));

        textScore = (TextView)findViewById(R.id.textViewScore);
        buttonOk = (Button) findViewById(R.id.buttonOK);

        if (field1_value == field2_value && field2_value == field3_value) {

            if (total_pressed == 0) {
                textScore.setText("Gained 100");
                score = 100;
            } else if (total_pressed == 1) {
                textScore.setText("Gained 50");
                score = 50;
            } else {
                textScore.setText("Gained 10");
                score = 10;
            }
        } else {
            textScore.setText("No Gain");
        }

        second_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent;
                switch (v.getId()) {
                    case R.id.buttonOK:
                        returnIntent = new Intent();
                        returnIntent.putExtra(Constants.INTENT_KEY_1, score);
                        setResult(Activity.RESULT_OK, returnIntent);
//                        setResult(RESULT_OK);
                        finish();
                        break;
                }
            }
        };
        Log.d(Constants.LOG_TAG, "------------>>2SecondActivity onCreate method was invoked" + Integer.toString(total_pressed));

        buttonOk.setOnClickListener(second_listener);

    }
}
