package com.search;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchAPI {

    private static final String API_KEY = "AIzaSyADZ98hKVRtFFL6ut0qdr_6_YzfDQyAsI4";
    private static final String CX = "2341d98d8d01f41c8";

    public List<String[]> getResultsFromAPI(String keyword) throws IOException, InterruptedException {
        List<String[]> apiResults = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        String encodedKeyword = URLEncoder.encode(keyword, java.nio.charset.StandardCharsets.UTF_8);

        String url = String.format("https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s",
                API_KEY, CX, encodedKeyword);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject json = new JSONObject(response.body());
        if (!json.has("items")) return apiResults;

        JSONArray items = json.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String title = item.optString("title");
            String link = item.optString("link");
            String snippet = item.optString("snippet");

            // Default empty thumbnail
            String thumbnail = "";

            // Check for thumbnail in pagemap
            if (item.has("pagemap")) {
                JSONObject pagemap = item.getJSONObject("pagemap");
                if (pagemap.has("cse_image")) {
                    JSONArray cseImages = pagemap.getJSONArray("cse_image");
                    if (cseImages.length() > 0) {
                        JSONObject imageObj = cseImages.getJSONObject(0);
                        thumbnail = imageObj.optString("src", "");
                    }
                }
            }

            // Now returning 4 fields: title, link, snippet, thumbnail
            apiResults.add(new String[]{title, link, snippet, thumbnail});
        }

        return apiResults;
    }
}
