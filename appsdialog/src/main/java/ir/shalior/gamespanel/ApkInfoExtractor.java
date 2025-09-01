package ir.shalior.gamespanel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import ir.shalior.stroiesforkids.R;

public class ApkInfoExtractor {

    Context context1;

    public ApkInfoExtractor(Context context2) {

        context1 = context2;
    }

    /**
     * Launch the application.
     *
     * @param packageName The name of the package.
     */
    public static void launchApp(final String packageName, Context context) {
        if (isSpace(packageName)) return;
        Intent launchAppIntent = getLaunchAppIntent(packageName, true, context);
        if (launchAppIntent == null) {
            Log.e("AppUtils", "Didn't exist launcher activity.");
            return;
        }
        context.startActivity(launchAppIntent);
    }

    private static Intent getLaunchAppIntent(final String packageName, Context context) {
        return getLaunchAppIntent(packageName, false, context);
    }

    private static Intent getLaunchAppIntent(final String packageName, final boolean isNewTask, Context context) {
        String launcherActivity = getLauncherActivity(packageName, context);
        if (!launcherActivity.isEmpty()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, launcherActivity);
            intent.setComponent(cn);
            return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
        }
        return null;
    }

    private static String getLauncherActivity(@NonNull final String pkg, Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        int size = info.size();
        if (size == 0) return "";
        for (int i = 0; i < size; i++) {
            ResolveInfo ri = info.get(i);
            if (ri.activityInfo.processName.equals(pkg)) {
                return ri.activityInfo.name;
            }
        }
        return info.get(0).activityInfo.name;
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public List<String> GetAllInstalledApkInfo() {

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);

        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if (!isSystemPackage(resolveInfo)) {

                ApkPackageName.add(activityInfo.applicationInfo.packageName);
            }
        }

        return ApkPackageName;

    }

    public boolean isSystemPackage(ResolveInfo resolveInfo) {

        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPackageName(String ApkTempPackageName) {

        Drawable drawable;

        try {
            drawable = context1.getPackageManager().getApplicationIcon(ApkTempPackageName);
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

            drawable = ContextCompat.getDrawable(context1, R.drawable.ic_launcher_background);
        }
        return drawable;
    }

    public String GetAppName(String ApkPackageName) {

        String Name = "";

        ApplicationInfo applicationInfo;

        PackageManager packageManager = context1.getPackageManager();

        try {

            applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);

            if (applicationInfo != null) {

                Name = (String) packageManager.getApplicationLabel(applicationInfo);
            }

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return Name;
    }

}
