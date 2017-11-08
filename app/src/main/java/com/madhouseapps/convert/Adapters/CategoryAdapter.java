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

public class CategoryAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> categoryItemList;
    private Typeface solomon;

    public CategoryAdapter(@NonNull Context context, @NonNull List<String> categoryItemList) {
        super(context, 0, categoryItemList);
        this.context = context;
        this.categoryItemList = categoryItemList;
        solomon = Typeface.createFromAsset(context.getAssets(), "fonts/Solomon.ttf");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String item = categoryItemList.get(position);

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_cat, parent, false);
        }

        TextView spinnerText = view.findViewById(R.id.textView_spinnerItem_cat);
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
