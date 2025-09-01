package ir.shalior.stroiesforkids.activities.questions;

public class AnswerEvent {
    long questionId;
    int chosenOption;
    boolean isCorrect;

    public AnswerEvent(long questionId, int chosenOption, boolean isCorrect) {
        this.questionId = questionId;
        this.chosenOption = chosenOption;
        this.isCorrect = isCorrect;
    }

    public long getQuestionId() {
        return questionId;
    }

    public int getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(int chosenOption) {
        this.chosenOption = chosenOption;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
