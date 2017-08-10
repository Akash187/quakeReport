/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(LOG_TAG,"TEST: onCreate() called ...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(new ArrayList<EarthQuake>());
        recyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(1, null, this).forceLoad();

        Log.i(LOG_TAG,"TEST: onCreate() finish ...");

    }

    //Update the adapter with earthquake data
    private void updateUi(List<EarthQuake> earthQuakes){

        adapter = new MyAdapter(earthQuakes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"TEST: onCreateLoader called ...");
        return new EarthquakeLoader(EarthquakeActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {
        Log.i(LOG_TAG,"TEST: onLoaderFinished called ...");
        updateUi(data);
    }


    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        Log.i(LOG_TAG,"TEST: onLoaderReset called ...");
        new MyAdapter(new ArrayList<EarthQuake>());
    }
//
//    //this is a AsyncTask class that was added to prevent app from crashing due to networkOnMainThread exception
//    private class DownloadFilesTask extends AsyncTask<String, Void, ArrayList<EarthQuake>> {
//        protected ArrayList<EarthQuake> doInBackground(String... urls) {
//            // Don't perform the request if there are no URLs, or the first URL is null.
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//            return Utils.fetchEarthquakeData(urls[0]);
//        }
//
//        protected void onPostExecute(ArrayList<EarthQuake> result) {
//            // If there is no result, do nothing.
//            if (result == null) {
//                return;
//            }
//            updateUi(result);
//        }
//    }
}
