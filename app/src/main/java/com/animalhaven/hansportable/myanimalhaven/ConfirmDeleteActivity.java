package com.animalhaven.hansportable.myanimalhaven;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.LivingAreaServiceImpl;

public class ConfirmDeleteActivity extends AppCompatActivity {

    private LivingAreaServiceImpl service;
    private boolean isBound;
    private LivingArea valueToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete);

        Intent myIntent = getIntent();
        long livingAreaId = myIntent.getLongExtra("id", 0);
        valueToDelete = new LivingArea.Builder().id(livingAreaId).build();
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

    public void executeDelete(View view)
    {
        valueToDelete = service.getLivingArea(valueToDelete.getLivingAreaId());
        service.deleteLivingArea(valueToDelete);
        Intent intent = new Intent (this, ViewAllAreas.class);
        startActivity(intent);
    }

    public void executeCancel(View view)
    {
        Intent intent = new Intent (this, ViewAllAreas.class);
        startActivity(intent);
    }
}
