package ru.evapps.testtask.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.evapps.testtask.R;
import ru.evapps.testtask.db.entities.EmployeeEntity;
import ru.evapps.testtask.db.entities.SpecialityEntity;
import ru.evapps.testtask.mvp.presenters.EmployeeDetailsPresenter;
import ru.evapps.testtask.mvp.views.EmployeeDetailsView;

/**
 * Created by ektitarev on 11/01/2019.
 *
 */

public class EmployeeDetailsFragment extends MvpAppCompatFragment implements EmployeeDetailsView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.container)
    View container;

    @BindView(R.id.first_last_name)
    TextView firstLastName;

    @BindView(R.id.birthday)
    TextView birthday;

    @BindView(R.id.age)
    TextView age;

    @BindView(R.id.employee_specialities)
    TextView employeeSpecialities;

    @InjectPresenter
    EmployeeDetailsPresenter presenter;

    @ProvidePresenter
    public EmployeeDetailsPresenter providePresenter() {
        return new EmployeeDetailsPresenter(getArguments());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavHostFragment.findNavController(this).popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressBar(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        container.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getView(), R.string.error_load_employee_details, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmployeeFirstLastName(String firstLastName) {
        this.firstLastName.setText(firstLastName);
    }

    @Override
    public void showEmployeeBirthDay(String birthday) {
        this.birthday.setText(birthday);
    }

    @Override
    public void showEmployeeAge(String age) {
        this.age.setText(age);
    }

    @Override
    public void showEmployeeSpecialities(String specialities) {
        employeeSpecialities.setText(specialities);
    }
}
