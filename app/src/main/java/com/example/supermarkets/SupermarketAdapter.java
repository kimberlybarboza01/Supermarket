package com.example.supermarkets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SupermarketAdapter extends RecyclerView.Adapter {
    private ArrayList<Supermarket> supermarketData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public class SupermarketViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewSupermarket;
        public TextView textAverageRate;
        public Button deleteButton;
        public SupermarketViewHolder(@NonNull View itemView){
            super(itemView);
            textViewSupermarket = itemView.findViewById(R.id.textViewName);
            textAverageRate = itemView.findViewById(R.id.textViewAvg);
            deleteButton = itemView.findViewById(R.id.buttonDeleteSupermarket);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public TextView getTextViewSupermarket(){
            return textViewSupermarket;
        }
        public TextView getTextAverageRate (){
            return textAverageRate;
        }
        public Button getDeleteButton(){
            return deleteButton;
        }
    }
    public SupermarketAdapter(ArrayList<Supermarket> arrayList, Context context){
        supermarketData = arrayList;
        parentContext = context;
    }
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new SupermarketViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        SupermarketViewHolder cvh = (SupermarketViewHolder) holder;
        cvh.getTextViewSupermarket().setText(supermarketData.get(position).getSupermarketName());
       // cvh.getTextAverageRate().setText(supermarketData.get(position).getTextAverage());
        if (isDeleting) {
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else {
            cvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return supermarketData.size();
    }
    private void deleteItem(int position) {
        Supermarket supermarket = supermarketData.get(position);
        MarketDataSource ds = new MarketDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteSupermarket(supermarket.getSupermarketID());
            ds.close();
            if (didDelete) {
                supermarketData.remove(position);
                notifyDataSetChanged();
            }
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
        }
    }
    public void setDelete(boolean b) {
        isDeleting = b;
    }
}
