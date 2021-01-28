package com.example.stackarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private Stack stack = new Stack();
    private String textViewN;
    private int id;
    private TextView textView, outputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Returns TextView object for output message view
     * @return      Output message TextView object*/
    public TextView outputMessage() {
        outputMessage = findViewById(R.id.outputMessage);
        return outputMessage;
    }

    /** Return textView object for given suffix number
     * @param n     Integer to suffix to 'textView' String
     * @return      TextView object for given String
     */
    public TextView getTextViewN(int n)
    {
        textViewN = "textView" + (stack.size() - n);
        id = getResources().getIdentifier(textViewN, "id", getPackageName());
        textView = findViewById(id);
        return textView;
    }


    /** Push input value to stack-array */
    public void pushToStack(View view) {

        if (stack.size() < 5) {
            EditText editText = findViewById(R.id.editText);
            String message = editText.getText().toString();

            if(!message.isEmpty()){

                stack.push(message);
                getTextViewN(0).setText(message);
                getTextViewN(0).setTextColor(Color.parseColor("#AEBF2121"));

                if(stack.size() > 1){
                    getTextViewN(1).setTextColor(Color.parseColor("#FF323232"));
                }

                outputMessage().setText("Input value " + "'" + message + "'" + " is pushed to top of stack (shown in red).");
            }
        }
    }

    /** Pop topmost element from stack-array */
    public void popStack(View view) {

        textView = getTextViewN(0);

        if(stack.size() > 0){

            Object temp = stack.peek();
            stack.pop();
            textView.setText(null);
            if(stack.size() > 0){
                getTextViewN(0).setTextColor(Color.parseColor("#AEBF2121"));
            }
            textViewN = "textView" + (stack.size() - 1);
            outputMessage().setText("Topmost element " + "'" + temp + "'" + " is removed from stack.");
        }
    }
}
