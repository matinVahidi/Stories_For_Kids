package ir.shalior.stroiesforkids.model;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.objectbox.Box;
import ir.shalior.stroiesforkids.activities.questions.fragments.QuestionFragment;
import ir.shalior.stroiesforkids.activities.story.fragments.steps.StoryLastStepFragment_;
import ir.shalior.stroiesforkids.activities.story.fragments.steps.StoryStepFragment;
import ir.shalior.stroiesforkids.model.myobjectbox.ObjectBox;
import ir.shalior.stroiesforkids.util.Helpers;

@EBean
public class StoryBoxer {
    public static String imagesPath = "file:///android_asset/stories/";
    public static int EASY_STORY = 1;
    public static int NORMAL_STORY = 2;
    public static int HARD_STORY = 3;

    public String TAG = "dd123";

    public static int STORY_QUESTIONS_MODE = 1;
    public static int STORY_UPGRADED = 1;
    public static int STORY_EDUCATIONAL = 0;

    public static int STORY_IS_LOCKED = 0;
    public static int STORY_IS_OPEN = 1;

    private Box<Story> storyBox;
    private Box<StoryStep> stepBox;
    private Box<Question> questionBox;

    @RootContext
    Context context;

    public StoryBoxer() {
        storyBox = ObjectBox.get().boxFor(Story.class);
        stepBox = ObjectBox.get().boxFor(StoryStep.class);
        questionBox = ObjectBox.get().boxFor(Question.class);
    }

    public Box<Story> getStoryBox() {
        return storyBox;
    }

    public Box<StoryStep> getStepBox() {
        return stepBox;
    }

    public List<String> getStoriesTitlesList() {
        List<String> storiesTitlesList = new ArrayList<>();
        for (Story story : storyBox.getAll()
        ) {
            storiesTitlesList.add(story.getName());
        }
        return storiesTitlesList;
    }

    public List<Story> getStoriesByLevel(int lvl) {
        return storyBox.query().equal(Story_.difficulty, lvl).build().find();
    }

    public void makeDummyStories() {
        stepBox.removeAll();
        storyBox.removeAll();

        List<Story> stories = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            Random random = new Random();
            int locked = random.nextBoolean() ? 1 : 0;
            Story story = new Story("داستان" + i, i, i, Math.round((i - 1) / 3) + 1, STORY_EDUCATIONAL, "4000-6000-2500", locked - 1);
            stories.add(story);
        }
        storyBox.put(stories);

        //dummy steps:
        //4steps for each story
        List<Story> storyList = storyBox.getAll();
        List<StoryStep> stepList = new ArrayList<>();
        for (int j = 1; j <= 9; j++) {
            for (int i = 1; i <= 4; i++) {
                //get resource id
                String id = Helpers.resNameToId("step" + i, context);
                StoryStep step = new StoryStep(storyList.get(j - 1).id, i, id, null);
                stepList.add(step);
            }
        }
        stepBox.put(stepList);

        //dummy Questions
        List<Question> questionList = new ArrayList<>();
        String options = "گزینه 1-گزینه2-گزینه3-گزینه4";
        for (int j = 1; j <= 9; j++) {
            int randomI = Helpers.randomInt(4, 8);
            Log.d(TAG, "makeDummyStories: randomint" + randomI);
            Log.d(TAG, "makeDummyStories: J = " + j);
            for (int i = 1; i <= randomI; i++) {
                Question question = new Question(storyList.get(j - 1).id, "سوال " + i, options, Helpers.randomInt(1, 4), null);
                questionList.add(question);
                Log.d(TAG, "makeDummyStories: Questions" + i);
            }
        }
        questionBox.put(questionList);
    }

    public List<StoryStep> getStorySteps(int storyId) {
        //stepBox.query().equal();
        Story story = storyBox.get(storyId);
        return new ArrayList<>(story.steps);
    }

    public List<Fragment> stepsToFragments(List<StoryStep> steps) {
        List<Fragment> fragments = new ArrayList<>();
        for (StoryStep step : steps
        ) {
            fragments.add(StoryStepFragment.newInstance(step));
        }
        fragments.add(StoryLastStepFragment_.builder().build());
        //reversing the list for viewPager rtl
        //Collections.reverse(fragments);
        return fragments;
    }

    public List<Fragment> stepsToFragments(List<StoryStep> steps, Fragment additionalFragment) {
        List<Fragment> fragments = new ArrayList<>();
        for (StoryStep step : steps
        ) {
            fragments.add(StoryStepFragment.newInstance(step));
        }
        if (additionalFragment != null) {
            fragments.add(additionalFragment);
        }
        //reversing the list for viewPager rtl
        //Collections.reverse(fragments);
        return fragments;
    }

    public List<Fragment> questionsToFragments(List<Question> questions) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 1; i <= questions.size(); i++
        ) {
            fragments.add(QuestionFragment.newInstance(questions.get(i - 1), i, questions.size()));
        }
        //reversing the list for viewPager rtl
        //Collections.reverse(fragments);
        return fragments;
    }

    public int getRandomTime(int storyId) {
        Story story = storyBox.get(storyId);
        List<Integer> list = Helpers.stringToIntegerList(story.getStopageTimes());
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public int getRandomTime(Story story) {
        List<Integer> list = Helpers.stringToIntegerList(story.getStopageTimes());
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public void setStoryIsLockedStatus(Story story, boolean isLocked) {
        if (isLocked) story.setIsLocked(STORY_IS_LOCKED);
        else story.setIsLocked(STORY_IS_OPEN);
    }

    public Box<Question> getQuestionBox() {
        return questionBox;
    }

}
