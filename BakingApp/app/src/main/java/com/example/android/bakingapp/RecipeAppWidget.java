package com.example.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidget extends AppWidgetProvider {

    public static final String ACTION_TEXT_CHANGED = "com.example.android.bakingapp.TEXT_CHANGED";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);

        //create intent to launch Main Activity when widget is clicked
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        //views.setOnClickPendingIntent(R.id.img_recipeappwidget_widgetbackground,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(ACTION_TEXT_CHANGED)) {
            // handle intent here
            String s = intent.getStringExtra("NewString");
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.recipe_app_widget);
            views.setTextViewText(R.id.txt_recipeappwidget_ingrediants, s);
            AppWidgetManager.getInstance(context).updateAppWidget(
                    new ComponentName(context, RecipeAppWidget.class),views);
        }
    }
}

