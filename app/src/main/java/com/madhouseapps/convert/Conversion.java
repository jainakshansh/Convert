package com.madhouseapps.convert;

/**
 * Created by Akshansh on 05-12-2017.
 */

public class Conversion {

    private String units;
    private String values;

    public Conversion() {
    }

    public Conversion(String units, String values) {
        this.units = units;
        this.values = values;
    }

    public String getUnits() {
        return units;
    }

    public String getValues() {
        return values;
    }
}
