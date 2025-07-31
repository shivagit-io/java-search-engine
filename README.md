# 🔍 Java Servlet Search Engine

A lightweight search engine built with **Java Servlets**, **JSP**, and **Google Custom Search API**, styled with JSP-based frontend. It includes pagination, caching, and a fallback to API when DB is empty.

---

## 🚀 Features

- ✅ Search via Google Custom Search API
- ✅ Dynamic search results with titles, links, and descriptions
- ✅ Pagination (5 results per page)
- ✅ Fallback logic: API -> DB
- ✅ JSP frontend with black & white gradient theme
- ✅ Mobile-friendly layout
- ✅ "Back to Home", voice search, and theme switcher
- ✅ (Optional) MySQL DB Caching logic (disabled if API-only)

---

## 📦 Tech Stack

- Java (Servlets + JSP)
- Tomcat Server
- Google Programmable Search JSON API
- (Optional) MySQL + JDBC

---

## 📂 Folder Structure

YourProject/
├── src/
│ └── com/search/
│ ├── SearchServlet.java
│ ├── SearchAPI.java
│ └── (Optional) SearchDao.java
├── WebContent/
│ ├── index.jsp
│ ├── results.jsp
│ └── style.css
├── .gitignore
├── README.md
└── .env (manually created)


---

## 🔐 Setup: Environment Variables

We use `.env` file to keep sensitive data (like API keys) secure. Make sure to **add `.env` to `.gitignore`**.

```env
API_KEY=your_google_api_key
CX=your_custom_search_engine_id


🛠️ How to Run Locally
Clone the repo

Open it in Eclipse or IntelliJ

Configure Apache Tomcat

Deploy the project

Go to http://localhost:8080/YourProjectName/

🙌 Author
Made with ❤️ by [SHIVA]


Replace:
- Name:`Shiva`
- Project Name:Search Engine`

---

## ✅ 2. `.env` File for API Key Security

**Create a new file named `.env`** in your root project directory and paste:

```env
API_KEY=AIzaSyXXXXXXYourAPIKeyHere
CX=2341d98d8d01f41c8




