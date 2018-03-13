package com.kokutha.kcube.copydatabase;

import android.content.ContextWrapper;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UserListAdapter userListAdapter;
    ArrayList<UserData> userDataArrayList;
    RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper;
    private static final String DB_NAME = "users.sqlite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = DataBaseHelper.getInstance(this, DB_NAME);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        userDataArrayList = new ArrayList<>();
        userDataArrayList = dataBaseHelper.getDataList();
        userListAdapter = new UserListAdapter(getApplicationContext(), userDataArrayList);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
