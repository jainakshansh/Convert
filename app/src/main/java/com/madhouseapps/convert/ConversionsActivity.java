package com.madhouseapps.convert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.madhouseapps.convert.Adapters.CategoryAdapter;
import com.madhouseapps.convert.Adapters.LowerSpinnerAdapter;
import com.madhouseapps.convert.Adapters.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConversionsActivity extends AppCompatActivity {

    private AppCompatSpinner categorySpinner, fromSpinner, toSpinner;
    private AppCompatEditText fromEdit, toEdit;
    private ImageView share;
    private Button swapButton;
    private LinearLayout upperParent;

    private List<String> categoriesList;
    private List<String> lengthList;
    private List<String> areaList;
    private List<String> volumeList;
    private List<String> weightList;
    private List<String> temperatureList;
    private List<String> dataStorageList;
    private List<String> powerList;
    private List<String> energyList;

    private Typeface solomon;
    private TextWatcher textWatcher;

    private SpinnerAdapter lengthAdapter, areaAdapter, volumeAdapter, weightAdapter, tempAdapter, dataAdapter, powerAdapter, energyAdapter;
    private LowerSpinnerAdapter lLenAdapter, lAreaAdapter, lVolAdapter, lWeightAdapter, lTempAdapter, lDataAdapter, lPowerAdapter, lEnergyAdapter;
    private CategoryAdapter categoryAdapter;

    private int from, to;
    private double res = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversions);

        initViews();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_r);
                        categoryAdapter.notifyDataSetChanged();

                        //Setting up spinner according to category.
                        lengthAdapter.setDropDownViewResource(R.layout.item_dd_r);
                        fromSpinner.setAdapter(lengthAdapter);
                        //Setting up the lower spinner according to the category.
                        lLenAdapter.setDropDownViewResource(R.layout.item_dd_r);
                        toSpinner.setAdapter(lLenAdapter);
                        fromSpinner.setSelection(1);
                        toSpinner.setSelection(5);

                        upperParent.setBackgroundResource(R.drawable.redgradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_r);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.redGrad));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.redGrad));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.redGrad));
                        share.setImageResource(R.drawable.ic_share_r);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 1:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_b);
                        categoryAdapter.notifyDataSetChanged();

                        //Setting up spinner according to category.
                        areaAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        fromSpinner.setAdapter(areaAdapter);
                        //Setting up the lower spinner according to the category.
                        lAreaAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        toSpinner.setAdapter(lAreaAdapter);
                        fromSpinner.setSelection(3);
                        toSpinner.setSelection(2);

                        upperParent.setBackgroundResource(R.drawable.bluegradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blueGrad));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.blueGrad));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.blueGrad));
                        share.setImageResource(R.drawable.ic_share_b);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 2:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_y);
                        categoryAdapter.notifyDataSetChanged();

                        //Setting up spinner according to category.
                        volumeAdapter.setDropDownViewResource(R.layout.item_dd_y);
                        fromSpinner.setAdapter(volumeAdapter);
                        //Setting up the lower spinner according to the category.
                        lVolAdapter.setDropDownViewResource(R.layout.item_dd_y);
                        toSpinner.setAdapter(lVolAdapter);
                        fromSpinner.setSelection(1);
                        toSpinner.setSelection(3);

                        upperParent.setBackgroundResource(R.drawable.yellowgradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_y);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellowGrad));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.yellowGrad));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.yellowGrad));
                        share.setImageResource(R.drawable.ic_share_y);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 3:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_g);
                        categoryAdapter.notifyDataSetChanged();

                        //Setting up spinner according to category.
                        weightAdapter.setDropDownViewResource(R.layout.item_dd_g);
                        fromSpinner.setAdapter(weightAdapter);
                        //Setting up the lower spinner according to the category.
                        lWeightAdapter.setDropDownViewResource(R.layout.item_dd_g);
                        toSpinner.setAdapter(lWeightAdapter);
                        fromSpinner.setSelection(6);
                        toSpinner.setSelection(1);

                        upperParent.setBackgroundResource(R.drawable.greengradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_g);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.greenGrad));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.greenGrad));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.greenGrad));
                        share.setImageResource(R.drawable.ic_share_g);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 4:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_py);
                        categoryAdapter.notifyDataSetChanged();

                        //Setting up spinner according to category.
                        tempAdapter.setDropDownViewResource(R.layout.item_dd_py);
                        fromSpinner.setAdapter(tempAdapter);
                        //Setting up the lower spinner according to the category.
                        lTempAdapter.setDropDownViewResource(R.layout.item_dd_py);
                        toSpinner.setAdapter(lTempAdapter);
                        fromSpinner.setSelection(1);
                        toSpinner.setSelection(2);

                        upperParent.setBackgroundResource(R.drawable.sublimelightgradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_o);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sublimeGrad));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.sublimeGrad));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.sublimeGrad));
                        share.setImageResource(R.drawable.ic_share_o);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 5:
                        dataAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        fromSpinner.setAdapter(dataAdapter);
                        lDataAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        toSpinner.setAdapter(lDataAdapter);

                        upperParent.setBackgroundResource(R.drawable.purplepinegradient);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 6:
                        powerAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        fromSpinner.setAdapter(powerAdapter);
                        lPowerAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        toSpinner.setAdapter(lPowerAdapter);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 7:
                        energyAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        fromSpinner.setAdapter(energyAdapter);
                        lEnergyAdapter.setDropDownViewResource(R.layout.item_dd_b);
                        toSpinner.setAdapter(lEnergyAdapter);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoriesList);
        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_r);
        categorySpinner.setAdapter(categoryAdapter);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (fromEdit.getText().hashCode() == s.hashCode()) {
                    toEdit.removeTextChangedListener(textWatcher);
                    switch (categorySpinner.getSelectedItemPosition()) {
                        case 0:
                            fromLengthConditions();
                            break;
                        case 1:
                            fromAreaConditions();
                            break;
                        case 2:
                            fromVolumeConditions();
                            break;
                        case 3:
                            fromWeightConditions();
                            break;
                        case 4:
                            fromTemperatureConditions();
                            break;
                        case 5:
                            fromStorageConditions();
                            break;
                        case 6:
                            fromPowerConditions();
                            break;
                        case 7:
                            fromEnergyConditions();
                            break;
                    }
                    if (fromEdit.getText().toString().isEmpty()) {
                        toEdit.setText("");
                    }
                    toEdit.addTextChangedListener(textWatcher);
                } else if (toEdit.getText().hashCode() == s.hashCode()) {
                    fromEdit.removeTextChangedListener(textWatcher);
                    switch (categorySpinner.getSelectedItemPosition()) {
                        case 0:
                            toLengthConditions();
                            break;
                        case 1:
                            toAreaConditions();
                            break;
                        case 2:
                            toVolumeConditions();
                            break;
                        case 3:
                            toWeightConditions();
                            break;
                        case 4:
                            toTemperatureConditions();
                            break;
                        case 5:
                            toStorageConditions();
                            break;
                        case 6:
                            toPowerConditions();
                            break;
                        case 7:
                            toEnergyConditions();
                            break;
                    }
                    if (toEdit.getText().toString().isEmpty()) {
                        fromEdit.setText("");
                    }
                    fromEdit.addTextChangedListener(textWatcher);
                }
            }
        };

        fromEdit.addTextChangedListener(textWatcher);
        toEdit.addTextChangedListener(textWatcher);

        dependentSpinners();

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromEdit.removeTextChangedListener(textWatcher);
                toEdit.removeTextChangedListener(textWatcher);
                String fromValue = null, toValue = null;
                if (!fromEdit.getText().toString().trim().isEmpty()) {
                    fromValue = fromEdit.getText().toString().trim();
                }
                if (!toEdit.getText().toString().trim().isEmpty()) {
                    toValue = toEdit.getText().toString().trim();
                }
                int pos1 = fromSpinner.getSelectedItemPosition();
                int pos2 = toSpinner.getSelectedItemPosition();
                fromEdit.setText(toValue);
                toEdit.setText(fromValue);
                fromSpinner.setSelection(pos2);
                toSpinner.setSelection(pos1);
                fromEdit.addTextChangedListener(textWatcher);
                toEdit.addTextChangedListener(textWatcher);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey, check out this amazing unit converter at: https://play.google.com/store/apps/details?id=com.madhouseapps.convert");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
    }

    private void

    initViews() {
        solomon = Typeface.createFromAsset(getAssets(), "fonts/Solomon.ttf");
        categorySpinner = findViewById(R.id.category_spinner);
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        fromEdit = findViewById(R.id.from_edit_text);
        fromEdit.setTypeface(solomon);
        toEdit = findViewById(R.id.to_edit_text);
        toEdit.setTypeface(solomon);
        share = findViewById(R.id.share_app);
        swapButton = findViewById(R.id.swap_button);
        upperParent = findViewById(R.id.upper_parent);

        //Initialising category list.
        categoriesList = new ArrayList<>();
        categoriesList.add("Length");
        categoriesList.add("Area");
        categoriesList.add("Volume");
        categoriesList.add("Weight");
        categoriesList.add("Temperature");
        categoriesList.add("Data Storage");
        categoriesList.add("Power");
        categoriesList.add("Energy");

        //Initialising the length list.
        lengthList = new ArrayList<>();
        lengthList.add("mm");
        lengthList.add("cm");
        lengthList.add("m");
        lengthList.add("km");
        lengthList.add("inch");
        lengthList.add("foot");
        lengthList.add("yard");
        lengthList.add("mile");

        //Initialising the area list.
        areaList = new ArrayList<>();
        areaList.add("sq. cm");
        areaList.add("sq. inch");
        areaList.add("sq. foot");
        areaList.add("sq. m");
        areaList.add("sq. km");
        areaList.add("acre");
        areaList.add("hectare");
        areaList.add("sq. yard");

        //Initialising the volume list.
        volumeList = new ArrayList<>();
        volumeList.add("cu. cm");
        volumeList.add("cu. m");
        volumeList.add("ml");
        volumeList.add("litre");
        volumeList.add("US Pint");
        volumeList.add("US Gallon");

        //Initialising the weight list.
        weightList = new ArrayList<>();
        weightList.add("mg");
        weightList.add("g");
        weightList.add("kg");
        weightList.add("ton");
        weightList.add("carat");
        weightList.add("ounce");
        weightList.add("pound");

        //Initialising the temperature list.
        temperatureList = new ArrayList<>();
        temperatureList.add("°K");
        temperatureList.add("°F");
        temperatureList.add("°C");

        //Initialising the data storage list.
        dataStorageList = new ArrayList<>();
        dataStorageList.add("bytes");
        dataStorageList.add("kb");
        dataStorageList.add("kB");
        dataStorageList.add("Mb");
        dataStorageList.add("MB");
        dataStorageList.add("Gb");
        dataStorageList.add("GB");
        dataStorageList.add("TB");

        //Initialising the energy list.
        energyList = new ArrayList<>();
        energyList.add("mJ");
        energyList.add("J");
        energyList.add("kJ");
        energyList.add("MJ");
        energyList.add("GJ");

        //Initialising the power list.
        powerList = new ArrayList<>();
        powerList.add("watt");
        powerList.add("hW");
        powerList.add("kW");
        powerList.add("MW");
        powerList.add("GW");
        powerList.add("hp");

        //Initialising the adapters.
        categoryAdapter = new CategoryAdapter(getApplicationContext(), categoriesList);
        lengthAdapter = new SpinnerAdapter(getApplicationContext(), lengthList);
        lLenAdapter = new LowerSpinnerAdapter(getApplicationContext(), lengthList);
        areaAdapter = new SpinnerAdapter(getApplicationContext(), areaList);
        lAreaAdapter = new LowerSpinnerAdapter(getApplicationContext(), areaList);
        volumeAdapter = new SpinnerAdapter(getApplicationContext(), volumeList);
        lVolAdapter = new LowerSpinnerAdapter(getApplicationContext(), volumeList);
        weightAdapter = new SpinnerAdapter(getApplicationContext(), weightList);
        lWeightAdapter = new LowerSpinnerAdapter(getApplicationContext(), weightList);
        tempAdapter = new SpinnerAdapter(getApplicationContext(), temperatureList);
        lTempAdapter = new LowerSpinnerAdapter(getApplicationContext(), temperatureList);
        dataAdapter = new SpinnerAdapter(getApplicationContext(), dataStorageList);
        lDataAdapter = new LowerSpinnerAdapter(getApplicationContext(), dataStorageList);
        powerAdapter = new SpinnerAdapter(getApplicationContext(), powerList);
        lPowerAdapter = new LowerSpinnerAdapter(getApplicationContext(), powerList);
        energyAdapter = new SpinnerAdapter(getApplicationContext(), energyList);
        lEnergyAdapter = new LowerSpinnerAdapter(getApplicationContext(), energyList);
    }

    private void dependentSpinners() {
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (categorySpinner.getSelectedItemPosition()) {
                    case 0:
                        removingWatcher();
                        fromLengthConditions();
                        addingWatcher();
                        break;
                    case 1:
                        removingWatcher();
                        fromAreaConditions();
                        addingWatcher();
                        break;
                    case 2:
                        removingWatcher();
                        fromVolumeConditions();
                        addingWatcher();
                        break;
                    case 3:
                        removingWatcher();
                        fromWeightConditions();
                        addingWatcher();
                        break;
                    case 4:
                        removingWatcher();
                        fromTemperatureConditions();
                        addingWatcher();
                        break;
                    case 5:
                        removingWatcher();
                        fromStorageConditions();
                        addingWatcher();
                        break;
                    case 6:
                        removingWatcher();
                        fromPowerConditions();
                        addingWatcher();
                        break;
                    case 7:
                        removingWatcher();
                        fromEnergyConditions();
                        addingWatcher();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (categorySpinner.getSelectedItemPosition()) {
                    case 0:
                        removingWatcher();
                        fromLengthConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.redGrad));
                        }
                        addingWatcher();
                        break;
                    case 1:
                        removingWatcher();
                        fromAreaConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blueGrad));
                        }
                        addingWatcher();
                        break;
                    case 2:
                        removingWatcher();
                        fromVolumeConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellowGrad));
                        }
                        addingWatcher();
                        break;
                    case 3:
                        removingWatcher();
                        fromWeightConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.greenGrad));
                        }
                        addingWatcher();
                        break;
                    case 4:
                        removingWatcher();
                        fromTemperatureConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sublimeGrad));
                        }
                        addingWatcher();
                        break;
                    case 5:
                        removingWatcher();
                        addingWatcher();
                        break;
                    case 6:
                        removingWatcher();
                        addingWatcher();
                        break;
                    case 7:
                        removingWatcher();
                        addingWatcher();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addingWatcher() {
        fromEdit.addTextChangedListener(textWatcher);
        toEdit.addTextChangedListener(textWatcher);
    }

    private void removingWatcher() {
        fromEdit.removeTextChangedListener(textWatcher);
        toEdit.removeTextChangedListener(textWatcher);
    }

    @SuppressLint("DefaultLocale")
    private void fromLengthConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mmtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mmtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mmtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mmtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mmtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mmtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mmtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mmtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = cmtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cmtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cmtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cmtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cmtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cmtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = cmtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = cmtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = mtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = kmtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kmtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kmtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kmtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kmtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kmtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kmtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kmtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = inchtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = inchtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = inchtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = inchtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = inchtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = inchtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = inchtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = inchtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = foottomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = foottocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = foottom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = foottokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = foottoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = foottofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = foottoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = foottomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = yardtomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = yardtocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = yardtom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = yardtokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = yardtoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = yardtofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = yardtoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = yardtomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = mitomm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mitocm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mitom(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mitokm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mitoinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mitofoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mitoyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mitomi(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toLengthConditions() {
        from = toSpinner.getSelectedItemPosition();
        to = fromSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mmtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mmtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mmtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mmtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mmtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mmtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mmtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mmtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = cmtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cmtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cmtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cmtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cmtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cmtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = cmtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = cmtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = mtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = kmtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kmtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kmtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kmtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kmtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kmtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kmtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kmtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = inchtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = inchtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = inchtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = inchtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = inchtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = inchtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = inchtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = inchtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = foottomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = foottocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = foottom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = foottokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = foottoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = foottofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = foottoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = foottomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = yardtomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = yardtocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = yardtom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = yardtokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = yardtoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = yardtofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = yardtoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = yardtomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = mitomm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mitocm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mitom(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mitokm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mitoinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mitofoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mitoyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = mitomi(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromAreaConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = sqcmTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqcmTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqcmTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqcmTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqcmTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqcmToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqcmTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqcmTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = sqinchTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqinchTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqinchTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqinchTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqinchTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqinchToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqinchTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqinchTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = sqfootTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqfootTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqfootTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqfootTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqfootTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqfootToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqfootTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqfootTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = sqmTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqmTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqmTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqmTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqmTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqmToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqmTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqmTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            break;
                        case 1:
                            res = sqkmTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqkmTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqkmTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqkmTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqkmToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqkmTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqkmTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = acreTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = acreTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = acreTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = acreTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = acreTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = acreToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = acreTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = acreTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = hectareTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hectareTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hectareTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hectareTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hectareTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hectareToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = hectareTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = hectareTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = sqyardTosqcm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqyardTosqinch(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqyardTosqfoot(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqyardTosqm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqyardTosqkm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqyardToacre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqyardTohectare(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqyardTosqyard(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toAreaConditions() {
        from = toSpinner.getSelectedItemPosition();
        to = fromSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = sqcmTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqcmTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqcmTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqcmTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqcmTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqcmToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqcmTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqcmTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = sqinchTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqinchTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqinchTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqinchTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqinchTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqinchToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqinchTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqinchTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = sqfootTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqfootTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqfootTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqfootTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqfootTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqfootToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqfootTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqfootTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = sqmTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqmTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqmTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqmTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqmTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqmToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqmTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqmTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            break;
                        case 1:
                            res = sqkmTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqkmTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqkmTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqkmTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqkmToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqkmTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqkmTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = acreTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = acreTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = acreTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = acreTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = acreTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = acreToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            acreTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = acreTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = hectareTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hectareTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hectareTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hectareTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hectareTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hectareToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = hectareTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = hectareTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = sqyardTosqcm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = sqyardTosqinch(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = sqyardTosqfoot(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = sqyardTosqm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = sqyardTosqkm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = sqyardToacre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = sqyardTohectare(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = sqyardTosqyard(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromVolumeConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = cucmTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cucmTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cucmToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cucmTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cucmTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cucmTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = cumTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cumTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cumToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cumTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cumTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cumTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = mlTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mlTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mlToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mlTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mlTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mlTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = litreTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = litreTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = litreToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = litreTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = litreTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = litreTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = pintTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = pintTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = pintToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = pintTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = pintTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = pintTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = gallonTocucm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = gallonTocum(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = gallonToml(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = gallonTolitre(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = gallonTopint(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = gallonTogallon(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toVolumeConditions() {
        to = fromSpinner.getSelectedItemPosition();
        from = toSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = cucmTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cucmTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cucmToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cucmTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cucmTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cucmTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = cumTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = cumTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = cumToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = cumTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = cumTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = cumTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = mlTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mlTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mlToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mlTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mlTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mlTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = litreTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = litreTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = litreToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = litreTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = litreTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = litreTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = pintTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = pintTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = pintToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = pintTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = pintTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = pintTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = gallonTocucm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = gallonTocum(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = gallonToml(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = gallonTolitre(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = gallonTopint(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = gallonTogallon(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromWeightConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mgTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mgTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mgTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mgToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mgTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mgToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mgTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = gmTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = gmTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = gmTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = gmToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = gmTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = gmToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = gmTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kgTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kgTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kgTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kgToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kgTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kgToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kgTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                case 3:
                    switch (to) {
                        case 0:
                            res = tonTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = tonTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = tonTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = tonToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = tonTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = tonToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = tonTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = carratTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = carratTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = carratTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = carratToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = carratTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = carratToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = carratTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = ounceTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            ounceTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = ounceTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = ounceToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = ounceTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = ounceToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = ounceTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = poundTomg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = poundTogm(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = poundTokg(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = poundToton(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = poundTocarrat(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = poundToounce(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = poundTopound(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toWeightConditions() {
        to = fromSpinner.getSelectedItemPosition();
        from = toSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mgTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mgTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mgTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mgToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mgTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = mgToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mgTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = gmTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = gmTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = gmTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = gmToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = gmTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = gmToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = gmTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kgTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kgTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kgTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kgToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kgTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kgToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kgTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                case 3:
                    switch (to) {
                        case 0:
                            res = tonTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = tonTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = tonTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = tonToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = tonTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = tonToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = tonTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = carratTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = carratTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = carratTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = carratToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = carratTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = carratToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = carratTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = ounceTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            ounceTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = ounceTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = ounceToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = ounceTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = ounceToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = ounceTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = poundTomg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = poundTogm(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = poundTokg(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = poundToton(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = poundTocarrat(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = poundToounce(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = poundTopound(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromTemperatureConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            try {
                                res = kelvinTokelvin(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            try {
                                res = kelTofah(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            try {
                                res = kelTocel(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            try {
                                res = fahTokel(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            try {
                                res = fahTofah(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            try {
                                res = fahTocel(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            try {
                                res = celTokel(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            try {
                                res = celTofah(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            try {
                                res = celTocel(Double.parseDouble(fromEdit.getText().toString().trim()));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toTemperatureConditions() {
        to = fromSpinner.getSelectedItemPosition();
        from = toSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().trim().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            try {
                                res = kelvinTokelvin(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                res = kelTofah(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                res = kelTocel(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            try {
                                res = fahTokel(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                res = fahTofah(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                res = fahTocel(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            try {
                                res = celTokel(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 1:
                            try {
                                res = celTofah(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                res = celTocel(Double.parseDouble(toEdit.getText().toString().trim()));
                                fromEdit.setText(String.format("%.4f", res));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromStorageConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = byteTobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = byteTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = byteTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = byteToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = byteToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = byteToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = byteToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = byteToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = kbTobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kbTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kbTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kbToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kbToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kbToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kbToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kbToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kBtobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kBtokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kBTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kBToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kBToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kBToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kBToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kBToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = Mbtobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MbTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MbTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MbToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MbToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MbToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MbToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = MbToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = MBTobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MBTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MBTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MBToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MBToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MBToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MBToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = MBToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = GbTobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GbTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GbTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GbToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GbToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GbToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GbToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = GbToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = GBTobyte(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GBTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GBTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GBToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GBToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GBToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GBToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = GBToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = TBTobytes(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = TBTokb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = TBTokB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = TBToMb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = TBToMB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = TBToGb(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = TBToGB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = TBToTB(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toStorageConditions() {
        from = toSpinner.getSelectedItemPosition();
        to = fromSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = byteTobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = byteTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = byteTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = byteToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = byteToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = byteToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = byteToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = byteToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = kbTobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kbTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kbTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kbToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kbToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kbToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kbToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kbToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kBtobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kBtokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kBTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kBToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kBToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kBToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kBToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = kBToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = Mbtobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MbTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MbTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MbToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MbToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MbToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MbToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = MbToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = MBTobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MBTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MBTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MBToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MBToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MBToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MBToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = MBToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = GbTobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GbTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GbTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GbToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GbToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GbToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GbToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = GbToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = GBTobyte(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GBTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GBTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GBToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GBToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GBToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GBToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = GBToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 7:
                    switch (to) {
                        case 0:
                            res = TBTobytes(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = TBTokb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = TBTokB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = TBToMb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = TBToMB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = TBToGb(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = TBToGB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 7:
                            res = TBToTB(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromPowerConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = wattTowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = wattTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = wattTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = wattToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = wattToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = wattTohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = hWtowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hWTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hWTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hWToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hWToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hWtohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kWTowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kWTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kWTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kWToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kWToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kWTohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = MWTowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MWTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MWTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MWToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MWToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MWTohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = GWTowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GWTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GWTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GWToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GWToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GWTohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = hpTowatt(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hpTohW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hpTokW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hpToMW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hpToGW(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hpTohp(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void toPowerConditions() {
        to = fromSpinner.getSelectedItemPosition();
        from = toSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = wattTowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = wattTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = wattTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = wattToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = wattToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = wattTohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = hWtowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hWTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hWTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hWToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hWToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hWtohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kWTowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kWTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kWTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kWToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kWToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = kWTohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = MWTowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MWTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MWTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MWToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MWToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = MWTohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = GWTowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GWTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GWTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GWToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GWToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = GWTohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = hpTowatt(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = hpTohW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = hpTokW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = hpToMW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = hpToGW(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = hpTohp(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void fromEnergyConditions() {
        from = fromSpinner.getSelectedItemPosition();
        to = toSpinner.getSelectedItemPosition();
        if (!fromEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mJTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mJToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mJTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mJToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mJToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = JTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = JToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = JTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = JToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = JToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kJTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kJToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kJTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kJToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kJToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = MJTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MJToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MJTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MJToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MJToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;

                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = GJTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GJToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GJTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GJToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GJToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    private void toEnergyConditions() {
        to = fromSpinner.getSelectedItemPosition();
        from = toSpinner.getSelectedItemPosition();
        if (!toEdit.getText().toString().isEmpty()) {
            switch (from) {
                case 0:
                    switch (to) {
                        case 0:
                            res = mJTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = mJToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = mJTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = mJToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = mJToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 1:
                    switch (to) {
                        case 0:
                            res = JTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = JToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = JTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = JToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = JToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 2:
                    switch (to) {
                        case 0:
                            res = kJTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = kJToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = kJTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = kJToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = kJToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 3:
                    switch (to) {
                        case 0:
                            res = MJTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = MJToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = MJTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = MJToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = MJToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;

                    }
                    break;
                case 4:
                    switch (to) {
                        case 0:
                            res = GJTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = GJToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = GJTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = GJToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = GJToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
            }
        }
    }

    private double mmtomm(double num) {
        return num * 1;
    }

    private double mmtocm(double num) {
        return num * 0.1;
    }

    private double mmtoinch(double num) {
        return num * 0.0394;
    }

    private double mmtofoot(double num) {
        return num * 0.00328;
    }

    private double mmtoyard(double num) {
        return num * 0.00109;
    }

    private double mmtom(double num) {
        return num * 0.001;
    }

    private double mmtokm(double num) {
        return num * 0.000001;
    }

    private double mmtomi(double num) {
        return num * 0.000000621;
    }

    private double cmtomm(double num) {
        return num * 10;
    }

    private double cmtocm(double num) {
        return num * 1;
    }

    private double cmtoinch(double num) {
        return num * 0.394;
    }

    private double cmtofoot(double num) {
        return num * 0.0328;
    }

    private double cmtoyard(double num) {
        return num * 0.0109;
    }

    private double cmtom(double num) {
        return num * 0.01;
    }

    private double cmtokm(double num) {
        return num * 0.00001;
    }

    private double cmtomi(double num) {
        return num * 0.00000621;
    }

    private double inchtomm(double num) {
        return num * 25.4;
    }

    private double inchtocm(double num) {
        return num * 2.54;
    }

    private double inchtoinch(double num) {
        return num * 1;
    }

    private double inchtofoot(double num) {
        return num * 0.0833;
    }

    private double inchtoyard(double num) {
        return num * 0.0278;
    }

    private double inchtom(double num) {
        return num * 0.0254;
    }

    private double inchtokm(double num) {
        return num * 0.0000254;
    }

    private double inchtomi(double num) {
        return num * 0.0000158;
    }

    private double foottomm(double num) {
        return num * 304.8;
    }

    private double foottocm(double num) {
        return num * 30.48;
    }

    private double foottoinch(double num) {
        return num * 12;
    }

    private double foottofoot(double num) {
        return num * 1;
    }

    private double foottoyard(double num) {
        return num * 0.333;
    }

    private double foottom(double num) {
        return num * 0.3048;
    }

    private double foottokm(double num) {
        return num * 0.0003048;
    }

    private double foottomi(double num) {
        return num * 0.000189;
    }

    private double yardtomm(double num) {
        return num * 914.4;
    }

    private double yardtocm(double num) {
        return num * 91.44;
    }

    private double yardtoinch(double num) {
        return num * 36;
    }

    private double yardtofoot(double num) {
        return num * 3;
    }

    private double yardtoyard(double num) {
        return num * 1;
    }

    private double yardtom(double num) {
        return num * 0.9144;
    }

    private double yardtokm(double num) {
        return num * 0.0009144;
    }

    private double yardtomi(double num) {
        return num * 0.000568;
    }

    private double mtomm(double num) {
        return num * 1000;
    }

    private double mtocm(double num) {
        return num * 100;
    }

    private double mtoinch(double num) {
        return num * 39.370;
    }

    private double mtofoot(double num) {
        return num * 3.281;
    }

    private double mtoyard(double num) {
        return num * 1.0936;
    }

    private double mtom(double num) {
        return num * 1;
    }

    private double mtokm(double num) {
        return num * 0.001;
    }

    private double mtomi(double num) {
        return num * 0.000621;
    }

    private double kmtomm(double num) {
        return num * 1000000;
    }

    private double kmtocm(double num) {
        return num * 100000;
    }

    private double kmtoinch(double num) {
        return num * 39370.0787;
    }

    private double kmtofoot(double num) {
        return num * 3280.840;
    }

    private double kmtoyard(double num) {
        return num * 1093.613;
    }

    private double kmtom(double num) {
        return num * 1000;
    }

    private double kmtokm(double num) {
        return num * 1;
    }

    private double kmtomi(double num) {
        return num * 0.621;
    }

    private double mitomm(double num) {
        return num * 1609350;
    }

    private double mitocm(double num) {
        return num * 160935;
    }

    private double mitoinch(double num) {
        return num * 63360.236;
    }

    private double mitofoot(double num) {
        return num * 5280.0197;
    }

    private double mitoyard(double num) {
        return num * 1760.00656;
    }

    private double mitom(double num) {
        return num * 1609.35;
    }

    private double mitokm(double num) {
        return num * 1.60935;
    }

    private double mitomi(double num) {
        return num * 1;
    }

    private double sqcmTosqcm(double num) {
        return num * 1;
    }

    private double sqcmTosqinch(double num) {
        return num * 0.155;
    }

    private double sqcmTosqfoot(double num) {
        return num * 0.00108;
    }

    private double sqcmTosqm(double num) {
        return num * 0.0001;
    }

    private double sqcmToacre(double num) {
        return num * 2.47E-8;
    }

    private double sqcmTohectare(double num) {
        return num * 1.E-8;
    }

    private double sqcmTosqkm(double num) {
        return num * 1.E-10;
    }

    private double sqcmTosqyard(double num) {
        return num * 0.000119599;
    }

    private double sqinchTosqcm(double num) {
        return num * 6.4516;
    }

    private double sqinchTosqinch(double num) {
        return num * 1;
    }

    private double sqinchTosqfoot(double num) {
        return num * 0.00694;
    }

    private double sqinchTosqm(double num) {
        return num * 0.00064516;
    }

    private double sqinchToacre(double num) {
        return num * 1.594E-7;
    }

    private double sqinchTohectare(double num) {
        return num * 6.4516E-8;
    }

    private double sqinchTosqkm(double num) {
        return num * 6.4516E-10;
    }

    private double sqinchTosqyard(double num) {
        return num * 0.0007716049;
    }

    private double sqfootTosqcm(double num) {
        return num * 929.0304;
    }

    private double sqfootTosqinch(double num) {
        return num * 144;
    }

    private double sqfootTosqfoot(double num) {
        return num * 1;
    }

    private double sqfootTosqm(double num) {
        return num * 0.0929;
    }

    private double sqfootToacre(double num) {
        return num * 0.0000230;
    }

    private double sqfootTohectare(double num) {
        return num * 0.00000929;
    }

    private double sqfootTosqkm(double num) {
        return num * 9.290E-8;
    }

    private double sqfootTosqyard(double num) {
        return num * 0.1111111111;
    }

    private double sqmTosqcm(double num) {
        return num * 10000;
    }

    private double sqmTosqinch(double num) {
        return num * 1550.0031;
    }

    private double sqmTosqfoot(double num) {
        return num * 10.764;
    }

    private double sqmTosqm(double num) {
        return num * 1;
    }

    private double sqmToacre(double num) {
        return num * 0.000247;
    }

    private double sqmTohectare(double num) {
        return num * 0.0001;
    }

    private double sqmTosqkm(double num) {
        return num * 0.000001;
    }

    private double sqmTosqyard(double num) {
        return num * 1.1959900463;
    }

    private double acreTosqcm(double num) {
        return num * 40468564.224;
    }

    private double acreTosqinch(double num) {
        return num * 6272640;
    }

    private double acreTosqfoot(double num) {
        return num * 43560;
    }

    private double acreTosqm(double num) {
        return num * 4046.856;
    }

    private double acreToacre(double num) {
        return num * 1;
    }

    private double acreTohectare(double num) {
        return num * 0.405;
    }

    private double acreTosqkm(double num) {
        return num * 0.00405;
    }

    private double acreTosqyard(double num) {
        return num * 4840;
    }

    private double hectareTosqcm(double num) {
        return num * 100000000;
    }

    private double hectareTosqinch(double num) {
        return num * 15500031;
    }

    private double hectareTosqfoot(double num) {
        return num * 107639.10417;
    }

    private double hectareTosqm(double num) {
        return num * 10000;
    }

    private double hectareToacre(double num) {
        return num * 2.471;
    }

    private double hectareTohectare(double num) {
        return num * 1;
    }

    private double hectareTosqkm(double num) {
        return num * 0.01;
    }

    private double hectareTosqyard(double num) {
        return num * 11959.900463;
    }

    private double sqkmTosqinch(double num) {
        return num * 1550003100;
    }

    private double sqkmTosqfoot(double num) {
        return num * 10763910.417;
    }

    private double sqkmTosqm(double num) {
        return num * 1000000;
    }

    private double sqkmToacre(double num) {
        return num * 247.105;
    }

    private double sqkmTohectare(double num) {
        return num * 100;
    }

    private double sqkmTosqkm(double num) {
        return num * 1;
    }

    private double sqkmTosqyard(double num) {
        return num * 1195990.0463;
    }

    private double sqyardTosqcm(double num) {
        return num * 8361.2736;
    }

    private double sqyardTosqinch(double num) {
        return num * 1296;
    }

    private double sqyardTosqfoot(double num) {
        return num * 9;
    }

    private double sqyardTosqm(double num) {
        return num * 0.83612736;
    }

    private double sqyardToacre(double num) {
        return num * 0.0002066116;
    }

    private double sqyardTohectare(double num) {
        return num * 0.0000836127;
    }

    private double sqyardTosqkm(double num) {
        return num * 8.3612736E-7;
    }

    private double sqyardTosqyard(double num) {
        return num * 1;
    }

    private double cucmTocucm(double num) {
        return num * 1;
    }

    private double cucmToml(double num) {
        return num * 1;
    }

    private double cucmTolitre(double num) {
        return num * 0.001;
    }

    private double cucmTopint(double num) {
        return num * 0.00211;
    }

    private double cucmTogallon(double num) {
        return num * 0.000264;
    }

    private double cucmTocum(double num) {
        return num * 0.000001;
    }

    private double mlTocucm(double num) {
        return num * 1;
    }

    private double mlToml(double num) {
        return num * 1;
    }

    private double mlTolitre(double num) {
        return num * 0.001;
    }

    private double mlTopint(double num) {
        return num * 0.00211;
    }

    private double mlTogallon(double num) {
        return num * 0.000264;
    }

    private double mlTocum(double num) {
        return num * 0.000001;
    }

    private double litreTocucm(double num) {
        return num * 1000;
    }

    private double litreToml(double num) {
        return num * 1000;
    }

    private double litreTolitre(double num) {
        return num * 1;
    }

    private double litreTopint(double num) {
        return num * 2.113;
    }

    private double litreTogallon(double num) {
        return num * 0.264;
    }

    private double litreTocum(double num) {
        return num * 0.001;
    }

    private double pintTocucm(double num) {
        return num * 473.17625;
    }

    private double pintToml(double num) {
        return num * 473.17625;
    }

    private double pintTolitre(double num) {
        return num * 0.473;
    }

    private double pintTopint(double num) {
        return num * 1;
    }

    private double pintTogallon(double num) {
        return num * 0.125;
    }

    private double pintTocum(double num) {
        return num * 0.000473;
    }

    private double gallonTocucm(double num) {
        return num * 3785.41;
    }

    private double gallonToml(double num) {
        return num * 3785.41;
    }

    private double gallonTolitre(double num) {
        return num * 3.785;
    }

    private double gallonTopint(double num) {
        return num * 8;
    }

    private double gallonTogallon(double num) {
        return num * 1;
    }

    private double gallonTocum(double num) {
        return num * 0.00379;
    }

    private double cumTocucm(double num) {
        return num * 1000000;
    }

    private double cumToml(double num) {
        return num * 1000000;
    }

    private double cumTolitre(double num) {
        return num * 1000;
    }

    private double cumTopint(double num) {
        return num * 2113.377;
    }

    private double cumTogallon(double num) {
        return num * 264.172;
    }

    private double cumTocum(double num) {
        return num * 1;
    }

    private double mgTomg(double num) {
        return num * 1;
    }

    private double mgTogm(double num) {
        return num * 0.001;
    }

    private double mgTocarrat(double num) {
        return num * 0.005;
    }

    private double mgToounce(double num) {
        return num * 0.0000353;
    }

    private double mgTopound(double num) {
        return num * 0.0000022046;
    }

    private double mgTokg(double num) {
        return num * 0.000001;
    }

    private double mgToton(double num) {
        return num * 1E-9;
    }

    private double gmTomg(double num) {
        return num * 1000;
    }

    private double gmTogm(double num) {
        return num * 1;
    }

    private double gmTocarrat(double num) {
        return num * 5;
    }

    private double gmToounce(double num) {
        return num * 0.0353;
    }

    private double gmTopound(double num) {
        return num * 0.00220;
    }

    private double gmTokg(double num) {
        return num * 0.001;
    }

    private double gmToton(double num) {
        return num * 0.000001;
    }

    private double carratTomg(double num) {
        return num * 200;
    }

    private double carratTogm(double num) {
        return num * 0.2;
    }

    private double carratTocarrat(double num) {
        return num * 1;
    }

    private double carratToounce(double num) {
        return num * 0.00705;
    }

    private double carratTopound(double num) {
        return num * 0.000441;
    }

    private double carratTokg(double num) {
        return num * 0.0002;
    }

    private double carratToton(double num) {
        return num * 2.E-7;
    }

    private double ounceTomg(double num) {
        return num * 28349.5;
    }

    private double ounceTogm(double num) {
        return num * 28.3495;
    }

    private double ounceTocarrat(double num) {
        return num * 141.7475;
    }

    private double ounceToounce(double num) {
        return num * 1;
    }

    private double ounceTopound(double num) {
        return num * 0.0625;
    }

    private double ounceTokg(double num) {
        return num * 0.0283;
    }

    private double ounceToton(double num) {
        return num * 0.0000283;
    }

    private double poundTomg(double num) {
        return num * 453592;
    }

    private double poundTogm(double num) {
        return num * 453.592;
    }

    private double poundTocarrat(double num) {
        return num * 2267.96;
    }

    private double poundToounce(double num) {
        return num * 16;
    }

    private double poundTopound(double num) {
        return num * 1;
    }

    private double poundTokg(double num) {
        return num * 0.453592;
    }

    private double poundToton(double num) {
        return num * 0.000454;
    }

    private double kgTomg(double num) {
        return num * 1000000;
    }

    private double kgTogm(double num) {
        return num * 1000;
    }

    private double kgTocarrat(double num) {
        return num * 5000;
    }

    private double kgToounce(double num) {
        return num * 35.274;
    }

    private double kgTopound(double num) {
        return num * 2.205d;
    }

    private double kgTokg(double num) {
        return num * 1d;
    }

    private double kgToton(double num) {
        return num * 0.001d;
    }

    private double tonTomg(double num) {
        return num * 1000000000d;
    }

    private double tonTogm(double num) {
        return num * 1000000d;
    }

    private double tonTocarrat(double num) {
        return num * 5000000d;
    }

    private double tonToounce(double num) {
        return num * 35273.991d;
    }

    private double tonTopound(double num) {
        return num * 2204.624d;
    }

    private double tonTokg(double num) {
        return num * 1000d;
    }

    private double tonToton(double num) {
        return num * 1;
    }

    private double kelvinTokelvin(double num) {
        return num;
    }

    private double kelTofah(double num) {
        return ((1.8d * (num - 273)) + 32);
    }

    private double kelTocel(double num) {
        return num - 273;
    }

    private double fahTokel(double num) {
        return (((5d / 9) * (num - 32)) + 273);
    }

    private double fahTofah(double num) {
        return num;
    }

    private double fahTocel(double num) {
        return (num - 32) * (5d / 9);
    }

    private double celTokel(double num) {
        return num + 273;
    }

    private double celTofah(double num) {
        return (1.8d * (num + 32));
    }

    private double celTocel(double num) {
        return num;
    }

    private double byteTobyte(double num) {
        return num;
    }

    private double byteTokb(double num) {
        return num * 0.0078125d;
    }

    private double byteTokB(double num) {
        return num * 0.0009765625d;
    }

    private double byteToMb(double num) {
        return num * 0.0000076294d;
    }

    private double byteToMB(double num) {
        return num * 9.536743164E-7d;
    }

    private double byteToGb(double num) {
        return num * 7.450580596E-9d;
    }

    private double byteToGB(double num) {
        return num * 9.313225746E-10d;
    }

    private double byteToTB(double num) {
        return num * 9.094947017E-13d;
    }

    private double kbTobyte(double num) {
        return num * 128d;
    }

    private double kbTokb(double num) {
        return num;
    }

    private double kbTokB(double num) {
        return num * 0.125d;
    }

    private double kbToMb(double num) {
        return num * 0.0009765625d;
    }

    private double kbToMB(double num) {
        return num * 0.0001220703d;
    }

    private double kbToGb(double num) {
        return num * 9.536743164E-7d;
    }

    private double kbToGB(double num) {
        return num * 1.192092895E-7d;
    }

    private double kbToTB(double num) {
        return num * 1.164153218E-10d;
    }

    private double kBtobyte(double num) {
        return num * 1024d;
    }

    private double kBtokb(double num) {
        return num * 8d;
    }

    private double kBTokB(double num) {
        return num;
    }

    private double kBToMb(double num) {
        return num * 0.0078125d;
    }

    private double kBToMB(double num) {
        return num * 0.0009765625d;
    }

    private double kBToGb(double num) {
        return num * 0.0000076294d;
    }

    private double kBToGB(double num) {
        return num * 9.536743164E-7d;
    }

    private double kBToTB(double num) {
        return num * 9.313225746E-10d;
    }

    private double Mbtobyte(double num) {
        return num * 131072d;
    }

    private double MbTokb(double num) {
        return num * 1024d;
    }

    private double MbTokB(double num) {
        return num * 128d;
    }

    private double MbToMb(double num) {
        return num;
    }

    private double MbToMB(double num) {
        return num * 0.125d;
    }

    private double MbToGb(double num) {
        return num * 0.0009765625d;
    }

    private double MbToGB(double num) {
        return num * 0.0001220703d;
    }

    private double MbToTB(double num) {
        return num * 1.192092895E-7d;
    }

    private double MBTobyte(double num) {
        return num * 1048576d;
    }

    private double MBTokb(double num) {
        return num * 8192d;
    }

    private double MBTokB(double num) {
        return num * 1024d;
    }

    private double MBToMb(double num) {
        return num * 8d;
    }

    private double MBToMB(double num) {
        return num;
    }

    private double MBToGb(double num) {
        return num * 0.0078125d;
    }

    private double MBToGB(double num) {
        return num * 0.0009765625d;
    }

    private double MBToTB(double num) {
        return num * 9.536743164E-7d;
    }

    private double GbTobyte(double num) {
        return num * 134217728d;
    }

    private double GbTokb(double num) {
        return num * 1048576d;
    }

    private double GbTokB(double num) {
        return num * 131072d;
    }

    private double GbToMb(double num) {
        return num * 1024d;
    }

    private double GbToMB(double num) {
        return num * 128d;
    }

    private double GbToGb(double num) {
        return num;
    }

    private double GbToGB(double num) {
        return num * 0.125d;
    }

    private double GbToTB(double num) {
        return num * 0.0001220703d;
    }

    private double GBTobyte(double num) {
        return num * 1073741824d;
    }

    private double GBTokb(double num) {
        return num * 8388608d;
    }

    private double GBTokB(double num) {
        return num * 1048576d;
    }

    private double GBToMb(double num) {
        return num * 8192d;
    }

    private double GBToMB(double num) {
        return num * 1024d;
    }

    private double GBToGb(double num) {
        return num * 8d;
    }

    private double GBToGB(double num) {
        return num * 1d;
    }

    private double GBToTB(double num) {
        return num * 0.0009765625d;
    }

    private double TBTobytes(double num) {
        return num * 1099511627776d;
    }

    private double TBTokb(double num) {
        return num * 8589934592d;
    }

    private double TBTokB(double num) {
        return num * 1073741824d;
    }

    private double TBToMb(double num) {
        return num * 8388608d;
    }

    private double TBToMB(double num) {
        return num * 1048576d;
    }

    private double TBToGb(double num) {
        return num * 8192d;
    }

    private double TBToGB(double num) {
        return num * 1024d;
    }

    private double TBToTB(double num) {
        return num;
    }

    private double mJTomJ(double num) {
        return num;
    }

    private double mJToJ(double num) {
        return num * 0.001d;
    }

    private double mJTokJ(double num) {
        return num * 0.000001d;
    }

    private double mJToMJ(double num) {
        return num * 1.E-9d;
    }

    private double mJToGJ(double num) {
        return num * 1.E-12d;
    }

    private double JTomJ(double num) {
        return num * 1000d;
    }

    private double JToJ(double num) {
        return num;
    }

    private double JTokJ(double num) {
        return num * 0.001d;
    }

    private double JToMJ(double num) {
        return num * 0.000001d;
    }

    private double JToGJ(double num) {
        return num * 1.E-10d;
    }

    private double kJTomJ(double num) {
        return num * 1000000d;
    }

    private double kJToJ(double num) {
        return num * 1000d;
    }

    private double kJTokJ(double num) {
        return num;
    }

    private double kJToMJ(double num) {
        return num * 0.001d;
    }

    private double kJToGJ(double num) {
        return num * 0.000001d;
    }

    private double MJTomJ(double num) {
        return num * 1000000000d;
    }

    private double MJToJ(double num) {
        return num * 1000000d;
    }

    private double MJTokJ(double num) {
        return num * 1000d;
    }

    private double MJToMJ(double num) {
        return num;
    }

    private double MJToGJ(double num) {
        return num * 0.001d;
    }

    private double GJTomJ(double num) {
        return num * 1000000000000d;
    }

    private double GJToJ(double num) {
        return num * 1000000000d;
    }

    private double GJTokJ(double num) {
        return num * 1000000d;
    }

    private double GJToMJ(double num) {
        return num * 1000d;
    }

    private double GJToGJ(double num) {
        return num;
    }

    private double wattTowatt(double num) {
        return num;
    }

    private double wattTohW(double num) {
        return num * 0.01d;
    }

    private double wattTokW(double num) {
        return num * 0.001d;
    }

    private double wattToMW(double num) {
        return num * 0.000001d;
    }

    private double wattToGW(double num) {
        return num * 1.E-9d;
    }

    private double wattTohp(double num) {
        return num * 745.69987158d;
    }

    private double hWtowatt(double num) {
        return num * 100d;
    }

    private double hWTohW(double num) {
        return num;
    }

    private double hWTokW(double num) {
        return num * 0.1d;
    }

    private double hWToMW(double num) {
        return num * 0.0001d;
    }

    private double hWToGW(double num) {
        return num * 1.E-7d;
    }

    private double hWtohp(double num) {
        return num * 0.134102209d;
    }

    private double kWTowatt(double num) {
        return num * 1000d;
    }

    private double kWTohW(double num) {
        return num * 10d;
    }

    private double kWTokW(double num) {
        return num;
    }

    private double kWToMW(double num) {
        return num * 0.001d;
    }

    private double kWToGW(double num) {
        return num * 0.000001d;
    }

    private double kWTohp(double num) {
        return num * 1.3410220896d;
    }

    private double MWTowatt(double num) {
        return num * 1000000d;
    }

    private double MWTohW(double num) {
        return num * 10000d;
    }

    private double MWTokW(double num) {
        return num * 1000d;
    }

    private double MWToMW(double num) {
        return num;
    }

    private double MWToGW(double num) {
        return num * 0.001d;
    }

    private double MWTohp(double num) {
        return num * 1341.0220896d;
    }

    private double GWTowatt(double num) {
        return num * 1000000000d;
    }

    private double GWTohW(double num) {
        return num * 10000000d;
    }

    private double GWTokW(double num) {
        return num * 1000000d;
    }

    private double GWToMW(double num) {
        return num * 1000d;
    }

    private double GWToGW(double num) {
        return num;
    }

    private double GWTohp(double num) {
        return num * 1341022.0896d;
    }

    private double hpTowatt(double num) {
        return num * 745.69987158d;
    }

    private double hpTohW(double num) {
        return num * 7.4569987158d;
    }

    private double hpTokW(double num) {
        return num * 0.7456998716d;
    }

    private double hpToMW(double num) {
        return num * 0.0007456999d;
    }

    private double hpToGW(double num) {
        return num * 7.456998715E-7d;
    }

    private double hpTohp(double num) {
        return num;
    }
}
