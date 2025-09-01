package ir.shalior.stroiesforkids.activities.story;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ir.shalior.stroiesforkids.model.StoryBoxer;

@EBean
public class StageTwoPresenter {

    @Bean
    StoryBoxer storyBoxer;


    public List<Integer> stoppageTimesList(String s) {
        String[] strings = s.split("-");
        List<Integer> list = new ArrayList<>();
        for (String time : strings
        ) {
            list.add(Integer.parseInt(time));
        }
        return list;
    }


    public int getRandomTime(List<Integer> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


}
