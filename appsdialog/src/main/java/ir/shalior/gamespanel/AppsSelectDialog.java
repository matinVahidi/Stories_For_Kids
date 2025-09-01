package ir.shalior.gamespanel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ir.shalior.stroiesforkids.R;

public class AppsSelectDialog {
    public static String NOT_SELECTED = "-11";
    private SubmitDialogClickListener submitDialogClickListener;

    public void showAppsDialog(Context context) {
        AppCompatActivity activity = (AppCompatActivity) context;
        View testView = activity.getLayoutInflater().inflate(R.layout.list_app, null, false);

        ApkInfoExtractor extractor = new ApkInfoExtractor(context);
        final List<String> installList = extractor.GetAllInstalledApkInfo();

        final ListView listView = testView.findViewById(R.id.listView);
        listView.setAdapter(new AppsAdapter(installList, context));

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        AlertDialog alertDialog = dialogBuilder.setTitle("انتخاب بازی")
                .setMessage("بازی مورد نظر خود را اننتخاب کنید")
                .setNeutralButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedPosition = listView.getCheckedItemPosition();
                        String selectedApp = NOT_SELECTED;
                        if (selectedPosition != -1) selectedApp = installList.get(selectedPosition);
                        //Log.d(TAG, "onClick: " + selectedApp);
                        if (submitDialogClickListener != null)
                            submitDialogClickListener.OnSubmitDialogClicked(selectedApp);
                    }
                })
                .setView(testView)
                .create();

        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void setSubmitDialogClickListener(SubmitDialogClickListener submitDialogClickListener) {
        this.submitDialogClickListener = submitDialogClickListener;
    }

    public interface SubmitDialogClickListener {
        void OnSubmitDialogClicked(String pachageSelected);
    }
}
