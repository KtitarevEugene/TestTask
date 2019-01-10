package ru.evapps.testtask.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ektitarev on 10/01/2019.
 *
 */

public abstract class BaseRecyclerViewAdapter<ItemType, ViewHolderType extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolderType> {

    public interface OnItemClickListener<T> {
        void onItemClick(T item, int position);
    }

    protected List<ItemType> items;
    protected Context context;
    protected OnItemClickListener<ItemType> listener;

    public BaseRecyclerViewAdapter(Context context, List<ItemType> items) {
        this.context = context;
        this.items = items;
    }

    public BaseRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public void setItems(List<ItemType> items) {
        this.items = items;

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<ItemType> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public abstract ViewHolderType onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override
    public abstract void onBindViewHolder(@NonNull ViewHolderType viewHolderType, int i);

    @Override
    public int getItemCount() {
        return items.size();
    }
}
