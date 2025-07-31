<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String keyword = (String) request.getAttribute("keyword");
    List<String[]> results = (List<String[]>) request.getAttribute("results");
    int currentPage = (int) request.getAttribute("currentPage");
    int totalPages = (int) request.getAttribute("totalPages");

    if (keyword == null || results == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Results for "<%= keyword %>"</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body.dark-mode {
            background-color: #121212;
            color: #e0e0e0;
        }
        .dark-mode .card {
            background-color: #1e1e1e;
            border-color: #333;
        }
        .thumbnail {
            width: 90px;
            height: 65px;
            object-fit: cover;
            margin-right: 15px;
            border-radius: 8px;
        }
        .loader {
            border: 4px solid #f3f3f3;
            border-top: 4px solid #007bff;
            border-radius: 50%;
            width: 40px;
            height: 40px;
            animation: spin 1s linear infinite;
            margin: 20px auto;
        }
        @keyframes spin {
            to { transform: rotate(360deg); }
        }
        .result-title a {
            text-decoration: none;
            color: #007bff;
        }
        .result-title a:hover {
            text-decoration: underline;
        }
        .card:hover {
            transform: translateY(-2px);
            transition: transform 0.2s;
        }
        .toggle-switch {
            display: flex;
            align-items: center;
        }
        .grid-view {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
        }
    </style>
</head>
<body class="bg-light">
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h4><i class="fas fa-search"></i> Results for "<%= keyword %>"</h4>
        <div class="toggle-switch">
            <button class="btn btn-sm btn-dark me-2" onclick="toggleDarkMode()">🌓 Dark Mode</button>
            <a href="index.jsp" class="btn btn-outline-secondary"><i class="fas fa-home"></i> Back to Home</a>
        </div>
    </div>

    <form method="post" action="SearchServlet" class="input-group mb-4" onsubmit="showLoader()">
        <input id="searchInput" type="text" name="keyword" class="form-control" value="<%= keyword %>" placeholder="Search again...">
        <input type="hidden" name="page" value="1">
        <button class="btn btn-primary"><i class="fas fa-search"></i></button>
        <button type="button" onclick="startVoice()" class="btn btn-secondary"><i class="fas fa-microphone"></i></button>
    </form>

    <div id="loader" class="loader d-none"></div>

    <div id="results" class="<%= results.isEmpty() ? "" : "grid-view" %>">
        <% if (results.isEmpty()) { %>
            <div class="alert alert-warning text-center">No results found for "<%= keyword %>"</div>
        <% } else { %>
            <% for (String[] result : results) { %>
                <div class="card p-3 d-flex flex-row align-items-center shadow-sm">
                    <% if (result.length >= 4 && result[3] != null && !result[3].isEmpty()) { %>
                        <img src="<%= result[3] %>" alt="Thumbnail" class="thumbnail">
                    <% } %>
                    <div>
                        <h5 class="result-title">
                            <a href="<%= result[1] %>" target="_blank"><%= result[0] %></a>
                        </h5>
                        <p><%= result[2] %></p>
                    </div>
                </div>
            <% } %>
        <% } %>
    </div>

    <% if (!results.isEmpty()) { %>
    <nav>
        <ul class="pagination justify-content-center mt-4">
            <% for (int i = 1; i <= totalPages; i++) { %>
                <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                    <form method="post" action="SearchServlet" class="d-inline">
                        <input type="hidden" name="keyword" value="<%= keyword %>">
                        <input type="hidden" name="page" value="<%= i %>">
                        <button class="page-link" type="submit"><%= i %></button>
                    </form>
                </li>
            <% } %>
        </ul>
    </nav>
    <% } %>
</div>

<script>
    function toggleDarkMode() {
        document.body.classList.toggle("dark-mode");
        localStorage.setItem("darkMode", document.body.classList.contains("dark-mode"));
    }

    function showLoader() {
        document.getElementById("loader").classList.remove("d-none");
    }

    function startVoice() {
        const recognition = new (window.SpeechRecognition || window.webkitSpeechRecognition)();
        recognition.lang = 'en-US';
        recognition.start();
        recognition.onresult = function(event) {
            document.getElementById("searchInput").value = event.results[0][0].transcript;
        };
    }

    window.onload = () => {
        if (localStorage.getItem("darkMode") === "true") {
            document.body.classList.add("dark-mode");
        }
        document.getElementById("searchInput").focus();
    };
</script>
</body>
</html>
