package com.rueda.roque.convertnumberstowords;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rueda.roque.convertnumberstowords.com.roque.rueda.convernumberstowords.service.NumberToWords;

/**
 * Main activity of the application that manages the user input.
 *
 * @author roquerueda
 * @version 1.0
 * @since 17/10/15
 */
public class ConvertActivity extends ActionBarActivity {

    // Widgets that we need from the User interface.
    private Button mEnglishButton;
    private Button mSpanishButton;
    private EditText mUserInput;
    private TextView mResultText;

    // Service Layer will handle all the business logic.
    private NumberToWords numberToWords;


    /**
     * Called to create the activity.
     * @param savedInstanceState Saved stated of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        // Create the object that handle the logic of the application.
        numberToWords = new NumberToWords();

        // First we get all the widgets from the UI.
        getWidgets();
        // Set actions to buttons.
        setListeners();

    }

    /**
     * Gets the widgets from the ui.
     */
    private void getWidgets() {

        mEnglishButton = (Button) findViewById(R.id.btn_english);
        mSpanishButton = (Button) findViewById(R.id.btn_spanish);
        mUserInput = (EditText) findViewById(R.id.edt_number);
        mResultText = (TextView) findViewById(R.id.txv_result);

    }

    /**
     * Set listener to the buttons of the application.
     */
    private void setListeners() {

        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mUserInput.setError(null);
                    int number = Integer.valueOf(mUserInput.getText().toString());
                    mResultText.setText(numberToWords.transformToEnglish(number));
                    mResultText.setVisibility(View.VISIBLE);
                } catch (NumberFormatException nfe) {
                    mUserInput.setError(getString(R.string.user_input_error));
                } catch (Exception ex) {
                    Toast.makeText(ConvertActivity.this, ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        mSpanishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Display a toast to indicate that this action is not supported.
                Toast.makeText(ConvertActivity.this, "This action is not supported yet.",
                        Toast.LENGTH_LONG).show();
            }
        });



    }
}
