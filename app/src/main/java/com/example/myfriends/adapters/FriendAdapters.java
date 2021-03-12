package com.example.myfriends.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfriends.EditFriendActivity;
import com.example.myfriends.R;
import com.example.myfriends.models.Friend;

import java.util.List;

public class FriendAdapters extends RecyclerView.Adapter<FriendAdapters.FriendHolder> {
    //data set
    private List<Friend> friends;

    public FriendAdapters(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //activity to display
        Context context = parent.getContext();

        //xml -> java object
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_friend, parent, false);

        return new FriendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend friend = friends.get(position);

        //bind date with view template
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageButton btnCall, btnSms, btnEdit, btnDelete, btnEmail;
        private Context context;


        public FriendHolder(@NonNull View itemView) {
            super(itemView);
            this.context = context;

            tvName = itemView.findViewById(R.id.tvName);

            btnCall = itemView.findViewById(R.id.btnCall);
            btnSms = itemView.findViewById(R.id.btnSms);
            btnEmail = itemView.findViewById(R.id.btnEmail);

            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);

        }

        public void bind(Friend friend){
            tvName.setText(friend.getName());

            //handle events

            //telephony
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+friend.getPhoneNo()));

                    //start activity
                    context.startActivity(intent);
                }
            });

            //TODO: send email
            btnEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Intent.ACTION_SENDTO));
                    intent.setData(Uri.parse("mailto:"+ Uri.encode(friend.getEmail())));

                    context.startActivity(intent);
                }
            });

            //TODO: send sms
            btnSms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent((Intent.ACTION_SEND));
                    intent.setData(Uri.parse("smsto:"+ friend.getPhoneNo()));

                    context.startActivity(intent);
                }
            });

            //delete this friend
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = friends.indexOf(friend);
                    friends.remove(friend);
                    //notify recycler view to update the change
//                    notifyDataSetChanged();
                    //optimize
                    notifyItemRemoved(position);
                }
            });


            //edit this friend
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, EditFriendActivity.class);
                    String uF = i.getStringExtra("edited_f");
                    if(uF==null){
                        int pos = friends.indexOf(friend);
                        String p = String.valueOf(pos);
                        i.putExtra("position_edit",p);
                        context.startActivity(i);
                    }
                }
            });

        }
    }
}
