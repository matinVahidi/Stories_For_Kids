package ir.shalior.stroiesforkids.activities.story.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import ir.shalior.stroiesforkids.R;
import ir.shalior.stroiesforkids.activities.story.StoryActivity;

public class StageTwoPausedDialog extends DialogFragment {


    StoryActivity storyActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        storyActivity = (StoryActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_story_stage_two_paused, null);

        Button btn = dialogView.findViewById(R.id.pausedDialogResumeStory);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyActivity.dialogResumeButtonclicked();
            }
        });

        Button nexStageBtn = dialogView.findViewById(R.id.dialogButtonNextStage);
        nexStageBtn.setOnClickListener(v -> {
            storyActivity.dialogNexStageButtonClicked();
        });

        ImageView imageView = dialogView.findViewById(R.id.imageView3);
        Glide.with(this)
                .load(R.drawable.placeholder)
                .into(imageView);
        builder.setView(dialogView);

        return builder.create();
    }

}