package ir.shalior.stroiesforkids.activities;

import android.content.Context;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.questions.QuestionsActivity_;
import ir.shalior.stroiesforkids.activities.story.StoryActivity_;
import ir.shalior.stroiesforkids.model.StoryBoxer;

@EActivity(R.layout.activity_option_lists)
public class OptionListsActivity extends AppCompatActivity {
    @Bean
    StoryBoxer storyBoxer;

    @ViewById(R.id.txtStoryTitle)
    TextView txtStoryTitle;

    @Extra("storyId")
    long storyId;

    @AfterViews
    public void afterViews() {
        txtStoryTitle.setText(storyBoxer.getStoryBox().get(storyId).getName());
    }

    @Click(R.id.btnPractice)
    public void btnPracticeClicked() {
        StoryActivity_.intent(this).extra("storyId", storyId).start();
    }

    @Click(R.id.btnTimedQuestions)
    public void btnTimedQuestionsClicked(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.timed_questions_time_select_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.second_45:
                    QuestionsActivity_.intent(this).extra("storyId", storyId).extra("timeSeconds", 45).start();
                    return true;
                case R.id.second_60:
                    QuestionsActivity_.intent(this).extra("storyId", storyId).extra("timeSeconds", 60).start();
                    return true;
                case R.id.second_75:
                    QuestionsActivity_.intent(this).extra("storyId", storyId).extra("timeSeconds", 75).start();
                    return true;
                default:
                    return false;
            }
        });

        popup.show();
    }

    @Click(R.id.btnRandomStop)
    public void btnRandomStopClicked() {
        StoryActivity_.intent(this).extra("storyId", storyId).extra("stageTwo", true).start();
    }

    @Click(R.id.btnQuestions)
    public void btnQuestionsClicked() {
        QuestionsActivity_.intent(this).extra("storyId", storyId).start();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
