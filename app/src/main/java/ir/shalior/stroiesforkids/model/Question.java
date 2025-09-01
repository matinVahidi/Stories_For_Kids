package ir.shalior.stroiesforkids.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class Question {
    @Id
    public long id;
    public ToOne<Story> story;
    public long storyId;
    public String title;
    public String options;
    public int currectOption;

    public String voice;

    public Question(long storyId, String title, String options, int currectOption, String voice) {
        this.storyId = storyId;
        this.title = title;
        this.options = options;
        this.voice = voice;
        this.currectOption = currectOption;
    }

    public Question() {
    }

    public Question(long storyId) {

    }

}
