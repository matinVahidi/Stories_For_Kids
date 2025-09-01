package ir.shalior.stroiesforkids.model;

import android.net.Uri;

public interface HasPicture {
    Uri getMediaUri();

    String getBasePath();

    String getMedia();

    void setMedia(String media);
}
