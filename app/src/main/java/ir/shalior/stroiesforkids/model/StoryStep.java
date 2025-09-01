package ir.shalior.stroiesforkids.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class StoryStep {
    @Id
    public long id;
    public long storyId;
    public ToOne<Story> story;
    private int position;
    private String media;
    private String voice;
    private String stopageTimes;

    public StoryStep(long id, long storyId, int position, String media, String voice) {
        this.id = id;
        this.storyId = storyId;
        this.story.setTargetId(storyId);
        this.position = position;
        this.media = media;
        this.voice = voice;
    }

    public StoryStep() {

    }

    public StoryStep(long storyId, int position, String media, String voice) {
        this.storyId = storyId;
        this.story.setTargetId(storyId);
        this.position = position;
        this.media = media;
        this.voice = voice;
    }

    public StoryStep(long storyId, int position, String media, String voice, String stopageTimes) {
        this.storyId = storyId;
        this.story.setTargetId(storyId);
        this.position = position;
        this.media = media;
        this.voice = voice;
        this.stopageTimes = stopageTimes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public ToOne<Story> getStory() {
        return story;
    }

    public void setStory(ToOne<Story> story) {
        this.story = story;
    }

    public String getStopageTimes() {
        return stopageTimes;
    }

    public void setStopageTimes(String stopageTimes) {
        this.stopageTimes = stopageTimes;
    }

}