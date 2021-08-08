package com.example.ums;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
   public List<User> users;
    DatabaseHelper helper;

    public UserAdapter(Context context, List<User> allUser){
         this.context=context;
         this.users=allUser;
         helper=new DatabaseHelper(this.context);
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler_content,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.userName.setText(users.get(position).getUserName());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {


        TextView userName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            userName=itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, MainActivity.class);
            intent.putExtra("POSITION",getAdapterPosition());
            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context,"Don't Press Me Please!",Toast.LENGTH_LONG).show();
            users.remove(getAdapterPosition());
            helper.delete(getAdapterPosition());
            notifyDataSetChanged();

            return true;
        }
    }

}
