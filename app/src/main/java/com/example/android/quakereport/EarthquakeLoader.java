package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by setha on 10-08-2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuake>> {

    private String LOG_TAG = EarthquakeLoader.class.getSimpleName();
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&minmag=3&limit=100";

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthQuake> loadInBackground() {
        Log.i(LOG_TAG,"TEST: loadInBackground() called ...");
        // Don't perform the request if there are no URLs, or the first URL is null.
        String[] urls = {USGS_REQUEST_URL};
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }
        return Utils.fetchEarthquakeData(urls[0]);
    }
}
