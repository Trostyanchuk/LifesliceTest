package com.lifeslicetest.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifeslicetest.R;
import com.lifeslicetest.databinding.ItemRecordBinding;
import com.lifeslicetest.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.Holder> {

    private final LayoutInflater layoutInflater;
    private List<Record> recordsList;
    private OnClickListener onItemClickListener;
    private int choice = 0;

    public RecordsAdapter(Context context, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.recordsList = new ArrayList<>();
        this.onItemClickListener = onClickListener;
    }

    public void setRecords(List<Record> records) {
        this.recordsList = records;
        notifyDataSetChanged();
    }

    public void setRecordChoice(int position) {
        if (position == recordsList.size() - 1) {
            notifyItemChanged(position);
            notifyItemChanged(0);
        } else {
            notifyItemRangeChanged(position, 2);
        }
    }

    public int getCurrent() {
        return choice;
    }

    public Record getNext() {
        setRecordChoice(choice);
        if (choice < recordsList.size() - 1) {
            return recordsList.get(++choice);
        }
        choice = 0;
        return recordsList.get(0);
    }

    public List<Record> getRecords() {
        return recordsList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder((ItemRecordBinding) DataBindingUtil.inflate(layoutInflater,
                R.layout.item_record, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Record record = recordsList.get(position);
        holder.bindingObject.setImageUri(record.getThumbnailUrl());
        holder.bindingObject.setUsername(record.getUserName());
        holder.bindingObject.setChose(choice == position);
    }

    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    public interface OnClickListener {
        void onItemClick(Record record);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemRecordBinding bindingObject;

        public Holder(ItemRecordBinding binding) {
            super(binding.getRoot());
            bindingObject = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                choice = getAdapterPosition();
                onItemClickListener.onItemClick(recordsList.get(choice));
            }
        }
    }
}
