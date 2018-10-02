package com.tavares.projetoculinaria.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.activity.StepsActivity;
import com.tavares.projetoculinaria.model.Receitas;
import com.tavares.projetoculinaria.model.Steps;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.NewsViewHolder> {
    private final List<Steps> listaSteps;
    private final Context mContext;

    public StepsAdapter(List<Steps> listaSteps, Context mContext) {
        this.listaSteps = listaSteps;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View newsView = LayoutInflater.from(context).inflate(R.layout.adapter_steps, parent, false);
        return new NewsViewHolder(newsView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Steps steps = listaSteps.get(position);

        holder.detalhes.setText("Step " + steps.getStepID()  +
                                   ": " + steps.getmShortDescription());
        holder.detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StepsActivity.class);
                intent.putExtra(Receitas.class.getSimpleName(), steps);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listaSteps == null)
            return 0;
        return listaSteps.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.short_description) TextView detalhes;

        NewsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

         }
    }

}