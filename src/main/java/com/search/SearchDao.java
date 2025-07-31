package com.search;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchDao {
	public List<String[]> getSearchResults(String keyword, int offset, int limit) throws SQLException{
		List<String[]> results = new ArrayList<>();
	    try (Connection con = DBUtil.getConnection()) {
	        String sql = "SELECT title, url, description FROM web_resources " +
	                     "WHERE LOWER(description) LIKE ? " +
	                     "ORDER BY (LENGTH(description) - LENGTH(REPLACE(LOWER(description), ?, ''))) DESC " +
	                     "LIMIT ? OFFSET ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        String likeKeyword = "%" + keyword.toLowerCase() + "%";
	        ps.setString(1, likeKeyword);
	        ps.setString(2, keyword.toLowerCase());
	        ps.setInt(3, limit);
	        ps.setInt(4, offset);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String[] row = {
	                rs.getString("title"),
	                rs.getString("url"),
	                rs.getString("description")
	            };
	            results.add(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return results;
	}
	 // Save searched keyword to history table
	public void saveSearchKeyword(String keyword) {
        try (Connection con = DBUtil.getConnection()) {
            String sql = "INSERT INTO search_history (keyword, searched_at) VALUES (?, NOW())";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, keyword);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get last 5 searched keywords
	public List<String> getRecentSearches() {
        List<String> recent = new ArrayList<>();
        try (Connection con = DBUtil.getConnection()) {
            String sql = "SELECT keyword FROM search_history ORDER BY searched_at DESC LIMIT 5";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                recent.add(rs.getString("keyword"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recent;
    }
	public int getTotalResults(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
