package com.tavares.projetoculinaria.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.model.Ingredientes;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientesAdapter extends RecyclerView.Adapter<IngredientesAdapter.NewsViewHolder> {
    private final List<Ingredientes> listaIngredients;

    public IngredientesAdapter(List<Ingredientes> listaIngredients, Context mContext) {
        this.listaIngredients = listaIngredients;
        Context mContext1 = mContext;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View newsView = LayoutInflater.from(context).inflate(R.layout.adapter_ingredientes, parent, false);
        return new NewsViewHolder(newsView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Ingredientes ingredientes = listaIngredients.get(position);

        holder.ingredient.setText(
                                    ingredientes.getQuantity() +
                                            "   "
                                    + ingredientes.getMeasure() +
                                            " of "
                                   +ingredientes.getIngredient() );

    }


    @Override
    public int getItemCount() {
        if(listaIngredients == null)
            return 0;
        return listaIngredients.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_ingredientes)TextView ingredient;

        NewsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

         }
    }


}