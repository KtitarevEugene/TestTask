package ru.evapps.testtask.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.evapps.testtask.R;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.mvp.presenters.EmployeesPresenter;
import ru.evapps.testtask.mvp.views.EmployeesView;
import ru.evapps.testtask.ui.adapters.BaseRecyclerViewAdapter;
import ru.evapps.testtask.ui.adapters.EmployeesAdapter;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class EmployeesFragment extends MvpAppCompatFragment implements EmployeesView, BaseRecyclerViewAdapter.OnItemClickListener<EmployeeEntity> {

    @BindView(R.id.root_container)
    View rootContainer;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.employees_list)
    RecyclerView employeesList;

    @InjectPresenter
    EmployeesPresenter presenter;

    @ProvidePresenter
    public EmployeesPresenter providePresenter() {
        return new EmployeesPresenter(getArguments());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, false);
        ButterKnife.bind(this, view);

        prepareRecyclerView();

        return view;
    }

    private void prepareRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        employeesList.setLayoutManager(linearLayoutManager);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.drawable_recycler_view_divider));

        employeesList.addItemDecoration(itemDecoration);

        EmployeesAdapter adapter = new EmployeesAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        employeesList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavHostFragment.findNavController(this).popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        employeesList.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(rootContainer, R.string.error_load_employees, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmployees(List<EmployeeEntity> employees) {
        RecyclerView.Adapter adapter = employeesList.getAdapter();
        if (adapter instanceof EmployeesAdapter) {
            EmployeesAdapter employeesAdapter = (EmployeesAdapter) adapter;
            employeesAdapter.setItems(employees);
        }
    }

    @Override
    public void navigateToEmployeeDetails(Bundle args) {
        NavHostFragment.findNavController(this).navigate(R.id.navigate_to_employee_details, args);
    }

    @Override
    public void onItemClick(EmployeeEntity item, int position) {
        presenter.showEmployeeDetails(item, position);
    }
}
