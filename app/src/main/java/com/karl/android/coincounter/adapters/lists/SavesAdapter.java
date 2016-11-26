package com.karl.android.coincounter.adapters.lists;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karl.android.coincounter.R;
import com.karl.android.templates.SlimSave;

import java.util.List;

/**
 * Created by Karl on 25/11/2016.
 *
 * The adapter for the recycler view for the saves list
 */

public class SavesAdapter extends RecyclerView.Adapter<SavesAdapter.MyViewHolder>{

    private List<SlimSave> savesList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, amount, date;

        public MyViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.my_saves_adapter_title_text_view);
            amount = (TextView) view.findViewById(R.id.my_saves_adapter_amount_text_view);
            date = (TextView) view.findViewById(R.id.my_saves_adapter_date_text_view);
        }
    }

    public SavesAdapter(List<SlimSave> savesList){
        this.savesList = savesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saves_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SlimSave save = savesList.get(position);
        holder.title.setText(save.getTitle());
        holder.amount.setText(save.getAmount());
        holder.date.setText(save.getDate());
    }

    @Override
    public int getItemCount() {
        return savesList.size();
    }
}
