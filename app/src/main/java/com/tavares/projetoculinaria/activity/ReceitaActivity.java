package com.tavares.projetoculinaria.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.fragments.ReceitasFragment;

public class ReceitaActivity extends AppCompatActivity {
    private boolean  mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);


        mTwoPane = findViewById(R.id.container_ingredientes) != null;

        ReceitasFragment receitasFragment = new ReceitasFragment();
        receitasFragment.isTwoPane(mTwoPane);
        getFragmentManager().beginTransaction()
                .replace(R.id.container_receitas, receitasFragment)
                .commit();


    }
}
