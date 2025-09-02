package ir.shalior.stroiesforkids.activities;

import android.content.Context;
import android.content.Intent;
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
import ir.shalior.stroiesforkids.activities.questions.QuestionsActivity;
import ir.shalior.stroiesforkids.activities.story.StoryActivity;
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
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("storyId", storyId);
        startActivity(intent);
    }

    @Click(R.id.btnTimedQuestions)
    public void btnTimedQuestionsClicked(View v) {

        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.timed_questions_time_select_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.second_45: {
                    Intent intent45 = new Intent(this, QuestionsActivity.class);
                    intent45.putExtra("storyId", storyId);
                    intent45.putExtra("timeSeconds", 45);
                    startActivity(intent45);
                    return true;
                }case R.id.second_60: {
                    Intent intent60 = new Intent(this, QuestionsActivity.class);
                    intent60.putExtra("storyId", storyId);
                    intent60.putExtra("timeSeconds", 60);
                    startActivity(intent60);
                    return true;
                }
                case R.id.second_75: {
                    Intent intent75 = new Intent(this, QuestionsActivity.class);
                    intent75.putExtra("storyId", storyId);
                    intent75.putExtra("timeSeconds", 75);
                    startActivity(intent75);
                    return true;
                }
                default:
                    return false;
            }
        });

        popup.show();
    }

    @Click(R.id.btnRandomStop)
    public void btnRandomStopClicked() {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("storyId", storyId);
        intent.putExtra("stageTwo", true);
        startActivity(intent);
    }

    @Click(R.id.btnQuestions)
    public void btnQuestionsClicked() {
        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.putExtra("storyId", storyId);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
