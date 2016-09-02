package com.animalhaven.hansportable.myanimalhaven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;

public class EnterLivingAreaDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_living_area_details);
    }

    public void openSave(View view) {

        EditText codeInput = (EditText) findViewById(R.id.codeText),
                nameInput = (EditText) findViewById(R.id.nameText),
                spaceInput = (EditText) findViewById(R.id.spaceText);

        String spaceString = spaceInput.getText().toString(),
                code = codeInput.getText().toString(),
                name = nameInput.getText().toString();

        int space = 0,
                codeLength = code.length(),
                nameLength = name.length();

        String errorMessage = "";

       if(codeLength == 0)
            errorMessage = "The code is required.";

        errorMessage = codeLength == 0 ? errorMessage + "\n" : errorMessage;
        if (nameLength == 0)
            errorMessage += "The name is required.";

        errorMessage = nameLength == 0 || codeLength == 0 ? errorMessage + "\n" : errorMessage;
        if (spaceString.length() == 0)
            errorMessage += "The space is required as an integer.";

        if (errorMessage.length() > 0)
            Toast.makeText(EnterLivingAreaDetails.this, errorMessage, Toast.LENGTH_LONG).show();

        try {
            space = Integer.parseInt(spaceString);
        } catch (Exception x) {
            errorMessage = "The Space Available Must Be An Integer.";
            if (spaceString.length() > 0)
                Toast.makeText(EnterLivingAreaDetails.this, errorMessage, Toast.LENGTH_LONG).show();
        }

        if(errorMessage.length() == 0){
            LivingArea newArea = new LivingArea.Builder()
                    .code(code)
                    .name(name)
                    .spaceAvailable(space)
                    .active(true)
                    .build();

            Intent areaActivity = new Intent(this, AddLivingAreaActivity.class);
            areaActivity.putExtra("code", newArea.getCode());
            areaActivity.putExtra("name", newArea.getName());
            areaActivity.putExtra("space", newArea.getSpaceAvailable());
            areaActivity.putExtra("active", newArea.isActive());

            startActivity(areaActivity);
        }
    }



}
