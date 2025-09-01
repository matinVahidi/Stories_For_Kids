package ir.shalior.stroiesforkids.games.alefba.activities.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.stroiesforkids.R;

@SuppressLint("Registered")
@EActivity(R.layout.activity_alefba_game)
public class AlefbaGameActivity extends AppCompatActivity {

    @ViewById(R.id.alefbaImageView)
    ImageView alefbaImage;
    @ViewById(R.id.rowLetters1)
    TableRow row1;
    @ViewById(R.id.rowLetters2)
    TableRow row2;

    @Extra("picId")
    long picId;
    @Bean
    AlefbaGamePresenter presenter;

    @AfterInject
    public void afterInject() {
        presenter.setPicId(picId);
        presenter.init();
    }

    @AfterViews
    public void afterViews() {

        int i = 1;
        for (String str : presenter.getAllLetters()
        ) {
            MaterialButton btn = presenter.makeAnswerButton(str);
            if (i <= 4) {
                row1.addView(btn);
            } else {
                row2.addView(btn);
            }
            i++;
        }

        Glide.with(this).asBitmap().load(presenter.getPicUri()).into(alefbaImage);
    }


    //apply Default font to the context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
