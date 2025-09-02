package ir.shalior.stroiesforkids.activities.stagetwo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.stagetwo.story.StoryFragment;

@EActivity(R.layout.activity_stories_stage_two)
public class StoriesStageTwoActivity extends FragmentActivity {
    String TAG = "d123";

    @AfterViews
    public void afterView() {

        //default fragment
        loadFragment(new StoryFragment());
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.stagetwo_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
