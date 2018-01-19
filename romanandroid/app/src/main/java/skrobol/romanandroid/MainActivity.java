package skrobol.romanandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import skrobol.romanandroid.model.*;
import static android.view.KeyEvent.KEYCODE_ENTER;

/**
 * Application activity.
 *
 * @author Skrobol Bartłomiej
 * @version 1.0
 */
public class MainActivity extends Activity {

    /**For converting roman number*/
    private RomanNumberConverter converter;

    /**Output for converted number*/
    private TextView resultView ;

    /**Input for roman number */
    private EditText inputRomanNumber ;

    /**Button for start convertion*/
    private Button convertButton ;

    /**Call when activity is initialize
     * @param savedInstanceState Pass data between activities */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        converter = new RomanNumberConverter();
        resultView = findViewById(R.id.textView);
        inputRomanNumber = findViewById(R.id.editText);
        convertButton =  findViewById(R.id.button);

        convertButton.setOnClickListener((View view)-> convertClick(view));

        inputRomanNumber.setOnKeyListener((View view,int key, KeyEvent keyEvent ) -> {
                if(keyEvent.getKeyCode() == KEYCODE_ENTER) {
                    convertClick(view);
                    return true;
                }

                return false;
            }
        );

    }

    /**
     * Start converting and clear view
     *
     * @param view The view where button or enter was clicked.
     */
    public void convertClick(View view){

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        resultView.clearComposingText();

        String romanNumber = inputRomanNumber.getText().toString();
        convert(romanNumber);
    }

    /**
     * Convert roman number and set result output
     * if input is invalid show Toast with error  @param romanNumber the roman number
     */
    public void convert(String romanNumber){

        try{
            final int result = converter.convert(romanNumber);
            resultView.setText(Integer.toString(result));
        } catch (RomanNumberFormatException e) {
            resultView.setText(null);
            Toast.makeText(this , e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
