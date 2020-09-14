package com.example.soulfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private ArrayList<DishItem> mDishList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onMinusButtonClick(int position);
        void onPlusButtonClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public Button mButton1;
        public Button mButton2;

        public ViewHolder(View ItemView, final OnItemClickListener listener) {
            super(ItemView);
            mImageView = itemView.findViewById(R.id.imageView5);
            mTextView1 = itemView.findViewById(R.id.dishName1);
            mTextView2 = itemView.findViewById(R.id.currentItemCount);
            mTextView3 = itemView.findViewById(R.id.dishPrice1);
            mButton1 = ItemView.findViewById(R.id.minusButton1);
            mButton2 = ItemView.findViewById(R.id.plusButton1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onMinusButtonClick(position);
                        }
                    }
                }
            });
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPlusButtonClick(position);
                        }
                    }
                }
            });
        }
    }

    public OrderAdapter(ArrayList<DishItem> dishList) {
        mDishList = dishList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item_order, parent, false);
        ViewHolder evh = new ViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DishItem currentItem = mDishList.get(position);
        holder.mImageView.setImageResource(currentItem.getPhotoId());
        holder.mTextView1.setText(currentItem.getDishName());
        holder.mTextView2.setText(Integer.toString(currentItem.getDishCount()));
        holder.mTextView3.setText("$" + Integer.toString(currentItem.getDishPrice() * currentItem.getDishCount()));
    }

    @Override
    public int getItemCount() {
        return mDishList.size();
    }
}
