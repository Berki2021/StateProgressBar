package com.kofigyan.stateprogressbarsample.not_stateprogressbar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kofigyan.stateprogressbarsample.R;
import com.kofigyan.stateprogressbarsample.not_stateprogressbar.pojo.ApiFeature;
import com.kofigyan.stateprogressbarsample.not_stateprogressbar.utils.Utils;

import java.util.List;

import static com.kofigyan.stateprogressbarsample.not_stateprogressbar.utils.Constants.IS_DESCENDING_ASCENDING_OPTIONS;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kofi Gyan on 7/12/2016.
 */

public class ApiFeatureAdapter extends RecyclerView.Adapter<ApiFeatureAdapter.ItemViewHolder> {

    private final List<ApiFeature> apiFeatures;
    private final Context context;

    public ApiFeatureAdapter(List<ApiFeature> apiFeatures, Context context) {
        this.apiFeatures = apiFeatures;
        this.context = context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView title;
        final TextView description;
        public final ItemClickListener listener;

        ItemViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            description = (TextView) itemView.findViewById(R.id.tvDescription);
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
        return apiFeatures.size();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_api_feature, viewGroup, false);
        return new ItemViewHolder(v, (view, position) -> {
            Intent intent = new Intent(context, Utils.selectActivity(position, Utils.allActivities));
            intent.putExtra(IS_DESCENDING_ASCENDING_OPTIONS, true);
            context.startActivity(intent);
        });
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.title.setText(apiFeatures.get(i).getTitle());
        itemViewHolder.description.setText(apiFeatures.get(i).getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}