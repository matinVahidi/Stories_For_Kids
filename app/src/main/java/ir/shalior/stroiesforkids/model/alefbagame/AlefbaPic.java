package ir.shalior.stroiesforkids.model.alefbagame;

import android.net.Uri;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import ir.shalior.stroiesforkids.model.HasPicture;
import ir.shalior.stroiesforkids.util.Helpers;

@Entity
public class AlefbaPic implements HasPicture {
    @Id
    public long id;
    private boolean isDone;
    //expected to be in this format: "file:///android_asset/img/flower.jpg"
    private String media;
    // seperated with '-'
    private String currectLetters;
    private String wrongLetters;

    public AlefbaPic() {

    }

    //all prop cons for objectbox's performance boost
    public AlefbaPic(long id, boolean isDone, String media, String currectLetters, String wrongLetters) {
        this.id = id;
        this.isDone = isDone;
        this.media = media;
        this.currectLetters = currectLetters;
        this.wrongLetters = wrongLetters;
    }

    /**
     * on glide it should be used like:
     * Glide.with(context).asBitmap().load(this.getMediaUri).into(imageView);
     * @return Uri
     */
    public Uri getMediaUri() {
        if (this.media != null)
            return Uri.parse(this.media);

        return null;
    }

    @Override
    public String getBasePath() {
        return AlefbaBoxer.imagesPath;
    }

    public String getWrongLetters() {
        return wrongLetters;
    }

    public List<String> getWrongLettersList() {
        return Helpers.shaliorStringsToList(wrongLetters);
    }

    public void setWrongLetters(String wrongLetters) {
        this.wrongLetters = wrongLetters;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        if (media.startsWith(AlefbaBoxer.imagesPath)) {
            this.media = media;
        } else {
            throw new RuntimeException();
        }
    }

    public String getCurrectLetters() {
        return currectLetters;
    }

    public List<String> getCurrectLettersList() {
        return Helpers.shaliorStringsToList(currectLetters);
    }

    public void setCurrectLetters(String currectLetters) {
        this.currectLetters = currectLetters;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
