# University Assignment Submission Portal

A full-stack web application built with Spring Boot and React that provides a centralized platform for managing academic assignments. This portal features distinct, role-based dashboards for Administrators, Instructors (Staff), and Students, streamlining the entire assignment lifecycle from creation to grading.

---

## 🌟 Key Features

### 👨‍💼 Admin Portal
* **User Management:** View a list of all users in the system (admins, instructors, students) and delete any user account.
* **Class Management:** View a list of all created classes and delete any class, which also removes all associated students, assignments, and submissions.
* **Secure Login:** Dedicated login for administrators.

### 👩‍🏫 Instructor (Staff) Portal
* **Secure Login:** Dedicated login for instructors.
* **Class Creation:** Create new classes and add a roster of students with their name, register number, email, and date of birth.
* **Student Management:** Add new students to classes that have already been created.
* **Assignment Posting:** Post new assignments with titles, descriptions, and deadlines to specific classes.
* **Submission Review:** View a list of all posted assignments, see which students have submitted their work, view the submitted answers in a popup modal, and award grades.

### 👨‍🎓 Student Portal
* **Secure Login:** Students log in using their email and their date of birth (in YYYY-MM-DD format) as the password.
* **Personalized Dashboard:** View personal profile information and enrolled course details.
* **Assignment Tracking:** See a list of all assignments for their course, along with due dates and their current status ("Not Submitted," "Submitted," or "Graded").
* **Assignment Submission:** View assignment details and submit text-based answers.
* **Instant Grade Feedback:** View grades as soon as they are posted by the instructor.

---

## 🛠️ Technology Stack

This project is built using a modern, industry-standard tech stack.

* **Backend:**
    * **Framework:** Spring Boot
    * **Language:** Java 17
    * **Data Persistence:** Spring Data JPA / Hibernate
    * **Database:** MySQL
    * **Build Tool:** Maven / Gradle
* **Frontend:**
    * **Library:** React (with Vite)
    * **Styling:** Tailwind CSS
    * **API Communication:** Axios
    * **Routing:** React Router DOM
* **Development Tools:**
    * **Backend IDE:** Eclipse
    * **Frontend IDE:** Visual Studio Code
    * **Database GUI:** MySQL Workbench

---

## 🏗️ System Architecture

The application follows a classic **three-tier architecture**, ensuring a clean separation of concerns and making the system scalable and maintainable.


+----------------+      HTTP/S      +----------------------+      JDBC      +----------------+
| Client Browser | <------------> | Spring Boot Backend  | <-----------> | MySQL Database |
|  (React UI)    |   (REST API)   | (Java, JPA/Hibernate)|               |                |
+----------------+                +----------------------+               +----------------+


---

## 🚀 Getting Started

To run this project on your local machine, follow these steps.

### Prerequisites
* Java Development Kit (JDK) 17 or later
* Eclipse IDE (with Spring Tools 4 installed)
* Node.js and npm
* Visual Studio Code
* MySQL Server and MySQL Workbench

### 1. Database Setup
1.  Open MySQL Workbench and create a new schema named `university_portal`.
2.  Run the following SQL script to create all the necessary tables:
    ```sql
    CREATE TABLE users (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        role VARCHAR(50) NOT NULL,
        dob DATE,
        register_no VARCHAR(255),
        course_id BIGINT
    );

    CREATE TABLE courses (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        instructor_id BIGINT
    );

    CREATE TABLE assignments (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        due_date TIMESTAMP,
        course_id BIGINT
    );

    CREATE TABLE submissions (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        assignment_id BIGINT,
        student_id BIGINT,
        file_url TEXT,
        grade VARCHAR(10),
        submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```

### 2. Backend Setup (Eclipse)
1.  Clone or download the backend project folder.
2.  Import the project into Eclipse as an existing Maven/Gradle project.
3.  Navigate to `src/main/resources/application.properties`.
4.  Update the database configuration with your MySQL username and password:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/university_portal
    spring.datasource.username=root
    spring.datasource.password=your_mysql_password
    ```
5.  Right-click on the main application file (`UniversitySubmissionSystemApplication.java`) and select **Run As -> Spring Boot App**. The server will start on `http://localhost:8080`.

### 3. Frontend Setup (VS Code)
1.  Clone or download the frontend project folder.
2.  Open the project in Visual Studio Code and open a new terminal.
3.  Install the required dependencies by running:
    ```bash
    npm install
    ```
4.  Start the development server:
    ```bash
    npm run dev
    ```
5.  The application will be available at `http://localhost:5173`.

---

## 📝 API Endpoints

The backend exposes the following RESTful API endpoints:

### Authentication (`/api/auth`)
* `POST /login`: Authenticates a user and returns their ID and role.

### Staff (`/api/staff`)
* `POST /create-class`: Creates a new course and adds a list of students.
* `GET /classes`: Retrieves a list of all classes with their student rosters.
* `POST /classes/{courseId}/students`: Adds new students to an existing class.
* `POST /assignments`: Posts a new assignment.
* `GET /assignments`: Retrieves a list of all posted assignments.
* `GET /assignments/{assignmentId}/submissions`: Gets the submission status for all students for a specific assignment.
* `PUT /submissions/{submissionId}/grade`: Awards a grade to a student's submission.

### Student (`/api/student`)
* `GET /{studentId}/profile`: Retrieves the profile and course details for a specific student.
* `GET /{studentId}/assignments`: Retrieves all assignments for the student's course.
* `POST /submissions`: Submits a student's answer for an assignment.

### Admin (`/api/admin`)
* `GET /users`: Retrieves a list of all users in the system.
* `DELETE /users/{userId}`: Deletes a user.
* `GET /courses`: Retrieves a list of all courses.
* `DELETE /courses/{courseId}`: Deletes a course and all its associated data.

---

Thank you for checking out the project!
s
