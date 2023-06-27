package com.seener.pressuretracking.ui.notifications;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.seener.pressuretracking.R;

import java.util.List;

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {
    private List<String> dataList;
    private OnItemClickListener onItemClickListener;

    public StrategyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    public void updateList(List<String> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_item_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = dataList.get(position);
        holder.textView.setText(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getBindingAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}