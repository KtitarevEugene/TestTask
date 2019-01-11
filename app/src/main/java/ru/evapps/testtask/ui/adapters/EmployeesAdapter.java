package ru.evapps.testtask.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.evapps.testtask.R;
import ru.evapps.testtask.common.Utils;
import ru.evapps.testtask.db.entities.EmployeeEntity;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class EmployeesAdapter extends BaseRecyclerViewAdapter<EmployeeEntity, EmployeesAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.employee_name)
        TextView employeeName;

        @BindView(R.id.employee_age)
        TextView employeeAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public EmployeesAdapter(Context context, List<EmployeeEntity> items) {
        super(context, items);
    }

    public EmployeesAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_employee, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EmployeeEntity currentItem = items.get(viewHolder.getAdapterPosition());

        viewHolder.employeeName.setText(String.format(
                Locale.getDefault(),
                context.getString(R.string.first_last_names),
                Utils.upperCaseFirstLetter(currentItem.getFirstName()), Utils.upperCaseFirstLetter(currentItem.getLastName())));

        String birthday = currentItem.getBirthday();
        if (birthday != null && !birthday.isEmpty()) {
            viewHolder.employeeAge.setText(Utils.formatAge(context, Utils.calcAge(Utils.stringToDate(currentItem.getBirthday()))));
        } else {
            viewHolder.employeeAge.setText(R.string.empty_birthday);
        }

        viewHolder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(items.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
            }
        });
    }
}
