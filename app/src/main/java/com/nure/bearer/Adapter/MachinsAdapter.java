package com.nure.bearer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nure.bearer.R;

import java.util.ArrayList;

public class MachinsAdapter extends ArrayAdapter<Machine> {

    public static final String ZERO = "0";

    public MachinsAdapter(@NonNull Context context, ArrayList<Machine> arrayList) {
        super(context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.machins_array, parent, false);
        }
        Machine currentNumberPosition = getItem(position);
        TextView machineName = currentItemView.findViewById(R.id.machineName);
        machineName.setText(currentNumberPosition.getName());
        TextView machineLocation = currentItemView.findViewById(R.id.machineLocation);
        machineLocation.setText(currentNumberPosition.getLocation());
        TextView machineSale = currentItemView.findViewById(R.id.machineSale);
        if (currentNumberPosition.getListOfCells() == null) {
            machineSale.setText(ZERO);
        } else
            machineSale.setText(currentNumberPosition.getListOfCells().size());


        return currentItemView;
    }

}
