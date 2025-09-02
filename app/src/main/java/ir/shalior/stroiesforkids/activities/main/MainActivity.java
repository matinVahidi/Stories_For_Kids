package ir.shalior.stroiesforkids.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;
import com.google.android.material.navigation.NavigationView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import io.objectbox.Box;
import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.games.GamesActivity;
import ir.shalior.stroiesforkids.activities.questions.QuestionsActivity;
import ir.shalior.stroiesforkids.activities.story.StoryActivity;
import ir.shalior.stroiesforkids.model.Story;
import ir.shalior.stroiesforkids.model.StoryBoxer;
import ir.shalior.stroiesforkids.model.StoryStep;
import ir.shalior.stroiesforkids.model.StoryStep_;
import ir.shalior.stroiesforkids.model.User;
import ir.shalior.stroiesforkids.model.UserBoxer;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;


@EActivity
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//todo: header with lvl choosing
    //todo: first Run wizard
    //tood
private static final int TIME_INTERVAL = 1500; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
String TAG = "dd123";
    List<Story> stories;
    StoriesAdapter adapter1;
    RecyclerView recyclerView;
    @Bean
    StoryBoxer storyBoxer;

    @Bean
    UserBoxer userBoxer;

    @ViewById(R.id.buttonLock)
    Button btnLocked;
    @ViewById(R.id.txtStarsCount)
    TextView txtStarsCount;

    @AfterInject
    public void doIt() {
        if (storyBoxer.getStoryBox().getAll().size() == 0) {
            storyBoxer.makeDummyStories();
        }
        if (userBoxer.getUser() == null) {
            User user = new User();
            user.starsCount = 1;
            userBoxer.getUserBox().put(user);
            //send user to first run activity
        }
        List<Story> list = storyBoxer.getStoryBox().getAll();
    }

    private void logOrders(Box<StoryStep> orderBox, Story story) {
        List<StoryStep> ordersQueried = orderBox.query().equal(StoryStep_.storyId, story.id).build().find();
        log("Customer " + story.id + " has " + ordersQueried.size() + " orders");
        for (StoryStep order : ordersQueried) {
            log("Order " + order.id + " related to customer " + order.story.getTargetId());
        }
        log("");
    }

    private void log(String message) {
        Log.d("ObjecgtBOx", message);
    }

    @Click(R.id.buttonLock)
    public void lockClicked() {
        stories = storyBoxer.getStoriesByLevel(1);
        adapter1.notifyItemRangeRemoved(0, 3);
        adapter1 = new StoriesAdapter(stories);
        recyclerView.swapAdapter(adapter1, false);
        adapter1.notifyItemRangeRemoved(0, 3);
        adapter1.notifyItemRangeInserted(0, 3);
    }

    @Click(R.id.buttonUnlocked)
    public void unlockClicked() {
        stories = storyBoxer.getStoriesByLevel(2);
        adapter1 = new StoriesAdapter(stories);
        recyclerView.swapAdapter(adapter1, false);
        adapter1.notifyItemRangeInserted(0, 3);
    }

    @Click(R.id.buttonAll)
    public void allClicked() {
        stories = storyBoxer.getStoryBox().getAll();
        adapter1 = new StoriesAdapter(stories);
        recyclerView.swapAdapter(adapter1, false);
        adapter1.notifyItemRangeInserted(0, 9);
    }

    @Click(R.id.btnToGamesActivity)
    public void toGamesActivityClicked() {
        Intent intent = new Intent(this, GamesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        txtStarsCount.setText(getString(R.string.main_act_start_count) + " " + userBoxer.getStartsCount());
        log(userBoxer.getStartsCount() + "= stars count");
        Log.d(TAG, "onCreate: test");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewStoriesMainActivity);
        final List<String> stringList = new ArrayList<>();
        stringList.add("داستان 1");
        stringList.add("داستان 2");
        stringList.add("داستان 3");

        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        stories = storyBoxer.getStoryBox().getAll();

        adapter1 = new StoriesAdapter(stories);
        adapter1.setHasStableIds(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter1);
        recyclerView.addOnScrollListener(new CenterScrollListener());
        recyclerView.setItemAnimator(new ScaleInAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(2000);
        recyclerView.getItemAnimator().setAddDuration(750);

/*        btnLocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stringList.remove(0);
                stringList.remove(0);
                stringList.remove(0);
                //recyclerView.setAdapter(null);

                //adapter1.notifyItemRangeRemoved(0 , 3);
                stringList.add("داستان 4");
                stringList.add("داستان 5");
                stringList.add("داستان 6");
                adapter1.notifyItemRangeInserted(0, 3);
                //adapter.set
                //recyclerView.
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                finish();
                return;
            } else {
                Toast.makeText(getBaseContext(), "برای خروج یکبار دیگر ضربه بزنید", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d(TAG, "start act clicked ");
            //TestActivity_.intent(this).start();
            Intent intent = new Intent(this, QuestionsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this , StoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //apply Default font to the context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
