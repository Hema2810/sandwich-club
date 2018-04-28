package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        // initialize a new Sandwich object to store the parsed data
        Sandwich detailSandwich = new Sandwich();

        try {
            // Converting the json string into an JSONObject to parse
            JSONObject sandwichInfo = new JSONObject(json);
            //Parse the JSONObject to get the information about the sandwich
            JSONObject name = sandwichInfo.getJSONObject("name");
            detailSandwich.setMainName(name.getString("mainName"));
            //alsoKnownAs can have multiple entries, hence an array
            JSONArray alsoKnownAsJasonArray = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsArray = new ArrayList<String>();
            if (alsoKnownAsJasonArray != null) {

                for (int i = 0; i < alsoKnownAsJasonArray.length(); i++) {
                    alsoKnownAsArray.add(alsoKnownAsJasonArray.getString(i));
                }
            }
            detailSandwich.setAlsoKnownAs(alsoKnownAsArray);
            detailSandwich.setPlaceOfOrigin(sandwichInfo.getString("placeOfOrigin"));
            detailSandwich.setDescription(sandwichInfo.getString("description"));
            detailSandwich.setImage(sandwichInfo.getString("image"));
            JSONArray ingredientsJsonArray = sandwichInfo.getJSONArray("ingredients");
            List<String> ingredientsArray = new ArrayList<String>();
            if (!ingredientsJsonArray.equals(null)) {

                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    ingredientsArray.add(ingredientsJsonArray.getString(i));
                }

            }
            detailSandwich.setIngredients(ingredientsArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //return the Sandwich object populated with data from the parsed JSON
        return detailSandwich;
    }
}
