package com.tavares.projetoculinaria.fragments;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.adapter.ReceitasAdapter;
import com.tavares.projetoculinaria.constants.Constantes;
import com.tavares.projetoculinaria.loader.ReceitasLoader;
import com.tavares.projetoculinaria.model.Receitas;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceitasFragment extends Fragment
        implements  LoaderManager.LoaderCallbacks<List<Receitas>>  {

    @BindView(R.id.loading_indicator) View mLoadingIndicator;
    @BindView(R.id.empty_view) TextView mEmptyStateTextView;
    @BindView(R.id.recycler_view_receitas) RecyclerView recyclerViewReceitas;


    private final ArrayList<Receitas> listReceitas = new ArrayList<>();
    private ReceitasAdapter mAdapter;
    private boolean mTwoPane;
    private Receitas receitas;
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    public ReceitasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    public void isTwoPane(boolean twoPane) {
        mTwoPane = twoPane;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receitas, container, false);
        ButterKnife.bind(this, view);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                savedInstanceState.getParcelable(Receitas.class.getSimpleName());

            }
        }

        recyclerViewReceitas.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewReceitas.setLayoutManager(mLayoutManager);

        mAdapter = new ReceitasAdapter(mTwoPane, listReceitas,  getActivity(),getFragmentManager());
        recyclerViewReceitas.setAdapter(mAdapter);


        ConnectivityManager connectivityManager =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {

          getLoaderManager().initLoader(Constantes.NEWS_LOADER_ID, null,  this);


        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            recyclerViewReceitas.setVisibility(View.GONE);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(getString(R.string.sem_resultados));
        }

        return view;

    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Receitas.class.getSimpleName(), receitas);
    }




    @NonNull
    @Override
    public Loader<List<Receitas>> onCreateLoader(int i, @Nullable Bundle bundle) {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        recyclerViewReceitas.setVisibility(View.GONE);

        Uri baseUri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new ReceitasLoader(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Receitas>> loader, List<Receitas> receitasList) {
        mLoadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setVisibility(View.VISIBLE);
        mEmptyStateTextView.setText(R.string.sem_resultados);

        mAdapter.clear();

        if (receitasList != null && !receitasList.isEmpty()) {
            recyclerViewReceitas.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setVisibility(View.GONE);
            mAdapter.addAll(receitasList);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Receitas>> loader) {
        mAdapter.clear();
    }







}
