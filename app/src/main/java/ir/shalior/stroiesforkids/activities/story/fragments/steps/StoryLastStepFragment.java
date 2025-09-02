package ir.shalior.stroiesforkids.activities.story.fragments.steps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.story.StoryActivity;
import ir.shalior.stroiesforkids.model.StoryStep;

@EFragment
public class StoryLastStepFragment extends Fragment {

    public StoryStep step;
    StoryActivity storyActivity;
    private long id;
    private long storyId;
    private String media;
    private String voice;

    public static StoryLastStepFragment newInstance() {
        StoryLastStepFragment fragment = new StoryLastStepFragment();
        return fragment;
    }

    private void getStep(StoryStep step) {
        id = step.getId();
        media = step.getMedia();
        voice = step.getVoice();
        this.step = step;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storyActivity = (StoryActivity) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_laststep, container, false);

        ImageView img = rootView.findViewById(R.id.imageViewLastStep);

        //set the image content
        Glide.with(this)
                .load(R.drawable.placeholder)
                .into(img);
        return rootView;
    }

    @Click(R.id.restartButton)
    public void restartClicked() {
        storyActivity.restartClicled();
    }

    @Click(R.id.nextStageButton)
    public void nextStageButtomClicked() {
        storyActivity.nextStageClicked();
    }
}