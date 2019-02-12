package mypc.mad.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText inputTxt;
    private TextView outputTxt;
    private TextView view1;
    private TextView view2;
    private TextView history;
    private RadioButton farRadio;
    private RadioButton celRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputTxt = findViewById(R.id.inputText);
        outputTxt = findViewById(R.id.outputText);
        farRadio = findViewById(R.id.fahreneitRadio);
        celRadio=findViewById(R.id.celsiusRadio);
        history=findViewById(R.id.historyText);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);

        //Setting historyText's scrollbar to Vertical
        history.setMovementMethod(new ScrollingMovementMethod());

        // Checking if Radio button is Selected and Set the Labels for Fahrenheit and Celsius Degrees
        if (farRadio.isChecked()) {
            view1.setText("Fahrenheit Degrees:");
            view2.setText("Celsius Degrees:");
        }
        else if (celRadio.isChecked()) {
            view1.setText("Celsius Degrees:");
            view2.setText("Fahrenheit Degrees:");
        }
    }

    public void convertBtnPressed(View view) {
        //Getting Input Text value
        String farOrCelValue = inputTxt.getText().toString().trim();
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton rb_fh = (RadioButton) findViewById(R.id.fahreneitRadio);
        RadioButton rb_cel = (RadioButton) findViewById(R.id.celsiusRadio);
        double scale = Math.pow(10, 1);
        String historyText= history.getText().toString();
        //Checking if input string is Empty
        if (farOrCelValue.isEmpty())
        {
            Toast.makeText(this, "Input Text is Empty" , Toast.LENGTH_SHORT).show();
        }
        else {
            if (rg.getCheckedRadioButtonId() != -1) {
                if (rb_fh.isChecked()) {
                    // F to C
                    double celsius = Math.round((Double) ((Double.parseDouble(farOrCelValue) - 32.0) / 1.8) * scale) / scale;
                    String outTextCelsius = Double.toString(celsius);
                    outputTxt.setText("" + outTextCelsius);
                    history.setText(farOrCelValue + " F ==> " + celsius + " C" + "\n" + historyText);
                }
                if (rb_cel.isChecked()) {
                    //C to F
                    double fahrenheit = Math.round((Double) ((Double.parseDouble(farOrCelValue) * 1.8) + 32.0) * scale) / scale;
                    String outTextFah = Double.toString(fahrenheit);
                    outputTxt.setText("" + outTextFah);
                    history.setText(farOrCelValue + " C ==> " + fahrenheit + " F" + "\n" + historyText);
                }
            }
        }
    }

    public void clearBtnClicked(View v)
    {
        history.setText("");
    }
    public void onRadioButtonClicked(View v) {
                if (farRadio.isChecked()) {
                    view1.setText("Fahrenheit Degrees:");
                    view2.setText("Celsius Degrees:");
                }
                else if (celRadio.isChecked()) {
                    view1.setText("Celsius Degrees:");
                    view2.setText("Fahrenheit Degrees:");
                }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("VIEW1",view1.getText().toString());
        outState.putString("VIEW2",view2.getText().toString());
        outState.putString("OUTPUTTEXT",outputTxt.getText().toString());
        outState.putString("HISTORY",history.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        view1.setText(savedInstanceState.getString("VIEW1"));
        view2.setText(savedInstanceState.getString("VIEW2"));
        outputTxt.setText(savedInstanceState.getString("OUTPUTTEXT"));
        history.setText(savedInstanceState.getString("HISTORY"));

    }


}
