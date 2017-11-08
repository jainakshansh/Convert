package com.madhouseapps.convert.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.madhouseapps.convert.R;

import java.util.List;

/**
 * Created by Akshansh on 07-11-2017.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> unitItemList;
    private Typeface solomon;

    public SpinnerAdapter(@NonNull Context context, @NonNull List<String> unitItemList) {
        super(context, 0, unitItemList);
        this.context = context;
        this.unitItemList = unitItemList;
        solomon = Typeface.createFromAsset(context.getAssets(), "fonts/Solomon.ttf");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String item = unitItemList.get(position);

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_upper, parent, false);
        }

        TextView spinnerText = view.findViewById(R.id.textView_spinnerItem_upper);
        spinnerText.setTypeface(solomon);
        spinnerText.setText(item);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView dropDownText = (TextView) super.getDropDownView(position, convertView, parent);
        dropDownText.setTypeface(solomon);
        return dropDownText;
    }
}
