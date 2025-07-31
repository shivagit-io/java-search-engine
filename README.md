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
SimpleSearchEngine/
│
├── src/com/search/
│ ├── SearchAPI.java # Handles API calls
│ └── SearchServlet.java # Main controller servlet
│
├── WebContent/
│ ├── index.jsp # UI: Search page
│ ├── results.jsp # UI: Results display
│ └── style.css # Styling
│
├── .env # Stores API Key & CX (not tracked)
└── README.md

