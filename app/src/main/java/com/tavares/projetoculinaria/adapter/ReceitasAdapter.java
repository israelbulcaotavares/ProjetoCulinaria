package com.tavares.projetoculinaria.adapter;

import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.activity.IngredientesActivity;
import com.tavares.projetoculinaria.data.ReceitaContract;
import com.tavares.projetoculinaria.fragments.IngredientesFragment;
import com.tavares.projetoculinaria.model.Ingredientes;
import com.tavares.projetoculinaria.model.Receitas;
import com.tavares.projetoculinaria.widget.ReceiraWidgetService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public  class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Receitas> listReceitas;
    private final android.app.FragmentManager mFragmentManager;
    private final boolean mTwoPane;

    public ReceitasAdapter(boolean twoPane,  List<Receitas> receitas, Context context,android.app.FragmentManager fragmentManager ) {
        this.mTwoPane = twoPane;
        this.listReceitas = receitas;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View newsView = LayoutInflater.from(context).inflate(R.layout.adapter_receita, parent, false);
        return new ReceitasAdapter.ViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Receitas receitas = listReceitas.get(position);

        holder.name.setText(receitas.getmName());
        holder.imageReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTwoPane) {
                    IngredientesFragment ingredientesFragment = new IngredientesFragment();
                    ingredientesFragment.setmReceitas(receitas);
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.container_ingredientes, ingredientesFragment).commit();


                }else {
                    Intent intent = new Intent(mContext, IngredientesActivity.class);
                    intent.putExtra(Receitas.class.getSimpleName(),  receitas);
                    mContext.startActivity(intent);

                }
            }
        });
        ImageView imageView = holder.imageReceita;
        Glide.with(mContext)
                .load(receitas.getmImage())
                .placeholder(R.drawable.receitas)
                .error(R.drawable.receitas)
                .into(imageView);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long timeAdded = System.currentTimeMillis();
                addIngredientDatabase(  getIngredientsInOnePiece() ,timeAdded   );
                Toast.makeText(mContext, R.string.sucesso,Toast.LENGTH_SHORT).show();
            }


            private String getIngredientsInOnePiece() {
                ArrayList<Ingredientes> ingredientsArrayList = receitas.getIngredientesArrayList();
                StringBuilder ingredientsParaSerSalvo = new StringBuilder();

                for (int i = 0; i < ingredientsArrayList.size(); i++) {
                    ingredientsParaSerSalvo
                            .append(ingredientsArrayList.get(i).getQuantity())
                            .append("")
                            .append(ingredientsArrayList.get(i).getMeasure())
                            .append("of ")
                            .append(ingredientsArrayList.get(i).getIngredient())
                            .append("\n");
                }
                return ingredientsParaSerSalvo.toString();
            }


            private void addIngredientDatabase(String ingredients, long date) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ReceitaContract.ReceitaEntry.RECEITA_NAME_COLUMN, receitas.getmName());
                contentValues.put(ReceitaContract.ReceitaEntry.INGREDIENTS_COLUMN, ingredients);
                contentValues.put(ReceitaContract.ReceitaEntry.TIME_STAMP_COLUMN, date);

                Uri uri = mContext.getContentResolver().insert(ReceitaContract.ReceitaEntry.CONTENT_URI, contentValues);
                onInsertingCompleted(uri);
            }

            private void onInsertingCompleted(Uri cakeUri) {
                if (cakeUri != null) {
                    Toast.makeText(mContext, R.string.sucesso ,  Toast.LENGTH_SHORT).show();
                    updateWidget();

                } else {
                    Toast.makeText(mContext, R.string.erro, Toast.LENGTH_SHORT).show();
                }
            }

            private void updateWidget() {
                ReceiraWidgetService.startActionUpdateWidget(mContext);
            }

        });
    }

    @Override
    public int getItemCount() {
        if(listReceitas == null)
            return 0;
        return listReceitas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.receita_name)TextView name;
        @BindView(R.id.receita_favorite)ImageView favorite;
        @BindView(R.id.image_receita)ImageView imageReceita;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    public void clear(){
        listReceitas.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Receitas> receitasList){
        listReceitas.addAll(receitasList);
        notifyDataSetChanged();
    }
}