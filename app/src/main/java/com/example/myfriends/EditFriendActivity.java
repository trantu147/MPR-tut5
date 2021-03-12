package com.example.myfriends;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myfriends.models.Friend;

public class EditFriendActivity extends AppCompatActivity {

    EditText edit_name, edit_address, edit_mobile;
    ImageButton YES,NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        YES=findViewById(R.id.btn_EYes);
        NO =findViewById(R.id.btn_ENO);

        edit_name = findViewById(R.id.edit_name);
        edit_address = findViewById(R.id.edit_address);
        edit_mobile=findViewById(R.id.edit_mobile);


        YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent input= getIntent();
                String pos = input.getStringExtra("position_edit");
                Log.d("nhận được pos ",pos);

                String  inpName=edit_name .getText().toString();
                String inpAddr=  edit_address.getText().toString();
                String inpMo= edit_mobile.getText().toString();
                Log.d("name ===",inpName);
                if(!inpAddr.equals("")&&!inpMo.equals("")&&!inpMo.equals("")){

                    Friend f= new Friend(inpName,inpAddr,inpMo);
                    Intent i = new Intent(EditFriendActivity.this,MainActivity.class);

                    Log.d("friend",f.toString());
                    i.putExtra("edited_f",f);
                    i.putExtra("pos", pos);
                    startActivity(i);
                }
                else{
                    new AlertDialog.Builder(EditFriendActivity.this)
                            .setIcon(android.R.drawable.ic_delete).setTitle("Alert dialog")
                            .setMessage("you should complete form!")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .show();

                }

            }
        });

        NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
