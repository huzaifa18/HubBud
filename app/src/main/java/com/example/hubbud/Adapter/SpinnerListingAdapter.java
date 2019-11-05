package com.example.hubbud.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hubbud.Model.DataIds;
import com.example.hubbud.R;

import java.util.List;

public class SpinnerListingAdapter extends BaseAdapter {
    private Context context;
    private List<DataIds> coreList;
    private LayoutInflater inflater;


    public SpinnerListingAdapter(Context context, List< DataIds> list) {
        this.context = context;
        this.coreList = list;
    }

    @Override
    public int getCount() {
        return coreList.size();
    }

    @Override
    public Object getItem(int i) {
        return coreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_dropdown_item, null);
        }

        TextView txtName = (TextView) view.findViewById(R.id.text1);

        DataIds bean = coreList.get(i);
        String name = bean.getName();
        String id = bean.getId();

        txtName.setText(name);

        Toast.makeText(context,"Id: " + id,Toast.LENGTH_LONG);

        return view;
    }

    @Override
    public int getItemViewType(int i) {

        Toast.makeText(context,"Id: " + coreList.get(i).getId(),Toast.LENGTH_LONG);
        return super.getItemViewType(i);
    }
}