package com.tavares.projetoculinaria.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.adapter.IngredientesAdapter;
import com.tavares.projetoculinaria.adapter.StepsAdapter;
import com.tavares.projetoculinaria.model.Ingredientes;
import com.tavares.projetoculinaria.model.Receitas;
import com.tavares.projetoculinaria.model.Steps;
import java.util.ArrayList;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientesFragment extends Fragment{
    @BindView(R.id.recycler_view_ingredients) RecyclerView recyclerViewIngredientes;
    @BindView(R.id.recycler_view_steps) RecyclerView recyclerViewSteps;
    @BindView(R.id.text_nome_receita) TextView mNameReceita;

    private int mRecipeId;
    private Receitas receitas;
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    public IngredientesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

     View view = inflater.inflate(R.layout.fragment_ingredientes, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                savedInstanceState.getParcelable(Receitas.class.getSimpleName());

            }
        }


        if(receitas == null){
            receitas = Objects.requireNonNull(getActivity().getIntent()).getParcelableExtra(Receitas.class.getSimpleName());
        }

        setupAdapters();

        return view;

    }

    private void setupAdapters(){
        if (receitas !=  null){

            //todo: INGREDIENTES
            LinearLayoutManager layoutManager  = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewIngredientes.setLayoutManager(layoutManager);

            ArrayList<Ingredientes> ingredientarrayList = receitas.getIngredientesArrayList();
            IngredientesAdapter ingredientAdapter = new IngredientesAdapter(ingredientarrayList, getActivity());
            recyclerViewIngredientes.setAdapter(ingredientAdapter);

            //todo: STEPS
            recyclerViewSteps.setLayoutManager(new LinearLayoutManager(getActivity()));
            ArrayList<Steps> stepsArrayList = receitas.getStepsArrayList();
            StepsAdapter stepsAdapter = new StepsAdapter(stepsArrayList, getActivity());
            recyclerViewSteps.setAdapter(stepsAdapter);

            //Nome da receita
            mNameReceita.setText(receitas.getmName());
            mRecipeId = receitas.getmId();

        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Receitas.class.getSimpleName(), receitas);
    }


    public void setmReceitas(Receitas mReceitas) {
        receitas = mReceitas;
    }


}
