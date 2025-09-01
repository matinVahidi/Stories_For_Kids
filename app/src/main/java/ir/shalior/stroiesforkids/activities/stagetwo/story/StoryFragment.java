package ir.shalior.stroiesforkids.activities.stagetwo.story;

import androidx.fragment.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.model.StoryStep;

@EFragment(R.layout.fragment_stagetwo_story)
public class StoryFragment extends Fragment {

    //member fields
    List<StoryStep> steps;

    int currentPagerPosition = 0;
    int currengStep = 0;
    int stepsCount;
    int pagesCount;
/*    @Extra("storyId")
    long storyId;*/
/*
    @Bean
    StoryBoxer_ storyBoxer;

    @RootContext
    StoriesStageTwoActivity_ activity;*/

    @AfterViews
    public void afterView() {
        init();
        pagerSetup();
        buttonGroupHandler();
    }


    private void pagerSetup() {

    }

    private void buttonGroupHandler() {

    }

    private void init() {

    }

}
