# 📞 SmartContactManager

A polished **Contact Management System** developed with **Spring Boot**, featuring multi-auth (email, Google, GitHub) via Spring Security, alongside theme toggling (light/dark), clean UI using **TailwindCSS + Thymeleaf**, and secure backend architecture.

---

## 🚀 Features  

- **User Authentication**: Sign up / log in via email, Google, or GitHub (OAuth).  
- **Responsive UI**: Light and Dark mode switch powered by Tailwind CSS.  
- **Contact Management**: Add, view, edit, and delete contacts.  
- **Secure**: Hardened using Spring Security and session handling.  
- **Modern Frontend**: Built with Thymeleaf templates and TailwindCSS aesthetics.  

---

## 🛠 Tech Stack

| Layer                 | Tools / Frameworks                                   |
|-----------------------|------------------------------------------------------|
| Backend               | Java, Spring Boot, Spring Security, OAuth integration|
| Frontend              | Thymeleaf, Tailwind CSS                              |
| Build & Dependencies  | Maven (via `pom.xml`)                                |
| Authentication        | Email, Google, GitHub OAuth                          |

---

## 📂 Project Structure  

SmartContactManager/  
├── src/  
│ ├── main/  
│ │ ├── java/com/... # Controllers, Models, Services, Repositories, Config  
│ │ └── resources/  
│ │ ├── templates/ # Thymeleaf HTML views  
│ │ └── static/ # CSS (Tailwind-generated), JS (if any)  
├── pom.xml # Maven configuration  
├── package.json # For managing frontend dependencies  
├── src/main/resources/application.properties  
└── Tailwind config files  

yaml  
Copy code  

---

## ⚡ Getting Started  

### 1️⃣ Clone the Repository  
git clone https://github.com/RaushanImamAnsari/SmartContactManager.git  
cd SmartContactManager  

2️⃣ Configure application properties  
Set your authentication and datasource parameters in  
src/main/resources/application.properties.  

3️⃣ Install frontend dependencies & build styles  
bash
Copy code
npm install
npm run build:css
  
4️⃣ Run the Spring Boot application  
bash
Copy code
mvn spring-boot:run
👉 Visit http://localhost:8080 to access the app.  

```

🗄 Database Schema (Optional)
sql
Copy code
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    provider ENUM('LOCAL', 'GOOGLE', 'GITHUB'),
    roles VARCHAR(50)
);

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    notes TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

```

## 🌱 Future Enhancements  

Add Images or Avatars for contacts  
Implement contact groups or tags  
Advanced search and pagination  
RESTful API + mobile frontend  
Import/export contacts (CSV, vCard)  

## 🤝 Contribution  

Contributions are welcome!  

## Fork the project  
Create a new feature branch (git checkout -b feature/your-feature)  
Commit your changes (git commit -m "feat: ..."), push, and open a PR  

👨‍💻 Author
Raushan Imam Ansari
Built with care using Spring Boot and modern UI practices.

🔗 Repo: SmartContactManager
