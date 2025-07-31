package com.search;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

@WebServlet("/LiveSearchServlet")  // This maps /search to this servlet
public class LiveSearchServlet extends HttpServlet {

    private static final String API_HOST = "bing-web-search1.p.rapidapi.com";
    private static final String API_KEY = "db9c04cad3msh1cb395dac7c823ep13a4b5jsna64a29c67ad9"; // replace with your actual key

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        if (query == null || query.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing search query");
            return;
        }

        @SuppressWarnings("deprecation")
		URL url = new URL("https://bing-web-search1.p.rapidapi.com/search?q=" + URLEncoder.encode(query, "UTF-8") + "&mkt=en-us");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-RapidAPI-Key", API_KEY);
        conn.setRequestProperty("X-RapidAPI-Host", API_HOST);

        int responseCode = conn.getResponseCode();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner sc = new Scanner(conn.getInputStream());
            while (sc.hasNext()) {
                out.println(sc.nextLine());
            }
            sc.close();
        } else {
            out.println("{\"error\": \"Search failed with code: " + responseCode + "\"}");
        }

        out.close();
    }
}
