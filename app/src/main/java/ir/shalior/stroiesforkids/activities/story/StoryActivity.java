package ir.shalior.stroiesforkids.activities.story;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.main.MainActivity_;
import ir.shalior.stroiesforkids.activities.questions.QuestionsActivity_;
import ir.shalior.stroiesforkids.activities.story.dialogs.StageTwoIntroDialog;
import ir.shalior.stroiesforkids.activities.story.dialogs.StageTwoPausedDialog;
import ir.shalior.stroiesforkids.customviews.StoriesPager;
import ir.shalior.stroiesforkids.model.Story;
import ir.shalior.stroiesforkids.model.StoryBoxer;
import ir.shalior.stroiesforkids.model.StoryStep;

@EActivity(R.layout.activity_story)
public class StoryActivity extends FragmentActivity {

    //member fields
    List<StoryStep> steps;
    Story story;
    int currentPagerPosition = 0;
    int currengStep = 0;
    int stepsCount;
    int pagesCount;
    int[] mStopTimes;
    int idFX1;
    int nowPlaying;
    int loadedNum = 0;
    boolean isRandomlyStoped = false;
    boolean isResumedOnStageTwo = false;
    String TAG = "dd123";
    List<Integer> streamIds = new ArrayList<>();
    MediaPlayerList mediaPlayerList;
    //class injection
    @Bean
    StoryBoxer storyBoxer;

    //Views injection
    @ViewById(R.id.progressBar)
    ProgressBar progressBar;
    @ViewById(R.id.storyIdText)
    TextView storyIdText;
    @ViewById(R.id.btnNextStep)
    MaterialButton buttonNextt;
    @ViewById(R.id.btnPlayPauseStep)
    MaterialButton buttonPlayPause;
    @ViewById(R.id.btnPrevStep)
    MaterialButton buttonPrev;
    @ViewById(R.id.storyPager)
    StoriesPager storiesPager;
    @ViewById(R.id.containerStory)
    ConstraintLayout constraintLayout;
    @ViewById(R.id.toggle_button_group)
    MaterialButtonToggleGroup buttonGroup;
    StageTwoPausedDialog stageTwoPausedDialog;
    @Extra("storyId")
    long storyId;

    @Extra("stageTwo")
    boolean isStageTwo;

    @AfterViews
    public void afterViewww() {
        init();
        if (isStageTwo) {
            stageTwoHandler();
        }
        pagerSetup();
        buttonGroupHandler();
    }

    private void pageChanged(int pos) {
        currentPagerPosition = pos;
        currengStep = pos;
        if (isStageTwo) {
            if (pos != (pagesCount - 1) && !isResumedOnStageTwo) {
                mediaPlayerList.play(pos);
            }
        }
        buttonPlayPause.setChecked(false);
        if (pos == (pagesCount - 1)) {
            buttonGroup.setVisibility(View.GONE);
            return;
        }
        if (pos == 0) {
            buttonPrev.setClickable(false);
            buttonPrev.setEnabled(false);
            return;
        }

        //default mode:
        buttonPrev.setClickable(true);
        buttonPrev.setEnabled(true);
        buttonGroup.setVisibility(View.VISIBLE);
    }

    private void init() {
        storyIdText.setText(storyBoxer.getStoryBox().get(storyId).getName());
        ViewCompat.setLayoutDirection(constraintLayout, ViewCompat.LAYOUT_DIRECTION_RTL);

        story = storyBoxer.getStoryBox().get(storyId);
        steps = storyBoxer.getStoryBox().get(storyId).steps;
        stepsCount = steps.size();
        this.mediaPlayerList = new MediaPlayerList(this, 3);
        mediaPlayerList.setOnAllPreparedListener(new MediaPlayerList.OnAllPreparedListener() {
            @Override
            public void onAllPrepared() {
                Log.d(TAG, "onAllPrepared: ALL PREP HORRAAAYYYYY");
            }
        });
    }

    private void pagerSetup() {
        StoriesPagerAdapter pagerAdapter = new StoriesPagerAdapter(getSupportFragmentManager(), storyBoxer.stepsToFragments(storyBoxer.getStoryBox().get(storyId).steps));
        storiesPager.setAdapter(pagerAdapter);
        //storiesPager.setLastItemAsDefault();
        storiesPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: SELECTED!!!!  " + position);
                pageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        storiesPager.setPageTransformer(true, new FlipHorizontalTransformer());
        storiesPager.setPagingEnabled(true);
        if (isStageTwo) storiesPager.setPagingEnabled(false);
        pagesCount = pagerAdapter.getCount();
    }

    private void buttonGroupHandler() {
        buttonGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (checkedId == R.id.btnPlayPauseStep) {

                    if (isChecked) {
                        //play button clicked
                        //add stage two logic
                        if (isStageTwo) {
                            //view changing
                            if (!isResumedOnStageTwo) {
                                buttonPlayPause.setChecked(false);
                                buttonPlayPause.setEnabled(false);
                            }
                            mediaPlayerList.play(currengStep);
                            //set the timer to pause
                            if (!isRandomlyStoped) {
                                mediaPlayerList.stopPlayingOn(storyBoxer.getRandomTime(story));
                            }
                            if (!isResumedOnStageTwo) buttonGroup.setVisibility(View.GONE);

                        }
                        buttonPlayPause.setIcon(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                        buttonPlayPause.setText("توقف");
                        mediaPlayerList.play(currengStep);
                    } else {
                        //puase btn clicked
                        buttonPlayPause.setIcon(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                        buttonPlayPause.setText("پخش");
                        mediaPlayerList.pauseAll();
                    }
                }
            }
        });
    }

    public void stageTwoHandler() {
        //view modifications
        buttonPlayPause.setText("شروع");
        buttonNextt.setVisibility(View.GONE);
        buttonPrev.setVisibility(View.GONE);
        storiesPager.setPagingEnabled(false);
        //
        Log.d(TAG, "stageTwoHandler: " + storyBoxer.getRandomTime(story));

        mediaPlayerList.setOnAMediaFinishedListener(new MediaPlayerList.OnAMediaFinishedListener() {
            @Override
            public void onAMediaFinished(MediaPlayer mediaPlayer) {
                if (currentPagerPosition != pagesCount) {
                    storiesPager.setCurrentItem(currentPagerPosition + 1);
                }
            }
        });
        mediaPlayerList.setOnRandomPausedListener(new MediaPlayerList.OnRandomPausedListener() {
            @Override
            public void OnRandomPaused() {
                stageTwoPausedDialog = new StageTwoPausedDialog();
                stageTwoPausedDialog.setCancelable(false);
                stageTwoPausedDialog.show(getSupportFragmentManager(), "ff");

                storiesPager.setPagingEnabled(true);
/*                buttonGroup.setVisibility(View.VISIBLE);
                buttonPlayPause.setText("ادامه");
                buttonPlayPause.setChecked(false);
                buttonPlayPause.setEnabled(true);*/
                isRandomlyStoped = true;
            }
        });

        //showing the diaog
        StageTwoIntroDialog introDialog = new StageTwoIntroDialog();
        introDialog.show(getSupportFragmentManager(), "s");
    }

    @Click(R.id.btnNextStep)
    public void nextClicked() {
        storiesPager.setCurrentItem(currentPagerPosition + 1);
        buttonPlayPause.setChecked(false);

    }

    @Click(R.id.btnPrevStep)
    public void prevClicked() {
        storiesPager.setCurrentItem(currentPagerPosition - 1);
        buttonPlayPause.setChecked(false);
    }

    public void restartClicled() {
        storiesPager.setCurrentItem(0);
        buttonPrev.setClickable(true);
        buttonPrev.setEnabled(true);
        buttonNextt.setClickable(true);
        buttonNextt.setEnabled(true);
        buttonPlayPause.setEnabled(true);
        buttonGroup.setVisibility(View.VISIBLE);
    }

    public void dialogResumeButtonclicked() {
        isResumedOnStageTwo = true;
        Log.d(TAG, "dialogResumeButtonclicked: :)");
        mediaPlayerList.play(currentPagerPosition);
        stageTwoPausedDialog.dismiss();
        buttonGroup.setVisibility(View.VISIBLE);
        buttonNextt.setVisibility(View.VISIBLE);
        buttonPrev.setVisibility(View.VISIBLE);
        buttonPlayPause.setChecked(true);
        buttonPlayPause.setEnabled(true);
        buttonPlayPause.setChecked(true);
    }

    public void dialogNexStageButtonClicked() {
        QuestionsActivity_.intent(this).extra("storyId", storyId).start();
    }

    public void nextStageClicked() {
        StoryActivity_.intent(this).extra("storyId", storyId).extra("stageTwo", true).start();
        Log.d(TAG, "nextStageClicked: " + storyId + "   " + isStageTwo);
    }


    @Override
    public void onBackPressed() {
        MainActivity_.intent(this).start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerList.releseAll();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
