package ir.shalior.stroiesforkids.activities.story.fragments.steps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.model.StoryStep;

public class StoryStepFragment extends Fragment {

    public StoryStep step;
    private long id;
    private long storyId;
    private String media;
    private String voice;

    public static StoryStepFragment newInstance(StoryStep step) {
        StoryStepFragment fragment = new StoryStepFragment();
        fragment.getStep(step);
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story_step, container, false);

        ImageView img = rootView.findViewById(R.id.stepImage);

        //set the image content
        Glide.with(this)
                .load(Integer.parseInt(step.getMedia()))
                .into(img);

        return rootView;
    }

}
