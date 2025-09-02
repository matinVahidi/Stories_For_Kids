package ir.shalior.stroiesforkids.activities.games;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.gamespanel.ApkInfoExtractor;
import ir.shalior.gamespanel.AppsSelectDialog;
import ir.shalior.stroiesforkids.games.tapouniom.GameTapActivity;
import ir.shalior.stroiesforkids.model.UserBoxer;
import ir.shalior.stroiesforkids.databinding.ActivityGamesBinding; // ViewBinding import
import ir.shalior.stroiesforkids.games.alefba.activities.main.AlefbaMainActivity; // Changed from AlefbaMainActivity_

import static ir.shalior.gamespanel.AppsSelectDialog.NOT_SELECTED;

public class GamesActivity extends AppCompatActivity {

    // Declare the binding object
    private ActivityGamesBinding binding;

    private UserBoxer userBoxer;
    private SharedPreferences customPrefrences;
    private AppsSelectDialog appsSelectDialog;
    private ApkInfoExtractor appsUtil;
    private String mCustomGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout and get a binding instance
        binding = ActivityGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the dependencies (replaces @Bean)
        // Note: You would typically use a Dependency Injection framework like Dagger or Koin for this.
        userBoxer = new UserBoxer();

        initPreferences();
        appsSelectDialog = new AppsSelectDialog();
        appsUtil = new ApkInfoExtractor(this);

        // Bind data and listeners (replaces @ViewById and @AfterViews)
        binding.btnCustomGameStart.setImageDrawable(appsUtil.getAppIconByPackageName(getAppPackage()));
        binding.txtStars.setText(userBoxer.getStartsCount() + "");

        // Set click listeners (replaces @Click annotations)
        binding.btnTapounimStart.setOnClickListener(v -> runTapGameClicked());
        binding.btnAlefbaStart.setOnClickListener(v -> runAlefbaClicked());
        binding.btnCustomGameStart.setOnClickListener(v -> runChosenGameClicked());
        binding.btnCardChosseGame.setOnClickListener(v -> choseGameClicked());
    }

    public void initPreferences() {
        customPrefrences = getSharedPreferences("custom_game", MODE_PRIVATE);
        mCustomGame = customPrefrences.getString("package", "0");
    }

    private void showDialogaSubmit() {
        Toast.makeText(this, "یک بازی انتخاب کنید", Toast.LENGTH_SHORT).show();
        appsSelectDialog.showAppsDialog(this);
        appsSelectDialog.setSubmitDialogClickListener(pachageSelected -> {
            if (pachageSelected.equals(NOT_SELECTED)) {
                return;
            }
            customPrefrences.edit().putString("package", pachageSelected).commit();
            binding.btnCustomGameStart.setImageDrawable(appsUtil.getAppIconByPackageName(pachageSelected));
        });
    }

    public void runTapGameClicked() {
        final Intent gameTapActivity = new Intent(this, GameTapActivity.class);
        startActivity(gameTapActivity);
    }

    public void runAlefbaClicked() {
        final Intent gameAlefbaActivity = new Intent(this, AlefbaMainActivity.class);
        startActivity(gameAlefbaActivity);
    }

    public String getAppPackage() {
        return customPrefrences.getString("package", "0");
    }

    public void runChosenGameClicked() {
        if (getAppPackage().equals("0")) {
            showDialogaSubmit();
            return;
        }
        ApkInfoExtractor.launchApp(getAppPackage(), this);
    }

    public void choseGameClicked() {
        showDialogaSubmit();
    }

    //apply Default font to the context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}