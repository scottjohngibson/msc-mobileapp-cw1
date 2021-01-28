package com.example.fibonacciseq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SecondScreen extends AppCompatActivity {

    private int seqNum, id, stepNum = 0;
    private String textViewN;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //int messageN = Integer.parseInt(message);

        seqNum = Integer.parseInt(message);
        Object output = fibonacciSeq(seqNum).get(seqNum);
        textView = findViewById(R.id.test);
        textView.setText("Fn for index " + "'" + message + "'" + " = " + output.toString());

    }

    /** Populates array-list with Fibonacci sequence
     * @param n        Sequence index
     * @return         Array-list of sequence
     */
    public ArrayList fibonacciSeq(int n) {
        ArrayList seqArray = new ArrayList();
        int n1 = 0, n2 = 1, n3, i;

        // Populate array-list with '0' & '1'
        seqArray.add(n1);
        if (n == 0) {
            return seqArray;
        }
        seqArray.add(n2);

        for (i = 0; i < n - 1; i++) {

            n3 = n2 + n1;
            n1 = n2;
            n2 = n3;
            seqArray.add(n3);
        }

        return seqArray;

    }

    /** Return TextView by its name
     * @param n        Integer to suffix the title with
     * @return         TextView object
     */
    public TextView getTextViewN(int n) {
        textViewN = "textView" + (n + 1);
        id = getResources().getIdentifier(textViewN, "id", getPackageName());
        textView = findViewById(id);
        return textView;
    }

    /** Populate TextView with array-list objects at given index
     * @param list  Array-list to index
     * @param n     Index number
     */
    public void populateTextView(ArrayList list, int n) {

            Object textViewInput = list.get(n);
            getTextViewN(n).setText(textViewInput.toString());

    }

    /** Controls TextView inputs/outputs for "Step through sequence" action
     */
    public void stepSequenceButton(View view) {

        int temp = stepNum;
        TextView tv0, tv1, tv2, tv3;


        if(temp < fibonacciSeq(seqNum).size() - 2 && seqNum != 1 && seqNum != 0) {

            // numbers to sum shown in blue
            tv1 = getTextViewN(temp);
            populateTextView(fibonacciSeq(seqNum),temp);
            tv1.setTextColor(Color.parseColor("#3368FF"));

            tv2 = getTextViewN(1 + temp);
            populateTextView(fibonacciSeq(seqNum), temp + 1);
            tv2.setTextColor(Color.parseColor("#3368FF"));

            // sum of numbers shown in green
            tv3 = getTextViewN(2 + temp);
            populateTextView(fibonacciSeq(seqNum), temp + 2);
            tv3.setTextColor(Color.parseColor("#38CD59"));

            // previous index not in sum returned to black
            if (temp > 0) {
                tv0 = getTextViewN(temp - 1);
                tv0.setTextColor(Color.parseColor("#000000"));
            }
        }

        else if(seqNum == 0){getTextViewN(0).setText("0");}
        else if(seqNum == 1){getTextViewN(0).setText("0");
                             getTextViewN(1).setText("1");}

        stepNum++;

    }
}