package com.kokutha.kcube.copydatabase;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {
    private Context context;
    private List<UserData> list = Collections.emptyList();
    private LayoutInflater inflater;
    private String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public void SwapList(List<UserData> dataList) {
        this.list = dataList;
        notifyDataSetChanged();
    }

    public UserListAdapter(Context context, List<UserData> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.user_name.setText(list.get(position).getName());
        holder.user_phone.setText(list.get(position).getPhone());
        holder.user_address.setText(list.get(position).getAddress());
        String name = list.get(position).getName();
        String firstCharName = name.substring(0, 1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharName, getColor());
        holder.user_image.setImageDrawable(drawable);
    }

    private int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView user_image;
        TextView user_name;
        TextView user_phone;
        TextView user_address;
        ImageButton user_detail_btn;

        MyViewHolder(View itemView) {
            super(itemView);
            user_image = (ImageView) itemView.findViewById(R.id.ivUser);
            user_name = (TextView) itemView.findViewById(R.id.user_name);
            user_phone = (TextView) itemView.findViewById(R.id.user_phone);
            user_address = (TextView) itemView.findViewById(R.id.user_address);
            user_detail_btn = (ImageButton) itemView.findViewById(R.id.user_detail_btn);
        }
    }
}
