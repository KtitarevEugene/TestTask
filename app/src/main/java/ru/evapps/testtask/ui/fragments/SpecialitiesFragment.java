package ru.evapps.testtask.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import ru.evapps.testtask.R;

/**
 * Created by ektitarev on 09/01/2019.
 *
 */

public class SpecialitiesFragment extends MvpAppCompatFragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specialities, container, false);
        ButterKnife.bind(this, view);

        return view;

    }
}
