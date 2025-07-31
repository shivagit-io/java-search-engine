package com.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    private static final String API_HOST = "bing-web-search1.p.rapidapi.com";
    private static final String API_KEY = "db9c04cad3msh1cb395dac7c823ep13a4b5jsna64a29c67ad9";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    	String keyword = request.getParameter("keyword");
        String pageParam = request.getParameter("page");

        int page = 1;
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int limit = 5;
        int offset = (page - 1) * limit;

        SearchDao dao = new SearchDao();
        List<String[]> resultList = null;
        int totalResults = 0;

        try {
            // 1. Try from DB
            resultList = dao.getSearchResults(keyword, offset, limit);
            totalResults = dao.getTotalResults(keyword);

            // 2. If DB is empty, fallback to web API
            if (resultList == null || resultList.isEmpty()) {
                SearchAPI searchAPI = new SearchAPI();
                resultList = searchAPI.getResultsFromAPI(keyword);
                totalResults = resultList != null ? resultList.size() : 0;
            }

            // 3. If still no results, show error
            if (resultList == null || resultList.isEmpty()) {
                request.setAttribute("error", "No results found for: " + keyword);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            int totalPages = (int) Math.ceil((double) totalResults / limit);

            // 4. Set attributes for frontend
            request.setAttribute("results", resultList);
            request.setAttribute("keyword", keyword);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("results.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
