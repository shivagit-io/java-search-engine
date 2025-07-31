<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Portal</title>
    <link rel="icon" href="assets/search-icon.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to bottom right, #2c3e50, #3498db);
            min-height: 100vh;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            transition: background 0.3s ease;
        }

        body.dark-mode {
            background: #121212;
            color: #e0e0e0;
        }

        .glass-box {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 20px;
            padding: 40px;
            max-width: 600px;
            width: 100%;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.37);
            backdrop-filter: blur(12px);
            -webkit-backdrop-filter: blur(12px);
            border: 1px solid rgba(255, 255, 255, 0.18);
            text-align: center;
        }

        .logo {
            width: 80px;
            transition: transform 0.3s;
        }

        .logo:hover {
            transform: scale(1.1) rotate(5deg);
        }

        .search-box {
            display: flex;
            gap: 10px;
            align-items: center;
            margin-top: 20px;
        }

        .form-control {
            flex: 1;
            border-radius: 30px;
            padding: 10px 20px;
        }

        .btn-search {
            border-radius: 30px;
            padding: 10px 20px;
        }

        .mic-btn {
            background: transparent;
            color: white;
            font-size: 1.5rem;
            border: none;
            cursor: pointer;
        }

        .toggle-btn {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .quote {
            margin-top: 30px;
            font-style: italic;
            font-size: 1rem;
            opacity: 0.8;
        }

        .loader {
            border: 5px solid #f3f3f3;
            border-top: 5px solid #3498db;
            border-radius: 50%;
            width: 35px;
            height: 35px;
            animation: spin 1s linear infinite;
            margin: 20px auto;
            display: none;
        }

        @keyframes spin {
            to { transform: rotate(360deg); }
        }

        @media (max-width: 576px) {
            .glass-box {
                padding: 25px;
            }
        }
    </style>
</head>
<body>
    <button class="btn btn-outline-light toggle-btn" onclick="toggleDarkMode()">
        <i class="bi bi-moon-stars-fill"></i>
    </button>

    <div class="glass-box">
        <img src="https://png.pngtree.com/png-clipart/20190614/original/pngtree-search-engine-logo-template-png-image_3720644.jpg" alt="Logo" class="logo mb-3">
        <h2 class="mb-3">Search Smarter, Find Faster</h2>

        <form method="post" action="SearchServlet" class="search-box" onsubmit="showLoader()">
            <input type="text" name="keyword" id="searchInput" class="form-control" placeholder="Type something..." required autofocus>
            <input type="hidden" name="page" value="1">
            <button class="btn btn-primary btn-search" type="submit">
                <i class="bi bi-search"></i>
            </button>
            <button type="button" class="mic-btn" onclick="startVoiceSearch()" title="Speak to Search">
                <i class="bi bi-mic-fill"></i>
            </button>
        </form>

        <div class="loader" id="loader"></div>

        <div class="quote" id="quoteText">
            “The best way to predict the future is to invent it.”
        </div>
    </div>

    <script>
        // Dark mode memory
        function toggleDarkMode() {
            document.body.classList.toggle("dark-mode");
            localStorage.setItem("darkMode", document.body.classList.contains("dark-mode"));
        }

        if (localStorage.getItem("darkMode") === "true") {
            document.body.classList.add("dark-mode");
        }

        // Loader
        function showLoader() {
            document.getElementById("loader").style.display = "block";
        }

        // Voice search
        function startVoiceSearch() {
            if ('webkitSpeechRecognition' in window) {
                const recognition = new webkitSpeechRecognition();
                recognition.lang = 'en-US';
                recognition.interimResults = false;
                recognition.maxAlternatives = 1;
                recognition.onresult = function(event) {
                    document.getElementById('searchInput').value = event.results[0][0].transcript;
                };
                recognition.start();
            } else {
                alert('Your browser does not support voice recognition.');
            }
        }

        // Random inspirational quote
        const quotes = [
            "“The best way to predict the future is to invent it.”",
            "“Search is the beginning of knowledge.”",
            "“Every question is the seed of an answer.”",
            "“The answer you seek might just be a search away.”",
            "“Innovation begins with curiosity.”"
        ];
        document.getElementById("quoteText").innerText = quotes[Math.floor(Math.random() * quotes.length)];
    </script>
</body>
</html>
