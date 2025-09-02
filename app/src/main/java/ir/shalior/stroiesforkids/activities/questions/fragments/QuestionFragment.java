package ir.shalior.stroiesforkids.activities.questions.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.questions.AnswerEvent;
import ir.shalior.stroiesforkids.activities.questions.QuestionsActivity;
import ir.shalior.stroiesforkids.model.Question;
import ir.shalior.stroiesforkids.util.Helpers;

import static ir.shalior.stroiesforkids.activities.story.MediaPlayerList.TAG;

public class QuestionFragment extends Fragment {
    private Question question;
    List<MaterialRadioButton> materialRadioButtonList;
    private List<String> options;
    private RadioGroup radioGroup;
    private TextView txtQuestionText;
    private TextView txtAnswerMessage;
    private TextView txtQuestionNumber;
    private MaterialButton btnAnswerCheck;
    private AnswerEvent answer;
    private QuestionsActivity questionsActivity;
    private boolean isChecked = false;
    private int currectColor = Color.parseColor("#43a047");
    private int falseColor = Color.parseColor("#e53935");
    private int questionNum;
    private int qTotal;
    Typeface typeface;

    public static QuestionFragment newInstance(Question question, int questionNumber, int qTotal) {
        QuestionFragment fragment = new QuestionFragment();
        fragment.getQuestion(question);
        fragment.getQNum(questionNumber, qTotal);
        return fragment;
    }

    private void getQNum(int qNum, int qTotal) {
        this.questionNum = qNum;
        this.qTotal = qTotal;
    }
    private void getQuestion(Question question) {
        this.question = question;
    }

    private int chosenOption;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.d(TAG, "onCreateView: fragment created" + question.id);
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        questionsActivity = (QuestionsActivity) getContext();
        answer = new AnswerEvent(question.id, -1, false);

        //Views
        txtAnswerMessage = rootView.findViewById(R.id.txtAnswerMessage);
        txtQuestionNumber = rootView.findViewById(R.id.txtQuestionNumber);
        txtQuestionText = rootView.findViewById(R.id.textQuestionText);
        txtQuestionText.setText(question.title);
        Log.d(TAG, "onCreateView: typeface" + txtQuestionText.getTypeface());
        typeface = txtQuestionText.getTypeface();
        radioGroup = rootView.findViewById(R.id.questionsQoptionsGroup);
        btnAnswerCheck = rootView.findViewById(R.id.btnCheckAnswer);

        //Question number
        txtQuestionNumber.setText(qTotal + " / " + questionNum);

        //Select option
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d(TAG, "onCheckedChanged: " + i);
                chosenOption = i;
                answer.setChosenOption(i);
                if (chosenOption == question.currectOption) answer.setCorrect(true);
                else answer.setCorrect(false);
                EventBus.getDefault().post(answer);
            }
        });
        optionsHandler();

        checkAnswerBtn();
        return rootView;
    }

    private void checkAnswerBtn() {
        if (isChecked) {
            //call the change page function form QA
            btnAnswerCheck.setOnClickListener(null);
            btnAnswerCheck.setOnClickListener(view -> {
                questionsActivity.nextPage();
            });

        } else {
            btnAnswerCheck.setOnClickListener(view -> {
                //disabling the options
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    radioGroup.getChildAt(i).setEnabled(false);
                }

                //check if the answer is correct and UI changes
                if (answer.isCorrect()) {
                    txtQuestionText.setTextColor(currectColor);
                    for (MaterialRadioButton radioButton : materialRadioButtonList
                    ) {
                        radioButton.setTextColor(currectColor);
                    }
                    btnAnswerCheck.setBackgroundColor(currectColor);
                    btnAnswerCheck.setText("بعدی");
                    isChecked = true;

                    //animation
                    txtAnswerMessage.setVisibility(View.VISIBLE);
                    txtAnswerMessage.setBackgroundColor(currectColor);
                    txtAnswerMessage.setText("درست!");
                    YoYo.with(Techniques.BounceInRight).delay(0).duration(1000).playOn(txtAnswerMessage);
                    //animation

                    checkAnswerBtn();
                }
                //actions when the answer is incorrect , UI changes accordingly
                else {
                    txtQuestionText.setTextColor(falseColor);
                    for (MaterialRadioButton radioButton : materialRadioButtonList
                    ) {
                        radioButton.setTextColor(falseColor);
                    }
                    btnAnswerCheck.setBackgroundColor(falseColor);
                    btnAnswerCheck.setText("بعدی");
                    isChecked = true;

                    //animation
                    txtAnswerMessage.setVisibility(View.VISIBLE);
                    txtAnswerMessage.setBackgroundColor(falseColor);
                    txtAnswerMessage.setText("نادرست!");
                    YoYo.with(Techniques.BounceInDown).delay(0).duration(1000).playOn(txtAnswerMessage);
                    //animation

                    checkAnswerBtn();
                }
            });
        }
    }

    public void currectAnswerNext() {

    }

    public void optionsHandler() {
        options = Helpers.shaliorStringsToList(question.options);
        materialRadioButtonList = new ArrayList<>();
        int i = 1;
        for (String option : options
        ) {
            MaterialRadioButton radioButton = new MaterialRadioButton(getContext());
            radioButton.setText(option);
            radioButton.setId(i);
            i++;
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            radioButton.setTypeface(typeface);
            radioButton.setTextSize(20f);
            materialRadioButtonList.add(radioButton);
        }

        //adding these to group:
        for (MaterialRadioButton radio : materialRadioButtonList
        ) {
            radioGroup.addView(radio);
        }
    }

    public int getChosenOption() {
        return chosenOption;
    }


}
