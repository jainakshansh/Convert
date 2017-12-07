package com.madhouseapps.convert.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
    private int catColor;

    public ConversionAdapter(Context context, List<Conversion> conversionList, int catColor) {
        super(context, 0, conversionList);
        this.context = context;
        this.conversionList = conversionList;
        this.catColor = catColor;
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
        unitsText.setTypeface(solomon, Typeface.BOLD);
        valuesText.setTypeface(solomon, Typeface.BOLD);

        switch (catColor) {
            case 0:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.redGrad));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.redGrad));
                break;
            case 1:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.blueGrad));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.blueGrad));
                break;
            case 2:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.yellowGrad));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.yellowGrad));
                break;
            case 3:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.greenGrad));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.greenGrad));
                break;
            case 4:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.sublimeGrad));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.sublimeGrad));
                break;
            case 5:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.deepspace));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.deepspace));
                break;
            case 6:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.sweetmorning));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.sweetmorning));
                break;
            case 7:
                unitsText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.hersheys));
                valuesText.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.hersheys));
                break;
        }

        unitsText.setText(conversionList.get(position).getUnits());
        valuesText.setText(String.format(conversionList.get(position).getValues(), "%.4f"));

        return view;
    }

    @Override
    public int getCount() {
        return conversionList.size();
    }
}
