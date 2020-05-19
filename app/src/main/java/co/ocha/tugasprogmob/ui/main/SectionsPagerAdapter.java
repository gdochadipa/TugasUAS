package co.ocha.tugasprogmob.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import co.ocha.tugasprogmob.Food.FoodModel;
import co.ocha.tugasprogmob.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private FoodModel foodModel;

    public SectionsPagerAdapter(Context context, FragmentManager fm, FoodModel food) {
        super(fm);
        mContext = context;
        foodModel=food;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment;

        switch (position){
            case 0:
                fragment=PlaceholderFragment.newInstance(position,foodModel);
                break;
            case 1:
                fragment=ReceptFragment.newInstance(position,foodModel);
                break;
            default:
                fragment=PlaceholderFragment.newInstance(position,foodModel);
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}