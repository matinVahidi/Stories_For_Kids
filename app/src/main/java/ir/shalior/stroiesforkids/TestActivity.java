package ir.shalior.stroiesforkids;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import ir.shalior.stroiesforkids.activities.story.MediaPlayerList;
import ir.shalior.stroiesforkids.model.StoryBoxer;
import ir.shalior.stroiesforkids.model.UserBoxer;

@EActivity
public class TestActivity extends AppCompatActivity {
    String TAG = "dd123";
    MediaPlayer mp;
    @Bean
    StoryBoxer storyBoxer;
    MediaPlayerList list;
    @ViewById(R.id.button)
    Button btn;

    @ViewById(R.id.button3)
    Button btn3;
    @Extra("tesdddt")
    String s;

    @Bean
    UserBoxer userBoxer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: act started");
        setContentView(R.layout.activity_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Log.d(TAG, "onCreate: user" + userBoxer.getUser().id);


    }

    @AfterViews
    public void xxx() {
        if (s != null) {
            btn3.setText(s);
        }
        btn3.setText(userBoxer.getStartsCount() + "333");
    }
    public void setupMediaplayer(int stepsCount) {

    }

    @Click(R.id.button)
    public void clickV() {
        TestActivity_.intent(this).extra("test", "testtttt").start();
    }

    @Click(R.id.button2)
    public void clickV2() {
        //mp.start();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
        Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        //btn3.setAnimation(blink);
        btn.startAnimation(blink);
        btn.setVisibility(View.GONE);
    }
}
