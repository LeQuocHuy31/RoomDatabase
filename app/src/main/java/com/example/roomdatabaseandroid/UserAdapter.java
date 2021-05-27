package com.example.roomdatabaseandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<user> mListUser;
    private IClickItemUser iClickItemUser;

    public interface  IClickItemUser{
        void updateUser(user user);

        void deleteUser(user user);
    }

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }


    public void setData(List<user> list){
        this.mListUser=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        user user=mListUser.get(position);
        if(user==null)
        {
            return;
        }
        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());
        holder.tvYear.setText(user.getYear());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.updateUser(user);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemUser.deleteUser(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mListUser !=null)
        {
            return  mListUser.size();
        }
        return 0;
    }

    public  class  UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        private TextView tvAddress;
        private TextView tvYear;
        private Button btnUpdate;
        private Button btnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername=itemView.findViewById(R.id.tv_username);
            tvAddress=itemView.findViewById(R.id.tv_address);
            tvYear=itemView.findViewById(R.id.tv_year);
            btnUpdate=itemView.findViewById(R.id.btn_update);
            btnDelete=itemView.findViewById(R.id.btn_delete);

        }
    }
}
