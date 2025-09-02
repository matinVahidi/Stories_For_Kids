package ir.shalior.stroiesforkids.activities.stagetwo.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.model.StoryStep;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stagetwo_story, container, false);
        init();
        pagerSetup();
        buttonGroupHandler();
        return rootView;
    }


    private void pagerSetup() {

    }

    private void buttonGroupHandler() {

    }

    private void init() {

    }

}
