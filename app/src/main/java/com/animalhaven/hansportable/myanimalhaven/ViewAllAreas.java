package com.animalhaven.hansportable.myanimalhaven;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.animalhaven.hansportable.myanimalhaven.Config.App;
import com.animalhaven.hansportable.myanimalhaven.Domain.LivingArea;
import com.animalhaven.hansportable.myanimalhaven.Services.Implementations.LivingAreaServiceImpl;

import java.util.ArrayList;

public class ViewAllAreas extends AppCompatActivity {

    private LivingAreaServiceImpl service;
    private boolean isBound;
    private LivingArea selectedItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_areas);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Intent intent = new Intent(this, LivingAreaServiceImpl.class);
        App.context = this;
        service = LivingAreaServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

        try{
            ArrayList<LivingArea> myList = service.getLivingAreas();

            final ArrayAdapter<LivingArea> arrayAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);

            ListView listView = (ListView)findViewById(R.id.myLivingAreaList);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedItem = arrayAdapter.getItem(position);
                }
            });
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
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

    public void backToHome(View view)
    {
        Intent intent = new Intent (this, EnterLivingAreaDetails.class);
        startActivity(intent);
    }

    public void startUpdateProcess(View view)
    {
        try {
            LivingArea currentArea = selectedItem;

            Intent areaActivity = new Intent(this, UpdateLivingAreaDetails.class);
            areaActivity.putExtra("id", currentArea.getLivingAreaId());
            areaActivity.putExtra("code", currentArea.getCode());
            areaActivity.putExtra("name", currentArea.getName());
            areaActivity.putExtra("space", currentArea.getSpaceAvailable());
            areaActivity.putExtra("active", currentArea.isActive());

            startActivity(areaActivity);

        }catch(Exception x){

        }
    }

    public void beginDeleteProcess(View view)
    {
        try{
            LivingArea currentArea = selectedItem;

            Intent areaActivity = new Intent(this, ConfirmDeleteActivity.class);
            areaActivity.putExtra("id", currentArea.getLivingAreaId());
            //areaActivity.putExtra("code", currentArea.getCode());
            //areaActivity.putExtra("name", currentArea.getName());
            //areaActivity.putExtra("space", currentArea.getSpaceAvailable());
            //areaActivity.putExtra("active", currentArea.isActive());

            startActivity(areaActivity);

        }catch(Exception x){

        }
    }

}
