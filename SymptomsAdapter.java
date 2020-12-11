package com.gs.medicaldiagnosisexpertsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.SymptomsViewHolder> {
private Context mContext;
private List<SymptomsList> symptomsLists;
    private SymptomsAdapter.OnSymptomsListener mOnSymptomsListener;

    public SymptomsAdapter(Context mContext, List<SymptomsList> symptomsLists, OnSymptomsListener mOnSymptomsListener) {
        this.mContext = mContext;
        this.symptomsLists = symptomsLists;
        this.mOnSymptomsListener = mOnSymptomsListener;
    }

    @NonNull
    @Override
    public SymptomsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.symptoms_layout,null);
        SymptomsViewHolder holder=new SymptomsViewHolder(view,mOnSymptomsListener);
        return new SymptomsViewHolder(view,mOnSymptomsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SymptomsViewHolder holder, int position) {
         SymptomsList symptoms= symptomsLists.get(position);
         holder.textviewTitle.setText(symptoms.getTitle());
         holder.textViewDesc.setText(symptoms.getDesc());
         holder.imageView.setImageDrawable(mContext.getResources().getDrawable(symptoms.getImage()));
    }

    @Override
    public int getItemCount() {
        return symptomsLists.size();
    }

    class SymptomsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textviewTitle,textViewDesc;
        OnSymptomsListener onSymptomsListener;


        public SymptomsViewHolder(@NonNull View itemView,OnSymptomsListener onSymptomsListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewSymptoms);
            textviewTitle=itemView.findViewById(R.id.textViewTitle);
            textViewDesc=itemView.findViewById(R.id.textViewShortDesc);
            this.onSymptomsListener=onSymptomsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSymptomsListener.onSymptomsClick(getAdapterPosition());

        }
    }
    public interface OnSymptomsListener{
        void onSymptomsClick(int position);

    }
}
