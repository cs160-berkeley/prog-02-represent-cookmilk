package com.example.cookmilk.prog02b;


import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.wearable.view.GridPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
//    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        final LinearLayout stub = (LinearLayout) findViewById(R.id.linear);
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        // if (extras != null) {
        //     int randInt = extras.getInt("ZIP");
        //     ((Helper) this.getApplication()).setZip(randInt);
        // }
        if (extras != null) {
            String candidateString = extras.getString("CANDIDATE");
            if (candidateString.equals("One")) {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 0));
            } else if (candidateString.equals("Two")) {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 1));
            } else {
                pager.setAdapter(new MyGridViewPagerAdapter(this, 2));
            }

        } else {
            pager.setAdapter(new MyGridViewPagerAdapter(this, 2));
        }

        LinearLayout vote2012 = (LinearLayout) findViewById(R.id.clicker);

        vote2012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, VoteActivity.class);
                startActivity(i);
            }
        });

    }
    private class MyGridViewPagerAdapter extends GridPagerAdapter {
        Context contexts;
        int rows;

        public MyGridViewPagerAdapter(final Context context, int row) {
            contexts = context;
            rows = row;
        }

        @Override
        public int getColumnCount(int arg0) {
            return 3;
        }

        @Override
        public int getRowCount() {
            return 1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int row, final int col) {
            final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.detail_result, container, false);
            final TextView textViewParty = (TextView) view.findViewById(R.id.party);
            final Button buttonName = (Button) view.findViewById(R.id.name);
            if (col == 0) {
                buttonName.setText("Barbara Boxer");
                textViewParty.setText("D");
            } else if (col == 1) {
                buttonName.setText("Dianne Feinstein");
                textViewParty.setText("D");
            } else {
                buttonName.setText("Sam Farr");
                textViewParty.setText("D");
            }
            buttonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("T", "Watch button is clicked");
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);

                    if (col == 0) {
                        sendIntent.putExtra("CANDIDATE", "first");
                    } else if (col == 1) {
                        sendIntent.putExtra("CANDIDATE", "second");
                    } else {
                        sendIntent.putExtra("CANDIDATE", "third");
                    }
                    startService(sendIntent);
                }
            });
            Log.d("T", "Watch button is set");
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int row, int col, Object view) {
            container.removeView((View)view);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
