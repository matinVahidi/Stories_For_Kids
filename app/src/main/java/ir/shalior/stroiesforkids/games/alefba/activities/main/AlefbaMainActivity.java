package ir.shalior.stroiesforkids.games.alefba.activities.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.games.alefba.activities.game.AlefbaGameActivity_;
import ir.shalior.stroiesforkids.model.alefbagame.AlefbaBoxer;
import ir.shalior.stroiesforkids.model.alefbagame.AlefbaPic;

@SuppressLint("Registered")
@EActivity(R.layout.activity_alefba_main)
public class AlefbaMainActivity extends AppCompatActivity {

    @ViewById(R.id.tableLayout)
    TableLayout tableLayout;

    @Bean
    AlefbaBoxer alefbaBoxer;

    List<AlefbaPic> alefbaPics;

    @Click(R.id.btnStartAlefba)
    public void runAlefbaGame() {
        AlefbaGameActivity_.intent(this).start();
    }

    @AfterViews
    public void afterViews() {
        alefbaPics = alefbaBoxer.getAlefbaPicBox().getAll();
        List<MaterialButton> btnList = new ArrayList<>();
        for (AlefbaPic pic : alefbaPics
        ) {
            MaterialButton btn = new MaterialButton(this);
            btn.setText("مرحله " + pic.id);
            btn.setTextSize(20f);
            btn.setPadding(8, 8, 8, 8);
            btn.setOnClickListener(v -> {
                AlefbaGameActivity_.intent(this).extra("picId", pic.id).start();
            });
            tableLayout.addView(btn);
            btnList.add(btn);
        }
/*
        for (MaterialButton btn: btnList){
            tableLayout.addView(btn);
        }*/
    }

    //apply Default font to the context
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
