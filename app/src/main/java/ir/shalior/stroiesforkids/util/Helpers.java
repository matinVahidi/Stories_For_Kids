package ir.shalior.stroiesforkids.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ir.shalior.stroiesforkids.activities.questions.AnswerEvent;

public class Helpers {
    public static String resNameToId(String name, Context context) {
        int id = context.getResources().getIdentifier("ir.shalior.stroiesforkids:drawable/" + name, null, null);
        return Integer.toString(id);
    }

    public static List<Integer> stringToIntegerList(String s) {
        String[] strings = s.split("-");
        List<Integer> list = new ArrayList<>();
        for (String time : strings
        ) {
            list.add(Integer.parseInt(time));
        }
        return list;
    }

    //Converts "foo-fly-fun-f" to a list of Strings separated by -
    public static List<String> shaliorStringsToList(String s) {
        String[] strings = s.split("-");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, strings);
        return list;
    }

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static boolean answersEqual(AnswerEvent a1, AnswerEvent a2) {
        if (a1.getQuestionId() != a2.getQuestionId()) return false;
        return a1.getChosenOption() == a2.getChosenOption();
    }

    //Convert dp to pixel
    private static Float scale;

    public static int dpToPixel(int dp, Context context) {
        if (scale == null)
            scale = context.getResources().getDisplayMetrics().density;
        return (int) ((float) dp * scale);
    }
}
