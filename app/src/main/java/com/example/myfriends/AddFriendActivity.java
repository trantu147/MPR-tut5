package com.example.myfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myfriends.models.Friend;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ImageButton btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get data from inputs & create object
                Friend friend = new Friend("New Friend", "new@gmail.com","032125332");

                Intent intent = new Intent();
                intent.putExtra("FRIEND", friend);

                setResult(RESULT_OK,intent);
                //finish the current activity & back to the previous one
                finish();
            }
        });
    }
}