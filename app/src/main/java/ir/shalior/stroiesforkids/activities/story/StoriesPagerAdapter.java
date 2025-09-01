package ir.shalior.stroiesforkids.activities.story;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class StoriesPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;

    public StoriesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public StoriesPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + " / " + getCount();
    }
}
