package ir.shalior.stroiesforkids.activities.questions;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.main.MainActivity;
import ir.shalior.stroiesforkids.activities.questions.dialogs.StarsDialog;
import ir.shalior.stroiesforkids.customviews.StoriesPager;
import ir.shalior.stroiesforkids.model.Story;
import ir.shalior.stroiesforkids.model.StoryBoxer;
import ir.shalior.stroiesforkids.model.UserBoxer;
import ir.shalior.stroiesforkids.util.Helpers;
import ir.shalior.stroiesforkids.util.events.StarEvent;

//A soulotion with event bus?

@EActivity(R.layout.activity_questions)
public class QuestionsActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    @Bean
    StoryBoxer storyBoxer;
    @Bean
    UserBoxer userBoxer;
    @ViewById(R.id.txtTimeRemaining)
    TextView txtTimeRemaining;
    @ViewById(R.id.QuestionsPager)
    StoriesPager questionsPager;
    @ViewById(R.id.btnFinishExam)
    MaterialButton btnFinishExam;
    @ViewById(R.id.btnCheckAnswer)
    MaterialButton btnCheckAnswer;
    QuestionsPagerAdapter questionsPagerAdapter;
    @Extra("storyId")
    long storyId;
    @Extra("timeSeconds")
    int timeSeconds;

    Story story;
    private int questionsCount;
    private AnswerEvent currentAnswer;
    private List<AnswerEvent> answersList = new ArrayList<>();
    private boolean isAnswerCheched = false;

    private String TAG = "dd123";

    @AfterViews
    public void afterViews() {
        init();
        Toast.makeText(this, "the seconds :" + timeSeconds, Toast.LENGTH_SHORT).show();
        setTimer();
    }

    private void init() {
        //for testing
        if (storyId <= 0) {
            storyId = 3;
        }
        story = storyBoxer.getStoryBox().get(storyId);
        questionsCount = story.questions.size();
        pagerHandler();
    }

    private void pagerHandler() {
        //adapter
        Log.d(TAG, "pagerHandler: " + storyId);
        questionsPagerAdapter = new QuestionsPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, storyBoxer.questionsToFragments(story.questions));

        questionsPager.setPagingEnabled(false);
        questionsPager.setAdapter(questionsPagerAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckAnswer(AnswerEvent answer) {
        currentAnswer = answer;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAnswerEvent(AnswerEvent answerEvent) {

        if (answerEvent.isCorrect()) {
            //show a dialog or change the ui so its shown that the answer is corretect
            Toast.makeText(this, "Answer is correct!", Toast.LENGTH_SHORT).show();
        }

        if (answersList.size() > 0) {

            for (AnswerEvent answer : answersList
            ) {
                if (Helpers.answersEqual(answer, answerEvent)) return;
                if (answer.getQuestionId() == answerEvent.getQuestionId()) {
                    answersList.remove(answer);
                    answersList.add(answerEvent);
                    return;
                }
            }

            answersList.add(answerEvent);
            return;
        } else {
            answersList.add(answerEvent);
        }

        Log.d(TAG, "onAnswerEvent: " + answerEvent.getChosenOption() + " | id=" + answerEvent.getQuestionId());
    }

    @Click(R.id.btnFinishExam)
    public void finishExamClicked() {
        int correctCount = 0;
        for (AnswerEvent answer : answersList
        ) {
            if (answer.isCorrect()) {
                correctCount++;
            }
        }
        if (correctCount >= (Math.round(questionsCount / 2f))) {
            Toast.makeText(this, "Correct: " + correctCount + " | Pass", Toast.LENGTH_SHORT).show();
            StarsDialog starsDialog = new StarsDialog();
            starsDialog.setStars(correctCount);
            starsDialog.setCancelable(false);
            starsDialog.show(getSupportFragmentManager(), "b");
            EventBus.getDefault().post(new StarEvent(correctCount, true));

        } else {
            Toast.makeText(this, "Correct: " + correctCount + "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextPage() {
        if (questionsPager.getCurrentItem() + 1 != questionsCount) {
            questionsPager.setCurrentItem(questionsPager.getCurrentItem() + 1, true);
        } else {
            finishExamClicked();
        }
    }

    public void setTimer() {
        //check if the timer is on
        if (timeSeconds == 0) {
            txtTimeRemaining.setVisibility(View.GONE);
            return;
        }

        countDownTimer = new CountDownTimer(timeSeconds * 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimeRemaining.setText("زمان: " + millisUntilFinished / 100);
            }

            @Override
            public void onFinish() {
                //Timer finished
                //show a dialog with result and
                //exit btn , try again btn
                txtTimeRemaining.setText("زمان: " + 0);
                finishExamClicked();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        userBoxer.release();
        super.onStop();
    }

    //apply Default font to the context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
