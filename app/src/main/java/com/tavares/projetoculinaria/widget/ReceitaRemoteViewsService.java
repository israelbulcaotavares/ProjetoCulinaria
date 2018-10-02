package com.tavares.projetoculinaria.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.tavares.projetoculinaria.R;
import com.tavares.projetoculinaria.data.ReceitaContract;

public class ReceitaRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridViewWidgetRemoteViewsFactory(this.getApplicationContext());
    }

    class GridViewWidgetRemoteViewsFactory implements RemoteViewsFactory {

        private Cursor mCursor;
        private final Context mContext;

        GridViewWidgetRemoteViewsFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            if (mCursor != null) mCursor.close();

            mCursor = getContentResolver().query(
                    ReceitaContract.ReceitaEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    ReceitaContract.ReceitaEntry.TIME_STAMP_COLUMN);
        }

        @Override
        public void onDestroy() {
            if (mCursor != null) {
                mCursor.close();
            }
        }

        @Override
        public int getCount() {
            if (mCursor == null) {
                return 0;
            } else {
                return mCursor.getCount();
            }
        }

        @Override
        public RemoteViews getViewAt(int i) {
            if (mCursor == null || mCursor.getCount() == 0) {
                return null;
            }

            mCursor.moveToPosition(i);
            int cakeNameIndex = mCursor.getColumnIndex(ReceitaContract.ReceitaEntry.RECEITA_NAME_COLUMN);
            int ingredientsIndex = mCursor.getColumnIndex(ReceitaContract.ReceitaEntry.INGREDIENTS_COLUMN);

            String cakeName = mCursor.getString(cakeNameIndex);
            String ingredients = mCursor.getString(ingredientsIndex);

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

            remoteViews.setTextViewText(R.id.widget_receita_name, cakeName);
            remoteViews.setTextViewText(R.id.widget_receita_ingredientes, ingredients);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
