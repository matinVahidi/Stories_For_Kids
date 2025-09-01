package ir.shalior.stroiesforkids.model;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Story {

    @Id
    public long id;

    @Backlink(to = "story")
    public ToMany<StoryStep> steps;

    @Backlink(to = "story")
    public ToMany<Question> questions;
    String name;
    int parts;
    int questionsCount;
    int difficulty;
    int stage;
    int isLocked;

    String stopageTimes;

    public Story(String name, int parts, int questionsCount, int difficulty, int stage, String stopageTimes, int isLocked) {
        this.name = name;
        this.parts = parts;
        this.questionsCount = questionsCount;
        this.difficulty = difficulty;
        this.stage = stage;
        this.stopageTimes = stopageTimes;
        this.isLocked = isLocked;
    }

    public Story() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getStopageTimes() {
        return stopageTimes;
    }

    public void setStopageTimes(String stopageTimes) {
        this.stopageTimes = stopageTimes;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

}
