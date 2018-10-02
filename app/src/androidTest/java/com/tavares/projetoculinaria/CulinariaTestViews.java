package com.tavares.projetoculinaria;

 import android.support.test.runner.AndroidJUnit4;

 import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
 import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
 import static android.support.test.espresso.matcher.ViewMatchers.withId;
 import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@android.support.test.filters.LargeTest

public class CulinariaTestViews {

    @Test
    public void clickListViewItem() {

        //todo: Testando RecyclerViews
        onView(allOf(withId(R.id.recycler_view_receitas),
                isDisplayed()));
        onView(allOf(withId(R.id.recycler_view_ingredients),
                isDisplayed()));
        onView(allOf(withId(R.id.recycler_view_steps),
                isDisplayed()));

        //todo: Testando GridView da Widget
        onView(allOf(withId(R.id.widget_grid_view),
                isDisplayed()));


    }

}
