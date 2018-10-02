package com.tavares.projetoculinaria.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.activity.ReceitaActivity;


@SuppressWarnings("WeakerAccess")
public class ReceitaAppWidget extends AppWidgetProvider {

    @SuppressWarnings("WeakerAccess")
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = getIngredientsGridView(context);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static RemoteViews getIngredientsGridView(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list_view);
        Intent gridViewServiceIntent = new Intent(context, ReceitaRemoteViewsService.class);
        remoteViews.setRemoteAdapter(R.id.widget_grid_view, gridViewServiceIntent);

        Intent appIntent = new Intent(context, ReceitaActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setPendingIntentTemplate(R.id.widget_grid_view, pendingIntent);

        remoteViews.setEmptyView(R.id.widget_grid_view, R.id.empty_view);
        return remoteViews;

    }

    public static void updateCakesAppWidget(Context context, AppWidgetManager manager, int[] widgetsIds) {
        for (int appWidgetId : widgetsIds) {
            updateAppWidget(context, manager, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ReceiraWidgetService.startActionUpdateWidget(context);
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

