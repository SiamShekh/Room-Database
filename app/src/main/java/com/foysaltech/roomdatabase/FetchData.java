package com.foysaltech.roomdatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class FetchData extends AppCompatActivity {
    RecyclerView recview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);

        getroomdata();
    }

    public void getroomdata() {
        RoomDatabases db = Room.databaseBuilder(getApplicationContext(),
                RoomDatabases.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        List<User> users = userDao.getallusers();

        CustomAdapter adapter = new CustomAdapter(users);
        recview.setAdapter(adapter);
    }
}