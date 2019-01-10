package ru.evapps.testtask.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.evapps.testtask.R;
import ru.evapps.testtask.db.entities.SpecialityEntity;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public class SpecialitiesAdapter extends BaseRecyclerViewAdapter<SpecialityEntity, SpecialitiesAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.speciality_name)
        TextView specialityName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public SpecialitiesAdapter(Context context, List<SpecialityEntity> items) {
        super(context, items);
    }

    public SpecialitiesAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_speciality, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SpecialityEntity currentEntity = items.get(viewHolder.getAdapterPosition());

        viewHolder.specialityName.setText(currentEntity.getName());
        viewHolder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(items.get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
            }
        });
    }
}
