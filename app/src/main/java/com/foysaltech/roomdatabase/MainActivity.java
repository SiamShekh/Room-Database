package com.foysaltech.roomdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    EditText edittext, editText2, editText3;
    TextView lbl;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edittext = findViewById(R.id.t1);
        editText2 = findViewById(R.id.t2);
        editText3 = findViewById(R.id.t3);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        lbl = findViewById(R.id.lbl);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RoomDatabases db = Room.databaseBuilder(getApplicationContext(),
                        RoomDatabases.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                Boolean check = userDao.is_exist(Integer.parseInt(edittext.getText().toString()));
                if (check == false) {
                    userDao.insertrecord(new User(Integer.parseInt(edittext.getText().toString()), editText2.getText().toString(), editText3.getText().toString()));
                    edittext.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    lbl.setText("Inserted Successfully");
                } else {
                    edittext.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    lbl.setText("Record is existing");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FetchData.class));
            }
        });
    }
}