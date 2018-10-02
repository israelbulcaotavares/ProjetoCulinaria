package com.tavares.projetoculinaria.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.tavares.projetoculinaria.R;

@SuppressWarnings("WeakerAccess")
public class ReceiraWidgetService extends IntentService {

    @SuppressWarnings("WeakerAccess")
    public static final String UPDATE_WIDGET_ACTION = "com.tavares.projetoculinaria.widget_update_widget_action";

    public ReceiraWidgetService() {
        super("ReceiraWidgetService");
    }

    public static void startActionUpdateWidget(Context context) {
        Intent intent = new Intent(context, ReceiraWidgetService.class);
        intent.setAction(UPDATE_WIDGET_ACTION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            String action = intent.getAction();
            if (UPDATE_WIDGET_ACTION.equals(action)) {
                handleUpdateWidgetAction();
            }
        }
    }

    private void handleUpdateWidgetAction() {
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(this);
        int widgetsIds[] = widgetManager.getAppWidgetIds(new ComponentName(this, ReceitaAppWidget.class));
        widgetManager.notifyAppWidgetViewDataChanged(widgetsIds, R.id.widget_grid_view);
        ReceitaAppWidget.updateCakesAppWidget(this, widgetManager, widgetsIds);
    }
}
