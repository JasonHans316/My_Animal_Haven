package com.animalhaven.hansportable.myanimalhaven;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void openLivingAreas(View view)
    {
        TextView userView = (TextView) findViewById(R.id.usernameTxt);
        String user = userView.getText().toString();
        TextView passwordView = (TextView) findViewById(R.id.passwordTxt);
        String password = passwordView.getText().toString();
        if(user.equalsIgnoreCase("cput") && password .equals("success")) {
            Intent intent = new Intent(this, ViewAllAreas.class);
            startActivity(intent);
        }
        else
            Toast.makeText(LoginActivity.this, "Wrong Username AND/OR Password", Toast.LENGTH_LONG).show();
    }
}
