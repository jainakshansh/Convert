package com.madhouseapps.convert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
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
import android.widget.ListView;
import android.widget.TextView;

import com.madhouseapps.convert.Adapters.CategoryAdapter;
import com.madhouseapps.convert.Adapters.ConversionAdapter;
import com.madhouseapps.convert.Adapters.LowerSpinnerAdapter;
import com.madhouseapps.convert.Adapters.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConversionsActivity extends AppCompatActivity {

    //View initialisation.
    private ConstraintLayout constraintLayout;
    private AppCompatSpinner categorySpinner, fromSpinner, toSpinner;
    private AppCompatEditText fromEdit, toEdit;
    private ImageView share, rate;
    private Button swapButton, seeAll;
    private LinearLayout upperParent, lowerParent, seeAllParent;
    private boolean bottomEnabled = false;

    private ListView listView;
    private List<Conversion> conversionList;
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
    private ConversionAdapter conversionAdapter;

    private int from, to;
    private double res = 0.0;

    private SharedPreferences sharedPreferences;
    int appOpened = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversions);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        appOpened = sharedPreferences.getInt("appOpened", 0);
        appOpened++;
        editor.putInt("appOpened", appOpened);
        editor.apply();

        initViews();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_r);
                        categoryAdapter.notifyDataSetChanged();

                        allLengthConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_r);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_r);
                        }

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
                        rate.setImageResource(R.drawable.ic_star_r);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 1:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_b);
                        categoryAdapter.notifyDataSetChanged();

                        allAreaConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_b);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_b);
                        }

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
                        rate.setImageResource(R.drawable.ic_star_b);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 2:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_y);
                        categoryAdapter.notifyDataSetChanged();

                        allVolumeCoversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_y);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_y);
                        }

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
                        rate.setImageResource(R.drawable.ic_star_y);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 3:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_g);
                        categoryAdapter.notifyDataSetChanged();

                        allWeightConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_g);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_g);
                        }

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
                        rate.setImageResource(R.drawable.ic_star_g);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 4:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_py);
                        categoryAdapter.notifyDataSetChanged();

                        allTemperatureConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_sub);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_sub);
                        }

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
                        rate.setImageResource(R.drawable.ic_star_py);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 5:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_ds);
                        categoryAdapter.notifyDataSetChanged();

                        allStorageConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_ds);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_ds);
                        }

                        //Setting up spinner according to category.
                        dataAdapter.setDropDownViewResource(R.layout.item_dd_ds);
                        fromSpinner.setAdapter(dataAdapter);
                        //Setting up the lower spinner according to the category.
                        lDataAdapter.setDropDownViewResource(R.layout.item_dd_ds);
                        toSpinner.setAdapter(lDataAdapter);
                        fromSpinner.setSelection(2);
                        toSpinner.setSelection(4);

                        upperParent.setBackgroundResource(R.drawable.deepspacegradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_ds);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.deepspace));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.deepspace));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.deepspace));
                        share.setImageResource(R.drawable.ic_share_ds);
                        rate.setImageResource(R.drawable.ic_star_ds);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 6:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_hs);
                        categoryAdapter.notifyDataSetChanged();

                        allPowerConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_hs);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_hs);
                        }

                        //Setting up spinner according to category.
                        powerAdapter.setDropDownViewResource(R.layout.item_dd_hs);
                        fromSpinner.setAdapter(powerAdapter);
                        //Setting up the lower spinner according to the category.
                        lPowerAdapter.setDropDownViewResource(R.layout.item_dd_hs);
                        toSpinner.setAdapter(lPowerAdapter);
                        fromSpinner.setSelection(2);
                        toSpinner.setSelection(5);

                        upperParent.setBackgroundResource(R.drawable.hersheysgradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_hs);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hersheys));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.hersheys));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.hersheys));
                        share.setImageResource(R.drawable.ic_share_hs);
                        rate.setImageResource(R.drawable.ic_star_hs);

                        fromEdit.setText(null);
                        toEdit.setText(null);
                        break;
                    case 7:
                        categoryAdapter.setDropDownViewResource(R.layout.cat_dd_sm);
                        categoryAdapter.notifyDataSetChanged();

                        allEnergyConversions();
                        if (!bottomEnabled) {
                            seeAll.setBackgroundResource(R.drawable.ic_list_sm);
                        } else {
                            seeAll.setBackgroundResource(R.drawable.ic_single_sm);
                        }

                        //Setting up spinner according to category.
                        energyAdapter.setDropDownViewResource(R.layout.item_dd_sm);
                        fromSpinner.setAdapter(energyAdapter);
                        //Setting up the lower spinner according to the category.
                        lEnergyAdapter.setDropDownViewResource(R.layout.item_dd_sm);
                        toSpinner.setAdapter(lEnergyAdapter);
                        fromSpinner.setSelection(1);
                        toSpinner.setSelection(2);

                        upperParent.setBackgroundResource(R.drawable.sweetmorninggradient);
                        swapButton.setBackgroundResource(R.drawable.ic_swap_vert_sm);
                        toEdit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sweetmorning));
                        toEdit.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.sweetmorning));
                        toSpinner.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.sweetmorning));
                        share.setImageResource(R.drawable.ic_share_sm);
                        rate.setImageResource(R.drawable.ic_star_sm);

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
                            allLengthConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 1:
                            fromAreaConditions();
                            allAreaConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            fromVolumeConditions();
                            allVolumeCoversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 3:
                            fromWeightConditions();
                            allWeightConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 4:
                            fromTemperatureConditions();
                            allTemperatureConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 5:
                            fromStorageConditions();
                            allStorageConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 6:
                            fromPowerConditions();
                            allPowerConversions();
                            conversionAdapter.notifyDataSetChanged();
                            break;
                        case 7:
                            fromEnergyConditions();
                            allEnergyConversions();
                            conversionAdapter.notifyDataSetChanged();
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
                if (fromEdit.getText().length() > 7) {
                    fromEdit.setTextSize(24f);
                } else {
                    fromEdit.setTextSize(35f);
                }
                if (toEdit.getText().length() > 7) {
                    toEdit.setTextSize(24f);
                } else {
                    toEdit.setTextSize(35f);
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

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bottomEnabled) {
                    //Multi Mode View.
                    bottomEnabled = true;

                    switch (categorySpinner.getSelectedItemPosition()) {
                        case 0:
                            seeAll.setBackgroundResource(R.drawable.ic_single_r);
                            break;
                        case 1:
                            seeAll.setBackgroundResource(R.drawable.ic_single_b);
                            break;
                        case 2:
                            seeAll.setBackgroundResource(R.drawable.ic_single_y);
                            break;
                        case 3:
                            seeAll.setBackgroundResource(R.drawable.ic_single_g);
                            break;
                        case 4:
                            seeAll.setBackgroundResource(R.drawable.ic_single_sub);
                            break;
                        case 5:
                            seeAll.setBackgroundResource(R.drawable.ic_single_ds);
                            break;
                        case 6:
                            seeAll.setBackgroundResource(R.drawable.ic_single_hs);
                            break;
                        case 7:
                            seeAll.setBackgroundResource(R.drawable.ic_single_sm);
                            break;
                    }

                    ConstraintSet set = new ConstraintSet();
                    set.clone(constraintLayout);
                    set.connect(upperParent.getId(), ConstraintSet.BOTTOM, seeAllParent.getId(), ConstraintSet.TOP, 0);
                    set.applyTo(constraintLayout);

                    lowerParent.setVisibility(View.GONE);
                    swapButton.setVisibility(View.GONE);
                    seeAllParent.setVisibility(View.VISIBLE);
                } else {
                    //Single Mode View.
                    bottomEnabled = false;

                    switch (categorySpinner.getSelectedItemPosition()) {
                        case 0:
                            seeAll.setBackgroundResource(R.drawable.ic_list_r);
                            break;
                        case 1:
                            seeAll.setBackgroundResource(R.drawable.ic_list_b);
                            break;
                        case 2:
                            seeAll.setBackgroundResource(R.drawable.ic_list_y);
                            break;
                        case 3:
                            seeAll.setBackgroundResource(R.drawable.ic_list_g);
                            break;
                        case 4:
                            seeAll.setBackgroundResource(R.drawable.ic_list_sub);
                            break;
                        case 5:
                            seeAll.setBackgroundResource(R.drawable.ic_list_ds);
                            break;
                        case 6:
                            seeAll.setBackgroundResource(R.drawable.ic_list_hs);
                            break;
                        case 7:
                            seeAll.setBackgroundResource(R.drawable.ic_list_sm);
                            break;
                    }

                    ConstraintSet set = new ConstraintSet();
                    set.clone(constraintLayout);
                    set.connect(upperParent.getId(), ConstraintSet.BOTTOM, lowerParent.getId(), ConstraintSet.TOP, 0);
                    set.applyTo(constraintLayout);

                    lowerParent.setVisibility(View.VISIBLE);
                    swapButton.setVisibility(View.VISIBLE);
                    seeAllParent.setVisibility(View.GONE);
                }
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.madhouseapps.convert")));
            }
        });
    }

    private void initViews() {
        listView = findViewById(R.id.list_view_all);
        conversionList = new ArrayList<>();

        solomon = Typeface.createFromAsset(getAssets(), "fonts/Solomon.ttf");
        constraintLayout = findViewById(R.id.conversion_constraint_parent);
        categorySpinner = findViewById(R.id.category_spinner);
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        fromEdit = findViewById(R.id.from_edit_text);
        fromEdit.setTypeface(solomon);
        toEdit = findViewById(R.id.to_edit_text);
        toEdit.setTypeface(solomon);
        share = findViewById(R.id.share_app);
        rate = findViewById(R.id.rate_app);
        swapButton = findViewById(R.id.swap_button);
        seeAll = findViewById(R.id.see_all_convs);
        upperParent = findViewById(R.id.upper_parent);
        lowerParent = findViewById(R.id.lower_parent);
        seeAllParent = findViewById(R.id.see_all_parent);

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
        weightList.add("gm");
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
        energyList.add("watt-hr");
        energyList.add("BTU");

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
                        allLengthConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 1:
                        removingWatcher();
                        fromAreaConditions();
                        allAreaConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 2:
                        removingWatcher();
                        fromVolumeConditions();
                        allVolumeCoversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 3:
                        removingWatcher();
                        fromWeightConditions();
                        allWeightConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 4:
                        removingWatcher();
                        fromTemperatureConditions();
                        allTemperatureConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 5:
                        removingWatcher();
                        fromStorageConditions();
                        allStorageConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 6:
                        removingWatcher();
                        fromPowerConditions();
                        allPowerConversions();
                        conversionAdapter.notifyDataSetChanged();
                        addingWatcher();
                        break;
                    case 7:
                        removingWatcher();
                        fromEnergyConditions();
                        allEnergyConversions();
                        conversionAdapter.notifyDataSetChanged();
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
                        fromStorageConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.deepspace));
                        }
                        addingWatcher();
                        break;
                    case 6:
                        removingWatcher();
                        fromPowerConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.hersheys));
                        }
                        addingWatcher();
                        break;
                    case 7:
                        removingWatcher();
                        fromEnergyConditions();
                        for (int i = 0; i < toSpinner.getChildCount(); i++) {
                            ((TextView) parent.getChildAt(i)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.sweetmorning));
                        }
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
    private void allLengthConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("mm", String.format("%.4f", mmtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", mmtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", mmtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", mmtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", mmtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", mmtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", mmtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", mmtomi(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("mm", String.format("%.4f", cmtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", cmtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", cmtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", cmtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", cmtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", cmtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", cmtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", cmtomi(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("mm", String.format("%.4f", mtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", mtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", mtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", mtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", mtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", mtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", mtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", mtomi(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("mm", String.format("%.4f", kmtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", kmtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", kmtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", kmtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", kmtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", kmtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", kmtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", kmtomi(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("mm", String.format("%.4f", inchtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", inchtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", inchtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", inchtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", inchtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", inchtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", inchtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", inchtomi(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("mm", String.format("%.4f", foottomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", foottocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", foottom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", foottokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", foottoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", foottofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", foottoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", foottomi(Double.parseDouble(value)))));
                    break;
                case 6:
                    conversionList.add(new Conversion("mm", String.format("%.4f", yardtomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", yardtocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", yardtom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", yardtokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", yardtoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", yardtofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", yardtoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", yardtomi(Double.parseDouble(value)))));
                    break;
                case 7:
                    conversionList.add(new Conversion("mm", String.format("%.4f", mitomm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cm", String.format("%.4f", mitocm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("m", String.format("%.4f", mitom(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("km", String.format("%.4f", mitokm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("inch", String.format("%.4f", mitoinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("foot", String.format("%.4f", mitofoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("yard", String.format("%.4f", mitoyard(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("mile", String.format("%.4f", mitomi(Double.parseDouble(value)))));
                    break;
            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 0);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allAreaConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", sqcmTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqcmTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqcmTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqcmTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqcmTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqcmToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqcmTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqcmTosqyard(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", sqinchTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqinchTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqinchTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqinchTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqinchTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqinchToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqinchTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqinchTosqyard(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", sqfootTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqfootTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqfootTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqfootTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqfootTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqfootToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqfootTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqfootTosqyard(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", sqmTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqmTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqmTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqmTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqmTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqmToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqmTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqmTosqyard(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", "0.0000")));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqkmTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqkmTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqkmTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqkmTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqkmToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqkmTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqkmTosqyard(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", acreTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", acreTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", acreTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", acreTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", acreTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", acreToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", acreTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", acreTosqyard(Double.parseDouble(value)))));
                    break;
                case 6:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", hectareTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", hectareTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", hectareTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", hectareTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", hectareTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", hectareToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", hectareTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", hectareTosqyard(Double.parseDouble(value)))));
                    break;
                case 7:
                    conversionList.add(new Conversion("sq. cm", String.format("%.4f", sqyardTosqcm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. inch", String.format("%.4f", sqyardTosqinch(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. foot", String.format("%.4f", sqyardTosqfoot(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. m", String.format("%.4f", sqyardTosqm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. km", String.format("%.4f", sqyardTosqkm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("acre", String.format("%.4f", sqyardToacre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hectare", String.format("%.4f", sqyardTohectare(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("sq. yard", String.format("%.4f", sqyardTosqyard(Double.parseDouble(value)))));
                    break;

            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 1);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allVolumeCoversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();
        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", cucmTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", cucmTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", cucmToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", cucmTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", cucmTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", cucmTogallon(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", cumTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", cumTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", cumToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", cumTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", cumTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", cumTogallon(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", mlTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", mlTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", mlToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", mlTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", mlTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", mlTogallon(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", litreTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", litreTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", litreToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", litreTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", litreTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", litreTogallon(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", pintTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", pintTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", pintToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", pintTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", pintTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", pintTogallon(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("cu. cm", String.format("%.4f", gallonTocucm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("cu. m", String.format("%.4f", gallonTocum(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ml", String.format("%.4f", gallonToml(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("litre", String.format("%.4f", gallonTolitre(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Pint", String.format("%.4f", gallonTopint(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("US Gallon", String.format("%.4f", gallonTogallon(Double.parseDouble(value)))));
                    break;

            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 2);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allWeightConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("mg", String.format("%.4f", mgTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", mgTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", mgTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", mgToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", mgTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", mgToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", mgTopound(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("mg", String.format("%.4f", gmTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", gmTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", gmTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", gmToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", gmTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", gmToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", gmTopound(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("mg", String.format("%.4f", kgTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", kgTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", kgTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", kgToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", kgTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", kgToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", kgTopound(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("mg", String.format("%.4f", tonTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", tonTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", tonTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", tonToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", tonTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", tonToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", tonTopound(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("mg", String.format("%.4f", carratTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", carratTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", carratTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", carratToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", carratTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", carratToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", carratTopound(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("mg", String.format("%.4f", ounceTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", ounceTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", ounceTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", ounceToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", ounceTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", ounceToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", ounceTopound(Double.parseDouble(value)))));
                    break;
                case 6:
                    conversionList.add(new Conversion("mg", String.format("%.4f", poundTomg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("gm", String.format("%.4f", poundTogm(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kg", String.format("%.4f", poundTokg(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ton", String.format("%.4f", poundToton(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("carat", String.format("%.4f", poundTocarrat(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("ounce", String.format("%.4f", poundToounce(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("pound", String.format("%.4f", poundTopound(Double.parseDouble(value)))));
                    break;

            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 3);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allTemperatureConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("°K", String.format("%.4f", kelvinTokelvin(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°F", String.format("%.4f", kelTofah(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°C", String.format("%.4f", kelTocel(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("°K", String.format("%.4f", fahTokel(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°F", String.format("%.4f", fahTofah(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°C", String.format("%.4f", fahTocel(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("°K", String.format("%.4f", celTokel(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°F", String.format("%.4f", celTofah(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("°C", String.format("%.4f", celTocel(Double.parseDouble(value)))));
                    break;

            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 4);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allStorageConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", byteTobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", byteTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", byteTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", byteToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", byteToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", byteToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", byteToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", byteToTB(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", kbTobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", kbTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", kbTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", kbToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", kbToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", kbToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", kbToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", kbToTB(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", kBtobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", kBtokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", kBTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", kBToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", kBToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", kBToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", kBToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", kBToTB(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", Mbtobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", MbTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", MbTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", MbToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", MbToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", MbToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", MbToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", MbToTB(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", MBTobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", MBTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", MBTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", MBToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", MBToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", MBToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", MBToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", MBToTB(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", GbTobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", GbTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", GbTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", GbToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", GbToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", GbToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", GbToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", GbToTB(Double.parseDouble(value)))));
                    break;
                case 6:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", GBTobyte(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", GBTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", GBTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", GBToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", GBToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", GBToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", GBToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", GBToTB(Double.parseDouble(value)))));
                    break;
                case 7:
                    conversionList.add(new Conversion("bytes", String.format("%.4f", TBTobytes(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kb", String.format("%.4f", TBTokb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kB", String.format("%.4f", TBTokB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Mb", String.format("%.4f", TBToMb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MB", String.format("%.4f", TBToMB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("Gb", String.format("%.4f", TBToGb(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GB", String.format("%.4f", TBToGB(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("TB", String.format("%.4f", TBToTB(Double.parseDouble(value)))));
                    break;

            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 5);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allEnergyConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", mJTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", mJToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", mJTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", mJToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", mJToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", mJTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", mJToBTU(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", JTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", JToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", JTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", JToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", JToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", JTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", JToBTU(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", kJTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", kJToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", kJTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", kJToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", kJToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", kJTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", kJToBTU(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", MJTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", MJToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", MJTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", MJToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", MJToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", MJtowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", MJToBTU(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", GJTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", GJToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", GJTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", GJToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", GJToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", GJTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", GJToBTU(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", watthrTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", watthrToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", watthrTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", watthrToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", watthrToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", watthrTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", watthrToBTU(Double.parseDouble(value)))));
                    break;
                case 6:
                    conversionList.add(new Conversion("mJ", String.format("%.4f", BTUTomJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("J", String.format("%.4f", BTUToJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kJ", String.format("%.4f", BTUTokJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MJ", String.format("%.4f", BTUToMJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GJ", String.format("%.4f", BTUToGJ(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("watt-hr", String.format("%.4f", BTUTowatthr(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("BTU", String.format("%.4f", BTUToBTU(Double.parseDouble(value)))));
                    break;
            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 6);
        listView.setAdapter(conversionAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void allPowerConversions() {
        conversionList.clear();
        int pos = fromSpinner.getSelectedItemPosition();
        String value = fromEdit.getText().toString();

        if (!value.isEmpty()) {
            switch (pos) {
                case 0:
                    conversionList.add(new Conversion("watt", String.format("%.4f", wattTowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", wattTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", wattTokW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", wattToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", wattToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", wattTohp(Double.parseDouble(value)))));
                    break;
                case 1:
                    conversionList.add(new Conversion("watt", String.format("%.4f", hWtowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", hWTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", hWTokW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", hWToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", hWToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", hWtohp(Double.parseDouble(value)))));
                    break;
                case 2:
                    conversionList.add(new Conversion("watt", String.format("%.4f", kWTowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", kWTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", kWTokW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", kWToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", kWToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", kWTohp(Double.parseDouble(value)))));
                    break;
                case 3:
                    conversionList.add(new Conversion("watt", String.format("%.4f", MWTowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", MWTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", (MWTokW(Double.parseDouble(value))))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", MWToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", MWToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", MWTohp(Double.parseDouble(value)))));
                    break;
                case 4:
                    conversionList.add(new Conversion("watt", String.format("%.4f", GWTowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", GWTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", GWTokW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", GWToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", GWToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", GWTohp(Double.parseDouble(value)))));
                    break;
                case 5:
                    conversionList.add(new Conversion("watt", String.format("%.4f", hpTowatt(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hW", String.format("%.4f", hpTohW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("kW", String.format("%.4f", hpTokW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("MW", String.format("%.4f", hpToMW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("GW", String.format("%.4f", hpToGW(Double.parseDouble(value)))));
                    conversionList.add(new Conversion("hp", String.format("%.4f", hpTohp(Double.parseDouble(value)))));
                    break;
            }
        }
        conversionAdapter = new ConversionAdapter(getApplicationContext(), conversionList, 7);
        listView.setAdapter(conversionAdapter);
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
                        case 5:
                            res = mJTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mJToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
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
                        case 5:
                            res = JTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = JToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
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
                        case 5:
                            res = kJTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = kJToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
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
                        case 5:
                            res = MJtowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MJToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
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
                        case 5:
                            res = GJTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GJToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = watthrTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = watthrToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = watthrTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = watthrToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = watthrToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = watthrTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = watthrToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = BTUTomJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = BTUToJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = BTUTokJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = BTUToMJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = BTUToGJ(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = BTUTowatthr(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = BTUToBTU(Double.parseDouble(fromEdit.getText().toString().trim()));
                            toEdit.setText(String.format("%.4f", res));
                            break;
                    }
            }
        }
    }

    @SuppressLint("DefaultLocale")
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
                        case 5:
                            res = mJTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = mJToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
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
                        case 5:
                            res = JTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = JToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
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
                        case 5:
                            res = kJTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = JToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
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
                        case 5:
                            res = MJtowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = MJToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
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
                        case 5:
                            res = GJTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = GJToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 5:
                    switch (to) {
                        case 0:
                            res = watthrTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = watthrToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = watthrTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = watthrToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = watthrToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = watthrTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = watthrToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                    }
                    break;
                case 6:
                    switch (to) {
                        case 0:
                            res = BTUTomJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 1:
                            res = BTUToJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 2:
                            res = BTUTokJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 3:
                            res = BTUToMJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 4:
                            res = BTUToGJ(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 5:
                            res = BTUTowatthr(Double.parseDouble(toEdit.getText().toString().trim()));
                            fromEdit.setText(String.format("%.4f", res));
                            break;
                        case 6:
                            res = BTUToBTU(Double.parseDouble(toEdit.getText().toString().trim()));
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
        return num * 0.0032808399d;
    }

    private double mmtoyard(double num) {
        return num * 0.0010936133d;
    }

    private double mmtom(double num) {
        return num * 0.001;
    }

    private double mmtokm(double num) {
        return num * 0.000001;
    }

    private double mmtomi(double num) {
        return num * 6.213688756E-7d;
    }

    private double cmtomm(double num) {
        return num * 10;
    }

    private double cmtocm(double num) {
        return num * 1;
    }

    private double cmtoinch(double num) {
        return num * 0.3937007874d;
    }

    private double cmtofoot(double num) {
        return num * 0.032808399d;
    }

    private double cmtoyard(double num) {
        return num * 0.010936133d;
    }

    private double cmtom(double num) {
        return num * 0.01;
    }

    private double cmtokm(double num) {
        return num * 0.00001;
    }

    private double cmtomi(double num) {
        return num * 0.0000062137d;
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
        return num * 0.0833333333d;
    }

    private double inchtoyard(double num) {
        return num * 0.0277777778d;
    }

    private double inchtom(double num) {
        return num * 0.0254;
    }

    private double inchtokm(double num) {
        return num * 0.0000254;
    }

    private double inchtomi(double num) {
        return num * 0.0000157828d;
    }

    private double foottomm(double num) {
        return num * 304.8d;
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
        return num * 0.3333333333d;
    }

    private double foottom(double num) {
        return num * 0.3048;
    }

    private double foottokm(double num) {
        return num * 0.0003048;
    }

    private double foottomi(double num) {
        return num * 0.0001893932d;
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
        return num * 0.0009144d;
    }

    private double yardtomi(double num) {
        return num * 0.0005681797d;
    }

    private double mtomm(double num) {
        return num * 1000;
    }

    private double mtocm(double num) {
        return num * 100;
    }

    private double mtoinch(double num) {
        return num * 39.37007874d;
    }

    private double mtofoot(double num) {
        return num * 3.280839895d;
    }

    private double mtoyard(double num) {
        return num * 1.0936132983d;
    }

    private double mtom(double num) {
        return num * 1;
    }

    private double mtokm(double num) {
        return num * 0.001;
    }

    private double mtomi(double num) {
        return num * 0.0006213689d;
    }

    private double kmtomm(double num) {
        return num * 1000000;
    }

    private double kmtocm(double num) {
        return num * 100000;
    }

    private double kmtoinch(double num) {
        return num * 39370.07874d;
    }

    private double kmtofoot(double num) {
        return num * 3280.839895d;
    }

    private double kmtoyard(double num) {
        return num * 1093.6132983d;
    }

    private double kmtom(double num) {
        return num * 1000;
    }

    private double kmtokm(double num) {
        return num * 1;
    }

    private double kmtomi(double num) {
        return num * 0.6213688756d;
    }

    private double mitomm(double num) {
        return num * 1609350;
    }

    private double mitocm(double num) {
        return num * 160935;
    }

    private double mitoinch(double num) {
        return num * 63360.23622d;
    }

    private double mitofoot(double num) {
        return num * 5280.019685d;
    }

    private double mitoyard(double num) {
        return num * 1760.0065617d;
    }

    private double mitom(double num) {
        return num * 1609.35d;
    }

    private double mitokm(double num) {
        return num * 1.60935d;
    }

    private double mitomi(double num) {
        return num * 1;
    }

    private double sqcmTosqcm(double num) {
        return num * 1;
    }

    private double sqcmTosqinch(double num) {
        return num * 0.15500031d;
    }

    private double sqcmTosqfoot(double num) {
        return num * 0.001076391d;
    }

    private double sqcmTosqm(double num) {
        return num * 0.0001;
    }

    private double sqcmToacre(double num) {
        return num * 2.471053814E-8d;
    }

    private double sqcmTohectare(double num) {
        return num * 1.E-8;
    }

    private double sqcmTosqkm(double num) {
        return num * 1.E-10d;
    }

    private double sqcmTosqyard(double num) {
        return num * 0.000119599d;
    }

    private double sqinchTosqcm(double num) {
        return num * 6.4516d;
    }

    private double sqinchTosqinch(double num) {
        return num * 1;
    }

    private double sqinchTosqfoot(double num) {
        return num * 0.0069444444d;
    }

    private double sqinchTosqm(double num) {
        return num * 0.00064516d;
    }

    private double sqinchToacre(double num) {
        return num * 1.594E-7d;
    }

    private double sqinchTohectare(double num) {
        return num * 6.4516E-8d;
    }

    private double sqinchTosqkm(double num) {
        return num * 6.4516E-10d;
    }

    private double sqinchTosqyard(double num) {
        return num * 0.0007716049d;
    }

    private double sqfootTosqcm(double num) {
        return num * 929.0304d;
    }

    private double sqfootTosqinch(double num) {
        return num * 144d;
    }

    private double sqfootTosqfoot(double num) {
        return num * 1d;
    }

    private double sqfootTosqm(double num) {
        return num * 0.09290304d;
    }

    private double sqfootToacre(double num) {
        return num * 0.0000229568d;
    }

    private double sqfootTohectare(double num) {
        return num * 0.0000092903d;
    }

    private double sqfootTosqkm(double num) {
        return num * 9.290E-8d;
    }

    private double sqfootTosqyard(double num) {
        return num * 0.1111111111d;
    }

    private double sqmTosqcm(double num) {
        return num * 10000d;
    }

    private double sqmTosqinch(double num) {
        return num * 1550.0031d;
    }

    private double sqmTosqfoot(double num) {
        return num * 10.763910417d;
    }

    private double sqmTosqm(double num) {
        return num * 1;
    }

    private double sqmToacre(double num) {
        return num * 0.0002471054d;
    }

    private double sqmTohectare(double num) {
        return num * 0.0001;
    }

    private double sqmTosqkm(double num) {
        return num * 0.000001;
    }

    private double sqmTosqyard(double num) {
        return num * 1.1959900463d;
    }

    private double acreTosqcm(double num) {
        return num * 40468564.224d;
    }

    private double acreTosqinch(double num) {
        return num * 6272640;
    }

    private double acreTosqfoot(double num) {
        return num * 43560;
    }

    private double acreTosqm(double num) {
        return num * 4046.8564224d;
    }

    private double acreToacre(double num) {
        return num * 1;
    }

    private double acreTohectare(double num) {
        return num * 0.4046856422d;
    }

    private double acreTosqkm(double num) {
        return num * 0.0040468564d;
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
        return num * 2.4710538147d;
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
        return num * 247.10538147d;
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
        return num * 8361.2736d;
    }

    private double sqyardTosqinch(double num) {
        return num * 1296;
    }

    private double sqyardTosqfoot(double num) {
        return num * 9;
    }

    private double sqyardTosqm(double num) {
        return num * 0.83612736d;
    }

    private double sqyardToacre(double num) {
        return num * 0.0002066116d;
    }

    private double sqyardTohectare(double num) {
        return num * 0.0000836127d;
    }

    private double sqyardTosqkm(double num) {
        return num * 8.3612736E-7d;
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
        return num * 0.0021133774d;
    }

    private double cucmTogallon(double num) {
        return num * 0.0002641722d;
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
        return num * 0.0021133774d;
    }

    private double mlTogallon(double num) {
        return num * 0.0002641722d;
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
        return num * 2.1133774149d;
    }

    private double litreTogallon(double num) {
        return num * 0.2641721769d;
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
        return num * 0.47317625d;
    }

    private double pintTopint(double num) {
        return num * 1;
    }

    private double pintTogallon(double num) {
        return num * 0.125d;
    }

    private double pintTocum(double num) {
        return num * 0.0004731763d;
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
        return num * 0.00378541d;
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
        return num * 2113.3774149d;
    }

    private double cumTogallon(double num) {
        return num * 264.17217686d;
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
        return num * 0.000035274d;
    }

    private double mgTopound(double num) {
        return num * 0.0000022046d;
    }

    private double mgTokg(double num) {
        return num * 0.000001;
    }

    private double mgToton(double num) {
        return num * 1E-9d;
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
        return num * 0.0352739907d;
    }

    private double gmTopound(double num) {
        return num * 0.0022046244d;
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
        return num * 0.0070547981d;
    }

    private double carratTopound(double num) {
        return num * 0.0004409249d;
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
        return num * 0.0283495d;
    }

    private double ounceToton(double num) {
        return num * 0.0000283495d;
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
        return num * 0.453592d;
    }

    private double poundToton(double num) {
        return num * 0.000453592d;
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
        return num * 35.273990723d;
    }

    private double kgTopound(double num) {
        return num * 2.2046244202d;
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
        return num * 35273.990723d;
    }

    private double tonTopound(double num) {
        return num * 2204.6244202d;
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

    private double mJTowatthr(double num) {
        return num * 2.777777777E-7d;
    }

    private double mJToBTU(double num) {
        return num * 9.478171203E-7d;
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

    private double JTowatthr(double num) {
        return num * 0.0002777778d;
    }

    private double JToBTU(double num) {
        return num * 0.0009478171d;
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

    private double kJTowatthr(double num) {
        return num * 0.2777777778d;
    }

    private double kJToBTU(double num) {
        return num * 0.9478171203d;
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

    private double MJtowatthr(double num) {
        return num * 277.77777778d;
    }

    private double MJToBTU(double num) {
        return num * 947.81712031d;
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

    private double GJTowatthr(double num) {
        return num * 277777.77778d;
    }

    private double GJToBTU(double num) {
        return num * 947817.12031d;
    }

    private double watthrTomJ(double num) {
        return num * 3600000d;
    }

    private double watthrToJ(double num) {
        return num * 3600d;
    }

    private double watthrTokJ(double num) {
        return num * 3.6d;
    }

    private double watthrToMJ(double num) {
        return num * 0.0036d;
    }

    private double watthrToGJ(double num) {
        return num * 0.0000036d;
    }

    private double watthrTowatthr(double num) {
        return num;
    }

    private double watthrToBTU(double num) {
        return num * 3.4121416331d;
    }

    private double BTUTomJ(double num) {
        return num * 1055055.8526d;
    }

    private double BTUToJ(double num) {
        return num * 1055.0558526d;
    }

    private double BTUTokJ(double num) {
        return num * 1.0550558526d;
    }

    private double BTUToMJ(double num) {
        return num * 0.0010550559d;
    }

    private double BTUToGJ(double num) {
        return num * 0.0000010551d;
    }

    private double BTUTowatthr(double num) {
        return num * 0.2930710702d;
    }

    private double BTUToBTU(double num) {
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

    @Override
    public void onBackPressed() {
        if (appOpened % 7 == 0) {
            MadHouseDialog dialog = new MadHouseDialog(ConversionsActivity.this);
            dialog.show();
        } else {
            finish();
        }
    }
}
