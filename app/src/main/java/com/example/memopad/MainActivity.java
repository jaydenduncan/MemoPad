package com.example.memopad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memopad.databinding.ActivityMainBinding;

import java.beans.PropertyChangeEvent;

public class MainActivity extends AppCompatActivity implements AbstractView {

    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";
    private MemoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /* Create Controller and Model */

        controller = new MemoController();
        MemoModel model = new MemoModel();

        /* Register Activity View and Model with Controller */

        controller.addView(this);
        controller.addModel(model);

        /* Initialize Model to Default Values */

        model.init(this);

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memoText = binding.editTextMemoToAdd.getText().toString();
                binding.editTextMemoToAdd.setText("");
                addMemo(memoText);

            }
        });

        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String memoId = binding.editTextMemoToDelete.getText().toString();
                binding.editTextMemoToDelete.setText("");
                deleteMemo(memoId);

            }
        });
    }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        /*
         * This method is called by the "propertyChange()" method of AbstractController
         * when a change is made to an element of a Model.  It identifies the element that
         * was changed and updates the View accordingly.
         */

        String propertyName = evt.getPropertyName();
        String propertyValue = evt.getNewValue().toString();

        Log.i(TAG, "New " + propertyName + " Value from Model: " + propertyValue);

        if ( propertyName.equals(MemoController.MEMOLIST_PROPERTY) ) {

            String oldPropertyValue = binding.memoDisplay.getText().toString();

            if ( !oldPropertyValue.equals(propertyValue) ) {
                binding.memoDisplay.setText(propertyValue);
            }

        }

    }

    public void addMemo(String memoText){

        try{
            if(memoText.equals("")){
                throw new IllegalArgumentException();
            }

            controller.changeMemoToAdd(memoText);
        }
        catch(Exception e){
            Toast toast = Toast.makeText(this, "Enter a valid memo", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void deleteMemo(String memoId){

        try{
            if(memoId.equals("")){
                throw new IllegalArgumentException();
            }

            controller.changeMemoToDelete(memoId);
        }
        catch(Exception e){
            Toast toast = Toast.makeText(this, "Enter a valid memo ID", Toast.LENGTH_LONG);
            toast.show();
        }

    }


}