package com.kofigyan.stateprogressbarsample.not_stateprogressbar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kofigyan.stateprogressbarsample.R;
import com.kofigyan.stateprogressbarsample.not_stateprogressbar.utils.Utils;

import java.util.List;

import static com.kofigyan.stateprogressbarsample.not_stateprogressbar.utils.Constants.ASCENDING;
import static com.kofigyan.stateprogressbarsample.not_stateprogressbar.utils.Constants.DESCENDING;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kofi Gyan on 7/12/2016.
 */

public class StatesListAdapter extends RecyclerView.Adapter<StatesListAdapter.ItemViewHolder> {

    private final List<String> items;
    private final Context context;
    private boolean isDescending;

    public StatesListAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public StatesListAdapter(List<String> items, Context context, boolean isDescending) {
        this.items = items;
        this.context = context;
        this.isDescending = isDescending;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView title;
        public final ItemClickListener listener;

        ItemViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getLayoutPosition());
        }

        public interface ItemClickListener {
             void onItemClick(View v, int position);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_states_list, viewGroup, false);
        return new ItemViewHolder(v, (view, position) -> {

            if (items.get(position).equals(ASCENDING)) {
                Intent intent = new Intent(context, Utils.selectActivity(position, Utils.allActivities));
                intent.putExtra(DESCENDING, false);
                context.startActivity(intent);


            } else if (items.get(position).equals(DESCENDING)) {
                Intent intent = new Intent(context, Utils.selectActivity(position - 1, Utils.allActivities));
                intent.putExtra(DESCENDING, true);
                context.startActivity(intent);

            } else {

                Intent intent = new Intent(context, Utils.selectActivity(position, isDescending ? Utils.basicDescendingActivities : Utils.basicActivities));
                context.startActivity(intent);
            }

        });
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.title.setText(items.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}