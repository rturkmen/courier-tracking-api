package com.migros.courier.util;

import org.apache.lucene.util.SloppyMath;

public class ApiUtil {

    private ApiUtil() {
    }

    public static Double calculateDistance(Double lat, Double lng, Double lat1, Double lng1) {
        return SloppyMath.haversinMeters(lat, lng, lat1, lng1);
    }

}
