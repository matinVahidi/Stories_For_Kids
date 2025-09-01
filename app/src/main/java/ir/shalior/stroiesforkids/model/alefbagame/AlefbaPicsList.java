package ir.shalior.stroiesforkids.model.alefbagame;

import java.util.ArrayList;
import java.util.List;

import static ir.shalior.stroiesforkids.model.alefbagame.AlefbaBoxer.imagesPath;

public class AlefbaPicsList {
    //used to make alefba pics
    public static List<AlefbaPic> alefbaPicList() {
        List<AlefbaPic> picList = new ArrayList<>();

        AlefbaPic alefbaPic = new AlefbaPic();
        alefbaPic.setMedia(imagesPath + "alefba1.jpg");
        alefbaPic.setCurrectLetters("ب-ق-ر");
        alefbaPic.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic);

        // Pic #2
        AlefbaPic alefbaPic2 = new AlefbaPic();
        alefbaPic2.setMedia(imagesPath + "alefba2.jpg");
        alefbaPic2.setCurrectLetters("ب-ق-ر");
        alefbaPic2.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic2);

        // Pic #3
        AlefbaPic alefbaPic3 = new AlefbaPic();
        alefbaPic3.setMedia(imagesPath + "alefba3.jpg");
        alefbaPic3.setCurrectLetters("ب-ق-ر");
        alefbaPic3.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic3);

        // Pic #2
        AlefbaPic alefbaPic4 = new AlefbaPic();
        alefbaPic4.setMedia(imagesPath + "alefba4.jpg");
        alefbaPic4.setCurrectLetters("ب-ق-ر");
        alefbaPic4.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic4);

        // Pic #2
        AlefbaPic alefbaPic5 = new AlefbaPic();
        alefbaPic5.setMedia(imagesPath + "alefba5.jpg");
        alefbaPic5.setCurrectLetters("ب-ق-ر");
        alefbaPic5.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic5);

        // Pic #2
        AlefbaPic alefbaPic6 = new AlefbaPic();
        alefbaPic6.setMedia(imagesPath + "alefba6.jpg");
        alefbaPic6.setCurrectLetters("ب-ق-ر");
        alefbaPic6.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic6);

        // Pic #2
        AlefbaPic alefbaPic7 = new AlefbaPic();
        alefbaPic7.setMedia(imagesPath + "alefba7.jpg");
        alefbaPic7.setCurrectLetters("ب-ق-ر");
        alefbaPic7.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic7);

        alefbaPic = new AlefbaPic();
        alefbaPic.setMedia(imagesPath + "alefba8.jpg");
        alefbaPic.setCurrectLetters("ب-ق-ر");
        alefbaPic.setWrongLetters("ف-ز-ظ-ص-ض");
        picList.add(alefbaPic);

        return picList;
    }
}
