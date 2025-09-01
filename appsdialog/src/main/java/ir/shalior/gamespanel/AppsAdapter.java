package ir.shalior.gamespanel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.shalior.stroiesforkids.R;

public class AppsAdapter extends BaseAdapter {

    List<String> allInstalledApps;
    ApkInfoExtractor apkInfoExtractor;

    public AppsAdapter(List<String> allInstalledApps, Context context) {
        this.allInstalledApps = allInstalledApps;
        apkInfoExtractor = new ApkInfoExtractor(context);
    }

    @Override
    public int getCount() {
        return allInstalledApps.size();
    }

    @Override
    public Object getItem(int i) {
        return allInstalledApps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cardView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_app, viewGroup, false);

        String ApplicationPackageName = allInstalledApps.get(i);
        String ApplicationLabelName = apkInfoExtractor.GetAppName(ApplicationPackageName);
        Drawable drawable = apkInfoExtractor.getAppIconByPackageName(ApplicationPackageName);

        TextView textView = cardView.findViewById(R.id.Apk_Name);
        textView.setText(ApplicationLabelName);

        ImageView imageView = cardView.findViewById(R.id.appImage);
        imageView.setImageDrawable(drawable);

        return cardView;
    }
}
