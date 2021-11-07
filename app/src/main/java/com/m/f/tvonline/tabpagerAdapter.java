package com.m.f.tvonline;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.m.f.tvonline.fragments.one;
import com.m.f.tvonline.fragments.two;

/**
 * Created by NIPU on 12/27/2017.
 */

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    String[] tabarray = new String[]{"Canais","Filmes"};
    Integer tabnumber = 2;

    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                one one1 = new one();
                return one1;
            case 1:
                two two2 = new two();
                return two2;


        }


        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
