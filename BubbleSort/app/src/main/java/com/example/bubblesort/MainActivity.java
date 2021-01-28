package com.example.bubblesort;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int i, j, n = 0; int temp = 0;
    private Random r = new Random();
    private int[] array = new int[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArray();

        TextView tv = findViewById(R.id.textView2);
        tv.setText(Arrays.toString(array));
    }

    /** Populates an array with random numbers between 1 & 100
     */
    public void populateArray(){
        for(i = 0; i<6; i++){
            array[i] = r.nextInt((100 - 1) + 1) + 1;
            String viewName = "bar" + (i + 1);

            getView(viewName).requestLayout();
            getView(viewName).getLayoutParams().height = array[i] * 10;

        }
    }

    /** Finds and returns a View object by its name
     * @param viewName      The name of the view object
     * @return              View object
     */
    public View getView(String viewName){
        int id = getResources().getIdentifier(viewName, "id", getPackageName());
        View view = findViewById(id);
        return view;
    }

    /** Animates array-graph when "Press to sort" button is clicked
     */
    public void animateOnClick(View view) {

        bubbleSort();

        for(i = 0; i<6; i++){

            String viewName = "bar" + (i + 1);

            getView(viewName).requestLayout();
            getView(viewName).getLayoutParams().height = array[i] * 10;
        }

        TextView tv = findViewById(R.id.textView);

        if(temp > 0 && temp < 5){tv.setText("The two bars in blue are compared. If the leftmost bar is " +
                "greater than its neighbour it will be moved further along the array - otherwise the algorithm moves on to the next element.");}
        else if(temp == 5){tv.setText("This continues until the largest element has been moved to the end of the array and into its sorted position. Continue clicking to see the final array.");}
        else if(temp > 5){tv.setText("");}
    }

    /** Specifies the colour of the array-graph bars
     * @param bar1      The first element being compared
     * @param bar2      The second element being compared
     */
    public void changeBarColour(int bar1, int bar2)
    {
        String viewName0 = "bar" + bar1, viewName1 = "bar" + (1 + bar1), viewName2 = "bar" + (1 + bar2);

    if(temp < 5) {
        if (bar1 > 0) {
            getView(viewName0).setBackgroundResource(R.drawable.border);
        }

        if (bar2 < 5) {
            getView(viewName1).setBackgroundResource(R.drawable.border_blue);
            getView(viewName2).setBackgroundResource(R.drawable.border_blue);
        } else {
            getView(viewName2).setBackgroundResource(R.drawable.border_green);

            if (array[bar1] == array[bar2]) {
                getView(viewName1).setBackgroundResource(R.drawable.border_green);
            }
        }
    }

    else {getView("bar5").setBackgroundResource(R.drawable.border);
          getView("bar6").setBackgroundResource(R.drawable.border);}

        temp++;
    }

    /** Bubble-sort algorithm
     */
    public void bubbleSort()
    {
        int k;

        bubbleSort:
        for (i = 0; i < array.length-1; i++) {
            for (j = n; j < array.length - 1 - i; j++) {

                if (array[j] > array[j + 1]) {

                    k = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = k;

                }

                changeBarColour(j, j+1);

                n++;
                break bubbleSort;
            }

            n = 0;
        }

        TextView tv = findViewById(R.id.textView2);
        tv.setText(Arrays.toString(array));

    }
}
