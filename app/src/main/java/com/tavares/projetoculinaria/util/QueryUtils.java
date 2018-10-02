package com.tavares.projetoculinaria.util;

import android.text.TextUtils;
import android.util.Log;
import com.tavares.projetoculinaria.constants.Constantes;
import com.tavares.projetoculinaria.model.Ingredientes;
import com.tavares.projetoculinaria.model.Receitas;
import com.tavares.projetoculinaria.model.Steps;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    private static List<Receitas> extractFeatureFromJson(String newsJSON) {

        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        List<Receitas> receitasList = new ArrayList<>();

        try {

        JSONArray receitasArray = new JSONArray(newsJSON);

            for (int i = 0; i < receitasArray.length(); i++) {
                JSONObject currentReceitas = receitasArray.getJSONObject(i);

                   int id = currentReceitas.getInt("id");
                String name = currentReceitas.getString("name");
                String servings = currentReceitas.getString("servings");
                String image = currentReceitas.getString("image");


        JSONArray ingredientesArray= currentReceitas.getJSONArray("ingredients");
        ArrayList<Ingredientes> ingredientesArrayList = new ArrayList<>();

                for (int j = 0; j < ingredientesArray.length(); j++) {
                    JSONObject currentIngredientes = ingredientesArray.getJSONObject(j);
                    String quantity = currentIngredientes.getString("quantity");
                    String measure = currentIngredientes.getString("measure");
                    String ingredient = currentIngredientes.getString("ingredient");

                    Ingredientes ingredients = new Ingredientes(id, quantity, measure, ingredient);
                    ingredientesArrayList.add(ingredients);

                }

                //Steps Stream
                JSONArray stepsArray= currentReceitas.getJSONArray("steps");
                ArrayList<Steps> stepsArrayList=new ArrayList<>();

                for (int k = 0; k < stepsArray.length(); k++) {
                    JSONObject currentStep = stepsArray.getJSONObject(k);
                    int stepId = currentStep.getInt("id");
                    String shortDescription = currentStep.getString("shortDescription");
                    String description = currentStep.getString("description");
                    String videoURL = currentStep.getString("videoURL");
                    String thumbnailURL = currentStep.getString("thumbnailURL");

                    Steps Step = new Steps(stepId, shortDescription, description, videoURL, thumbnailURL, name);
                    stepsArrayList.add(Step);
                }

                Receitas receitas = new Receitas(id, name, servings, image, ingredientesArrayList, stepsArrayList);
                receitasList.add(receitas);
            }

        } catch (JSONException e) {
            Log.e(Constantes.QUERY_UTILS, Constantes.PROBLEM_PARSING_JSON_RESULTS, e);
        }

        return receitasList;
    }
    public static List<Receitas> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, Constantes.ERROR_CLOSING_STREAM, e);
        }

        return extractFeatureFromJson(jsonResponse);
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, Constantes.ERROR_CREATING_URL, e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(Constantes.DEZMILI /* milisegundos */);
            urlConnection.setConnectTimeout(Constantes.QUINZEMILI /* milisegundos */);
            urlConnection.setRequestMethod(Constantes.GET);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == Constantes.GET_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, Constantes.ERROR_RESPONSE_CODE + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, Constantes.PROBLEM_RETRIEVING_JSON_RESULTS, e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName(Constantes.UTF_8));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}