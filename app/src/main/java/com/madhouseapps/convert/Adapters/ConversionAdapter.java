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

import com.madhouseapps.convert.Conversion;
import com.madhouseapps.convert.R;

import java.util.List;

/**
 * Created by Akshansh on 06-12-2017.
 */

public class ConversionAdapter extends ArrayAdapter<Conversion> {

    private Context context;
    private List<Conversion> conversionList;
    private Typeface solomon;

    public ConversionAdapter(Context context, List<Conversion> conversionList) {
        super(context, 0, conversionList);
        this.context = context;
        this.conversionList = conversionList;
        solomon = Typeface.createFromAsset(getContext().getAssets(), "fonts/Solomon.ttf");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversion_item, null);
        }

        TextView unitsText = view.findViewById(R.id.conv_unit);
        TextView valuesText = view.findViewById(R.id.conv_value);
        unitsText.setTypeface(solomon);
        valuesText.setTypeface(solomon);
        unitsText.setText(conversionList.get(position).getUnits());
        valuesText.setText(String.format(conversionList.get(position).getValues(), "%.4f"));

        return view;
    }

    @Override
    public int getCount() {
        return conversionList.size();
    }
}
