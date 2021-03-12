package com.example.myfriends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myfriends.adapters.FriendAdapters;
import com.example.myfriends.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int FRIEND_ADDED = 1;
    private List<Friend> friends;
    private FriendAdapters friendAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler View
        RecyclerView rvFriends = findViewById(R.id.rvFriends);

        //demo data
        friends = new ArrayList<>();
        friends.add(new Friend("Tu", "trantu1472@gmail.com","0921328923324"));
        friends.add(new Friend("Ty", "ty43@gmail.com","092142354524"));
        friends.add(new Friend("Tom", "tomtr@gmail.com","0423542743"));
        friends.add(new Friend("Yvone", "yvone@gmail.com","02323454675"));

        //set up recycle view
        //adapter
        friendAdapters = new FriendAdapters(friends);
        rvFriends.setAdapter(friendAdapters);
        //layout manager
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        //handle add action
        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADDED);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == FRIEND_ADDED){
            Friend friend = (Friend) data.getSerializableExtra("FRIEND");

            friends.add(0, friend);

            //notify the adapter to update the recycler
            //friendAdapters.notifyDataSetChanged();
            friendAdapters.notifyItemInserted(0);
        }
    }
}