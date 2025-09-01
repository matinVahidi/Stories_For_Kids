package ir.shalior.stroiesforkids.activities.questions.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ir.shalior.stroiesforkids.R;

public class StarsDialog extends DialogFragment {

    private int stars;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_star_get, null);

        TextView txtGotStars = dialogView.findViewById(R.id.txtGotStars);
        txtGotStars.setText(stars + " ستاره گرفتی");

        builder.setView(dialogView);
        return builder.create();
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}