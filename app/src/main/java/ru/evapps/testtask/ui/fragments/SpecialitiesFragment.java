package ru.evapps.testtask.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.evapps.testtask.R;
import ru.evapps.testtask.db.entities.SpecialityEntity;
import ru.evapps.testtask.mvp.presenters.SpecialitiesPresenter;
import ru.evapps.testtask.mvp.views.SpecialitiesView;
import ru.evapps.testtask.ui.adapters.SpecialitiesAdapter;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

public class SpecialitiesFragment extends MvpAppCompatFragment implements SpecialitiesView {

    @InjectPresenter
    SpecialitiesPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.root_container)
    View rootContainer;

    @BindView(R.id.specialities_list)
    RecyclerView specialitiesList;

    @ProvidePresenter
    SpecialitiesPresenter providePresenter() {
        Activity activity = getActivity();
        SharedPreferences preferences = null;
        if (activity != null) {
            preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        }

        return new SpecialitiesPresenter(preferences);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialities, container, false);
        ButterKnife.bind(this, view);
        prepareRecyclerView();
        return view;

    }

    private void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        specialitiesList.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.drawable_recycler_view_divider));

        specialitiesList.addItemDecoration(itemDecoration);

        SpecialitiesAdapter adapter = new SpecialitiesAdapter(getActivity());
        specialitiesList.setAdapter(adapter);
    }

    @Override
    public void showProgressBar(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        specialitiesList.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(rootContainer, R.string.error_load_specialities, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showSpecialities(List<SpecialityEntity> specialities) {
        RecyclerView.Adapter adapter = specialitiesList.getAdapter();
        if (adapter instanceof SpecialitiesAdapter) {
            SpecialitiesAdapter specialitiesAdapter = (SpecialitiesAdapter) adapter;
            specialitiesAdapter.setItems(specialities);
        }
    }
}
