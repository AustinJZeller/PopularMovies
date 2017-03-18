package com.example.android.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class FetchTrailersTask extends AsyncTask<MovieInfo, Void, List<TrailersInfo>> {

    private final String LOG_TAG = FetchTrailersTask.class.getSimpleName();

    private TrailerListAdapter mTrailerListAdapter;
    private final Context mContext;


    public FetchTrailersTask(Context context, TrailerListAdapter trailerListAdapter) {
        mContext = context;
        mTrailerListAdapter = trailerListAdapter;
    }


    @Override
    protected List<TrailersInfo> doInBackground(MovieInfo... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String trailersJsonStr = null;
        MovieInfo curMovie = params[0];
        final String movieId = curMovie.getId();
        Log.v(LOG_TAG, "Movie id:" + movieId);


        try {

            // final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/" + order + "?";

            final String TRAILERS_BASE_URL = "http://api.themoviedb.org/3/movie/" + movieId + "/videos?";
            final String API_KEY = "api_key";

            Uri builtUri = Uri.parse(TRAILERS_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY, BuildConfig.MOVIE_API_KEY)
                    .build();

            // String apiKey = "&APPID=" + BuildConfig.OPEN_WEATHER_MAP_API_KEY;
            //URL url = new URL(bui.concat(apiKey));

            URL url = new URL(builtUri.toString());

            //Log.v(LOG_TAG, "Built URI: " + builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            trailersJsonStr = buffer.toString();

            Log.v(LOG_TAG, "Trailer JSON String: " + trailersJsonStr);


        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            trailersJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

        }

        try {
            return getTrailerDataFromJson(trailersJsonStr);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;

    }

    private List<TrailersInfo> getTrailerDataFromJson(String trailersJsonStr)
            throws JSONException {

        final String T_RESULTS = "results";


        JSONObject trailersJson = new JSONObject(trailersJsonStr);
        JSONArray trailersArray = trailersJson.getJSONArray(T_RESULTS);


        List<TrailersInfo> trailersList = new ArrayList<TrailersInfo>();


        for (int i = 0; i < trailersArray.length(); i++) {
            String trailerTitle;
            String trailerUrl;

            JSONObject trailer = trailersArray.getJSONObject(i);

            trailerTitle = trailer.getString("name");
            trailerUrl = trailer.getString("key");

            TrailersInfo trailersInfo = new TrailersInfo(trailerTitle, trailerUrl);

            trailersList.add(trailersInfo);
        }

        //for (MovieInfo movie : moviesList) {
        //  Log.v(LOG_TAG, "Movie poster: " + movie.getImageUrl());
        // }
        return trailersList;


    }


    @Override
    protected void onPostExecute(List<TrailersInfo> trailersList) {
        if(trailersList != null)
        {
            mTrailerListAdapter.clear();
            for(TrailersInfo trailer: trailersList)
            {
                mTrailerListAdapter.add(trailer);

                //View item = mTrailerListAdapter.getView(trailer, null, null);
            }

            LayoutInflater inflater = LayoutInflater.from(mContext);


        }
//            if ( null != maf ) {
//                maf.onOrderChanged();
//                Log.i(LOG_TAG, "onOrderChanged called");
//            }


    }






}
