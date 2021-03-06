package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback {

    private String mSortOrder;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    boolean mTwoPane;

    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //
//            if(savedInstanceState == null){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_detail, new DetailActivityFragment())
//                        .commit();
//            }
        mTwoPane = findViewById(R.id.fragment_detail) != null;

        mSortOrder = Utilities.getPreferredOrder(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(MovieInfo movieInfo) {

        if(mTwoPane){

            Bundle bundle = new Bundle();
            bundle.putSerializable("curMovie", movieInfo);

            DetailActivityFragment fragment = new DetailActivityFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail, fragment, DETAILFRAGMENT_TAG)
                    .commit();
        }
        else{
            Intent intent = new Intent(this, Element.class).putExtra("curMovie", movieInfo);
            startActivity(intent);
        }

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        String sortOder = Utilities.getPreferredOrder(this);
//        // update the location in our second pane using the fragment manager
//        if (sortOder != null && !sortOder.equals(mSortOrder)) {
//            MainActivityFragment maf = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_main);
//            if ( null != maf ) {
//                maf.onOrderChanged();
//                Log.i(LOG_TAG, "onOrderChanged called");
//            }
//            mSortOrder = sortOder;
//
//        }
//    }
}
