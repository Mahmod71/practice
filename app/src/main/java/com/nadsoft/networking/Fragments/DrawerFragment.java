package com.nadsoft.networking.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nadsoft.networking.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {

    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    final static  String FILE_NAME="Pref";
    private boolean mUserLearnedDrawer;
    private  boolean mSaveInstanceState;
    View view;
    public DrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }
    public void setUp(int fragmentID,DrawerLayout drawelLayout, final Toolbar toolbar) {
        this.mDrawerLayout=drawelLayout;
        view=getActivity().findViewById(R.id.fragment);
        mToggle=new ActionBarDrawerToggle(getActivity(),drawelLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer=true;
                    saveToPreference(getActivity(),FILE_NAME,mUserLearnedDrawer+"");
                }
              getActivity().invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
               // getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6) toolbar.setAlpha(1-slideOffset);
            }
        };
        if (!mUserLearnedDrawer&& !mSaveInstanceState){
            mDrawerLayout.openDrawer(view);
        }
        mDrawerLayout.setDrawerListener(mToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                 mToggle.syncState();
            }
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreference(getActivity(),FILE_NAME,"false"));
        if (savedInstanceState!=null)    mSaveInstanceState=true;
    }
    public static   void saveToPreference(Context context,String preferenceName,String preferenceValue){
        context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE ).edit().putString(preferenceName,preferenceValue).apply();
    }
    public static  String readFromPreference(Context context,String preferenceName,String defualtValue){
     return   context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE ).getString(preferenceName,defualtValue);
    }
}