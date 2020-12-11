package com.gs.medicaldiagnosisexpertsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MenuViewHolder>{

    private Context mContext;
    private List<MainMenuList> menuList;
    private OnMainListener mOnMainListener;

    public MainMenuAdapter(Context mContext, List<MainMenuList> menuList,OnMainListener onMainListener) {
        this.mContext = mContext;
        this.menuList = menuList;
        this.mOnMainListener=onMainListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.list_layout,null);
        MenuViewHolder holder= new MenuViewHolder(view,mOnMainListener);
        return new MenuViewHolder(view,mOnMainListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MainMenuList main =menuList.get(position);
        holder.textViewTitle.setText(main.getTitle());
        holder.textViewDesc.setText(main.getDesc());

        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(main.getImage()));

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDesc;
        OnMainListener onMainListener;

        public MenuViewHolder(@NonNull View itemView, OnMainListener onMainListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDesc=itemView.findViewById(R.id.textViewShortDesc);
            this.onMainListener=onMainListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMainListener.onMainClick(getAdapterPosition());

        }
    }
    public interface OnMainListener{
        void onMainClick(int position);
    }
}
