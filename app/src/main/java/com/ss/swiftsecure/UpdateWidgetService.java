package com.ss.swiftsecure;
import java.util.Random;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends IntentService {
    private static final String LOG = "com.ss.swiftsecure";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateWidgetService(String name) {
        super(name);
    }
    public static void startAlertSecurity(Context context){
    Intent intent=new Intent(context,UpdateWidgetService.class);
    context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }


}