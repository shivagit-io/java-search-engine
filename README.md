# 🔍 Simple Search Engine (Java Servlet + JSP + Google Custom Search)

A lightweight, responsive search engine built using **Java Servlets**, **JSP**, and the **Google Programmable Search Engine JSON API**. This web application allows users to search for any keyword and view a clean, paginated list of results with titles, snippets, and thumbnails.

---

## 🚀 Features

- 🌐 Real-time web search using Google Custom Search API  
- 🖼️ Displays result title, snippet, and thumbnail image  
- 📦 Fallback support to local DB cache (optional)  
- 🧩 Built with Java EE (Servlet + JSP)  
- 🎨 Responsive frontend UI using HTML + CSS  
- 🔐 API Key security via `.env` file

---

## 🛠️ Tech Stack

| Layer        | Technology                         |
|--------------|-------------------------------------|
| Backend      | Java Servlets, Java 11+             |
| Frontend     | JSP, HTML5, CSS3                    |
| API          | Google Programmable Search JSON API |
| Build Tool   | Eclipse IDE / Apache Tomcat         |
| Optional DB  | MySQL (for caching, optional)       |

---

## 📂 Folder Structure
<pre>
SimpleSearchEngine/
├── src/
│   └── com/search/
│       ├── SearchAPI.java        # Handles API requests to Google Search
│       └── SearchServlet.java    # Main controller servlet
│
├── WebContent/
│   ├── index.jsp                 # Home search page
│   ├── results.jsp               # Results display page
│   └── style.css                 # Basic styling
│
├── .env                          # Stores API key & CX (not tracked)
├── .gitignore                    # Ignore .env and other sensitive files
└── README.md                     # Project documentation
</pre>

---


---

## 🔐 Environment Setup

Create a `.env` file in your project root (not tracked by Git):

```env
API_KEY=your_google_api_key
CX=your_custom_search_engine_id
```

---

🧪 How to Run
1. Clone the Repository
   git clone https://github.com/shivagit-io/java-search-engine.git

2. Open in Eclipse IDE
Import as a Dynamic Web Project

Add Tomcat Server Runtime

3. Add Environment Variables
Ensure your .env file is in the root and accessible from Java code. Use a library like dotenv-java or manually read the file.

4. Run the App
Right-click SearchServlet.java → Run on Server

Open browser: http://localhost:8080/SimpleSearchEngine

---

🧾 Example Search
Search for: Java Servlets

Output:

✅ Title of the webpage

✅ Description/snippet

✅ Image thumbnail

✅ Link to original page

---

📈 Future Enhancements
 Add database caching for offline searches

 Implement pagination using API's start parameter

 Add search history per session/user

 Dark mode toggle

 ---

 🛡️ Security Notes
API credentials are stored securely in .env

.env is ignored by Git via .gitignore

---

🤝 Contributing
Contributions are welcome! Open an issue or submit a pull request.

---

📄 License
This project is licensed under the MIT License.

---

🙋 Author
Shiva Kumar
GitHub | LinkedIn


---

Let me know if you want me to auto-generate the `LICENSE` file or `.env.example` too.

