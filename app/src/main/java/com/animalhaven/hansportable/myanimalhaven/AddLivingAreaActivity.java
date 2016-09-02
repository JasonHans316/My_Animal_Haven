package com.animalhaven.hansportable.myanimalhaven;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Config.DBConstants;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.LivingAreaServiceImpl;

public class AddLivingAreaActivity extends AppCompatActivity {

    private LivingAreaServiceImpl service;
    private boolean isBound;
    private LivingArea valueToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_living_area);

        Intent myIntent = getIntent();

        TextView code = (TextView) findViewById(R.id.codeDisplay);
        TextView name = (TextView) findViewById(R.id.nameDisplay);
        TextView space = (TextView) findViewById(R.id.spaceDisplay);
        TextView active = (TextView) findViewById(R.id.activeDisplay);


        LivingArea readyForSave = new LivingArea.Builder()
                .code(myIntent.getStringExtra("code"))
                .name(myIntent.getStringExtra("name"))
                .spaceAvailable(myIntent.getIntExtra("space", 0))
                .active(myIntent.getBooleanExtra("active", false))
                .build();

        valueToSave = new LivingArea.Builder()
                .copy(readyForSave)
                .id(null)
                .build();

        code.setText(readyForSave.getCode());
        name.setText(readyForSave.getName());
        space.setText(readyForSave.getSpaceAvailable() + "");
        if(readyForSave.isActive())
           active.setText("Area is active");
        else
           active.setText("Area is not active");
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder localService) {
            LivingAreaServiceImpl.ActivateServiceLocalBinder binder
                    = (LivingAreaServiceImpl.ActivateServiceLocalBinder) localService;
            service = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onStart()
    {
        super.onStart();
        Intent intent = new Intent(this, LivingAreaServiceImpl.class);


        App.context = this;
        service = LivingAreaServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public void saveDetails(View view)
    {
        valueToSave = service.createLivingArea(valueToSave);

        if(valueToSave.getLivingAreaId() != null) {
            Toast.makeText(AddLivingAreaActivity.this, "SUCCESSFULLY ADDED LIVING AREA", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ViewAllAreas.class);
            startActivity(intent);
        }
        else
            Toast.makeText(AddLivingAreaActivity.this,"COULD NOT ADD RECORD",Toast.LENGTH_LONG).show();
    }

}
