package ir.shalior.stroiesforkids.games.alefba.activities.game;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ir.shalior.stroiesforkids.model.alefbagame.AlefbaBoxer;
import ir.shalior.stroiesforkids.model.alefbagame.AlefbaPic;
import ir.shalior.stroiesforkids.util.Helpers;

@EBean
public class AlefbaGamePresenter {

    @Bean
    public AlefbaBoxer alefbaBoxer;
    private List<String> allLetters;

    @RootContext
    AlefbaGameActivity gameActivity;
    private long picId;
    private List<String> currectLetter;
    private AlefbaPic alefbaPic;

    public AlefbaGamePresenter() {

    }

    /*
     * makes data ready to use - fake instructor of
     */
    public void init() {
        alefbaPic = alefbaBoxer.getAlefbaPicBox().get(picId);
        currectLetter = alefbaPic.getCurrectLettersList();
        List<String> wrongLetters = alefbaPic.getWrongLettersList();

        //merge 2 lists and shuffle them
        allLetters = new ArrayList<String>();
        allLetters.addAll(currectLetter);
        allLetters.addAll(wrongLetters);
        Collections.shuffle(allLetters);
    }

    protected void setPicId(long picId) {
        this.picId = picId;
    }

    protected List<String> getAllLetters() {
        return allLetters;
    }

    public Uri getPicUri() {
        return alefbaPic.getMediaUri();
    }

    void btnClicked(View v) {
        if (!(v instanceof MaterialButton)) return;
        MaterialButton btn = (MaterialButton) v;

        String selectedLetter = btn.getText().toString();
        //is correct?
        if (currectLetter.contains(selectedLetter)) {
            currectAnswer(btn);
        } else {
            wrongAnswer(btn);
        }
    }

    private void wrongAnswer(MaterialButton btn) {
        Toast.makeText(gameActivity, "Wrong!", Toast.LENGTH_SHORT).show();
        btn.setClickable(false);
        btn.setEnabled(false);
    }

    private void currectAnswer(MaterialButton btn) {
        Toast.makeText(gameActivity, "correct!", Toast.LENGTH_SHORT).show();
        btn.setClickable(false);
        btn.setEnabled(false);
        btn.setBackgroundColor(Color.green(123));
    }

    //answer button generator
    public MaterialButton makeAnswerButton(String str) {
        MaterialButton btn = new MaterialButton(gameActivity);
        android.widget.TableRow.LayoutParams p = new android.widget.TableRow.LayoutParams();
        p.rightMargin = Helpers.dpToPixel(4, gameActivity);
        p.leftMargin = Helpers.dpToPixel(4, gameActivity);
        p.weight = 1;
        btn.setLayoutParams(p);
        btn.requestLayout();
        btn.setText(str);
        btn.setOnClickListener(this::btnClicked);
        return btn;
    }
}
