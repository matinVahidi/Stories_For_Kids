package ir.shalior.stroiesforkids.model.alefbagame;

import org.androidannotations.annotations.EBean;

import java.util.List;

import io.objectbox.Box;
import ir.shalior.stroiesforkids.model.myobjectbox.ObjectBox;

@EBean
public class AlefbaBoxer {
    public static String imagesPath = "file:///android_asset/alefba_img/";
    private Box<AlefbaPic> alefbaPicBox;

    public AlefbaBoxer() {
        this.alefbaPicBox = ObjectBox.get().boxFor(AlefbaPic.class);
        savePicsToBox(AlefbaPicsList.alefbaPicList());
    }

    public Box<AlefbaPic> getAlefbaPicBox() {
        return alefbaPicBox;
    }

    public void savePicsToBox(List<AlefbaPic> picList) {
        if (this.alefbaPicBox.count() > 0) {
            return;
        }

        alefbaPicBox.put(picList);
    }
}
