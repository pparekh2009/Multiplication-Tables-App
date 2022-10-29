package com.priyanshparekh.multiplicationtables.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.priyanshparekh.multiplicationtables.R;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    Context context;
    ArrayList<String> tableArray;

    public TableAdapter(Context context, ArrayList<String> tableArray) {
        this.context = context;
        this.tableArray = tableArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvRow.setText(tableArray.get(position));
    }

    @Override
    public int getItemCount() {
        return tableArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRow = itemView.findViewById(R.id.textView2);
        }
    }
}
