package com.tavares.projetoculinaria.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ReceitaContract {
    public static final String AUTHORITY = "com.tavares.projetoculinaria";
    public static final String RECEITA_PATH = "ReceitasIngredients";
    private static final Uri BASE_CONTENT = Uri.parse("content://" + AUTHORITY);

    private ReceitaContract() {
        throw new AssertionError("not available!");
    }

    public static class ReceitaEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT.buildUpon()
                .appendPath(RECEITA_PATH)
                .build();

        public static final String TABLE_NAME = "Ingredients";
        public static final String RECEITA_NAME_COLUMN = "_name";
        public static final String INGREDIENTS_COLUMN = "_ingredients";
        public static final String TIME_STAMP_COLUMN = "_time";
    }
}
