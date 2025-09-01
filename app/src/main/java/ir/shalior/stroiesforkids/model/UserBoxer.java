package ir.shalior.stroiesforkids.model;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.objectbox.Box;
import ir.shalior.stroiesforkids.model.myobjectbox.ObjectBox;
import ir.shalior.stroiesforkids.util.events.StarEvent;

@EBean
public class UserBoxer {
    private Box<User> userBox;
    private User user;

    public UserBoxer() {
        this.userBox = ObjectBox.get().boxFor(User.class);
        this.user = ObjectBox.get().boxFor(User.class).get(1);
        EventBus.getDefault().register(this);
    }

    public void release() {
        EventBus.getDefault().unregister(this);
    }

    public int getStartsCount() {
        return userBox.get(1).starsCount;
    }

    /**
     * @param starts
     * @return new starts count
     */
    public int setStartsCount(int starts) {
        user.starsCount = starts;
        userBox.put(user);

        return getStartsCount();
    }

    public int addStarts(int stars) {
        user.starsCount += stars;
        userBox.put(user);
        return getStartsCount();
    }

    public int decreaseStars(int stars) {
        user.starsCount = getStartsCount() - stars;
        userBox.put(user);
        return getStartsCount();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onStarEvent(StarEvent starEvent) {
        if (starEvent.isPositive()) addStarts(starEvent.getStarChange());
        if (!starEvent.isPositive()) decreaseStars(starEvent.getStarChange());
    }

    //getters & setters
    public Box<User> getUserBox() {
        return userBox;
    }

    public User getUser() {
        return user;
    }

}
