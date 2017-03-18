package com.example.android.popularmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

class Utilities {

    static String detailDateFormatter(String releaseDate){

        char[] releaseYearArr = new char[4];
        StringBuilder releaseYear = new StringBuilder(4);

        for(int i=0; i<4; i++)
        {
            releaseYear.append(releaseDate.charAt(i));
        }
        return releaseYear.toString();
    }

    static String voteFormatter(String votes){
        String formattedVotes;

        String ten = "10";
        formattedVotes = votes + "\\" + ten;
        return formattedVotes;
    }


    static String getPreferredOrder(Context context)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(context.getString(R.string.pref_order_key), context.getString(R.string.pref_order_default));
    }


}
