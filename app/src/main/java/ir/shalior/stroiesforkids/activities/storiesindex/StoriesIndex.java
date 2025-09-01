package ir.shalior.stroiesforkids.activities.storiesindex;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity
public class StoriesIndex extends AppCompatActivity {

    @Extra("storyId")
    long storyId;

    @AfterViews
    public void storyIndexAfterViews() {

    }

}
