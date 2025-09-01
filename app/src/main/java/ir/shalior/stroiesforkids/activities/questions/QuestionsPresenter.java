package ir.shalior.stroiesforkids.activities.questions;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import ir.shalior.stroiesforkids.model.StoryBoxer;

@EBean
public class QuestionsPresenter {

    @RootContext
    AppCompatActivity QuestionsActivity;

    @Bean
    StoryBoxer storyBoxer;

}
