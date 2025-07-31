package com.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchFromWeb {

    public static List<String[]> getWebResults(String keyword) throws Exception {
        List<String[]> results = new ArrayList<>();

        String apiUrl = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/WebSearchAPI?q=" + 
                         keyword + "&pageNumber=1&pageSize=10&autoCorrect=true";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        conn.setRequestProperty("X-RapidAPI-Key", "db9c04cad3msh1cb395dac7c823ep13a4b5jsna64a29c67ad9");
        conn.setRequestProperty("X-RapidAPI-Host", "contextualwebsearch-websearch-v1.p.rapidapi.com");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        JSONArray valueArray = json.getJSONArray("value");

        for (int i = 0; i < valueArray.length(); i++) {
            JSONObject obj = valueArray.getJSONObject(i);
            String title = obj.getString("title");
            String urlLink = obj.getString("url");
            String description = obj.getString("description");
            results.add(new String[] { title, urlLink, description });
        }

        return results;
    }
}
