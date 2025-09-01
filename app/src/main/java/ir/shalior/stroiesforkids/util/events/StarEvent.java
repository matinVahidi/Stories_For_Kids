package ir.shalior.stroiesforkids.util.events;

public class StarEvent {
    private int starChange;
    private boolean isPositive;

    public StarEvent(int starCount, boolean isPositive) {
        this.starChange = starCount;
        this.isPositive = isPositive;
    }

    public int getStarChange() {
        return starChange;
    }

    public void setStarChange(int starChange) {
        this.starChange = starChange;
    }


    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

}
