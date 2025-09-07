# CPU Scheduling Algorithm Web Application

A comprehensive full-stack web application that visualizes and solves various CPU scheduling algorithms. This project demonstrates the implementation of operating system concepts through an interactive web interface.

## 🚀 Features

- **Multiple CPU Scheduling Algorithms:**
  - ✅ **First Come First Serve (FCFS)** - Non-preemptive, processes executed in arrival order
  - ✅ **Shortest Job First (SJF)** - Non-preemptive, shortest burst time first
  - ✅ **Shortest Remaining Time First (SRTF)** - Preemptive version of SJF
  - ✅ **Round Robin (RR)** - Preemptive with configurable time quantum
  - ✅ **Priority Non-Preemptive (PNP)** - Lower priority number = higher priority
  - ✅ **Priority Preemptive (PP)** - Preemptive priority scheduling

- **Interactive User Interface:**
  - Real-time input validation
  - Dynamic Gantt chart visualization
  - Comprehensive results table showing:
    - Arrival Time (AT)
    - Burst Time (BT)
    - Completion Time (CT)
    - Turnaround Time (TT)
    - Waiting Time (WT)

- **Full-Stack Architecture:**
  - RESTful API backend with Spring Boot
  - Modern React frontend with Vite
  - MySQL database for process data storage

## 🛠️ Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Lombok**

### Frontend
- **React 18**
- **Vite**
- **Tailwind CSS**
- **Axios**
- **React Icons**

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **Maven 3.6+**
- **Node.js 18+ and npm**
- **MySQL 8.0+**
- **Git**

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/cpu-scheduler.git
cd cpu-scheduler
```

### 2. Database Setup

1. **Install MySQL** and start the MySQL service
2. **Create a database:**
   ```sql
   CREATE DATABASE CPU_Database;
   ```
3. **Update database credentials** in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/CPU_Database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. Backend Setup

Navigate to the backend directory and run the Spring Boot application:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

**Alternative using Maven Wrapper:**
```bash
./mvnw clean install    # On macOS/Linux
.\mvnw.cmd clean install # On Windows

./mvnw spring-boot:run    # On macOS/Linux
.\mvnw.cmd spring-boot:run # On Windows
```

### 4. Frontend Setup

Open a new terminal, navigate to the frontend directory, and start the development server:

```bash
cd frontend
npm install
npm run dev
```

The frontend application will start on `http://localhost:5173`

## 🎯 Usage

1. **Open your browser** and navigate to `http://localhost:5173`

2. **Select an Algorithm** from the dropdown menu

3. **Enter Process Data:**
   - **Arrival Time:** Space-separated values (e.g., `0 2 4 6 8`)
   - **Burst Time:** Space-separated values (e.g., `3 6 4 5 2`)
   - **Priority Values:** Required only for Priority algorithms (lower value = higher priority)
   - **Time Quantum:** Required only for Round Robin algorithm (default: 3)

4. **Click "Solve"** to see the results

5. **View Results:**
   - Gantt chart visualization
   - Detailed process table
   - Algorithm-specific calculations

## 📁 Project Structure

```
cpu-scheduler/
│
├── README.md                 # This file
├── backend/                  # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/cpu/scheduling/CPUBackend/
│   │   │   │       ├── CpuBackendApplication.java
│   │   │   │       ├── controller/
│   │   │   │       │   └── AlgorithmController.java
│   │   │   │       ├── entity/
│   │   │   │       │   └── CpuData.java
│   │   │   │       ├── repository/
│   │   │   │       │   └── AlgorithmRepository.java
│   │   │   │       └── service/
│   │   │   │           ├── AlgorithmService.java
│   │   │   │           └── AlgorithmServiceImplementation.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw, mvnw.cmd
│
└── frontend/                 # React + Vite frontend
    ├── src/
    │   ├── App.jsx
    │   ├── main.jsx
    │   ├── index.css
    │   └── Components/
    │       ├── ErrorMessage.jsx
    │       ├── InputFile.jsx
    │       ├── OutputFile.jsx
    │       └── Table.jsx
    ├── public/
    ├── package.json
    ├── vite.config.js
    ├── tailwind.config.js
    └── index.html
```

## 🔌 API Endpoints

### Backend REST API

- **POST** `/saveData` - Save process data to database
- **GET** `/clear` - Clear all process data
- **GET** `/fcfs` - Execute First Come First Serve algorithm
- **GET** `/sjf` - Execute Shortest Job First algorithm
- **GET** `/srtf` - Execute Shortest Remaining Time First algorithm
- **GET** `/rr?timeQuantum={value}` - Execute Round Robin (default quantum = 3)
- **GET** `/priority-np` - Execute Priority Non-Preemptive algorithm
- **GET** `/priority-p` - Execute Priority Preemptive algorithm

**Example API Response:**
```json
[
  [0, 2, 4, 6, 8],     // Arrival times
  [3, 6, 4, 5, 2],     // Burst times
  [3, 9, 13, 18, 20],  // Completion times
  [3, 7, 9, 12, 12],   // Turnaround times
  [0, 1, 5, 7, 10]     // Waiting times
]
```

## 🚀 Available Scripts

### Backend
```bash
mvn clean install    # Build the project
mvn spring-boot:run  # Run the application
mvn test            # Run tests
```

### Frontend
```bash
npm install         # Install dependencies
npm run dev         # Start development server
npm run build       # Build for production
npm run preview     # Preview production build
npm run lint        # Run ESLint
```

## 🔧 Configuration

### Backend Configuration (`application.properties`)
```properties
# Database configuration
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/CPU_Database
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
```

### Frontend Configuration
- **Vite config:** `vite.config.js`
- **Tailwind config:** `tailwind.config.js`
- **PostCSS config:** `postcss.config.js`

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 Algorithm Details

### First Come First Serve (FCFS) ✅
- **Type:** Non-preemptive scheduling algorithm
- **Logic:** Processes are executed in the order they arrive
- **Pros:** Simple implementation, no starvation
- **Cons:** Can cause convoy effect with long processes

### Shortest Job First (SJF) ✅
- **Type:** Non-preemptive algorithm
- **Logic:** Selects process with smallest burst time among available processes
- **Pros:** Optimal for average waiting time
- **Cons:** Can cause starvation of longer processes

### Shortest Remaining Time First (SRTF) ✅
- **Type:** Preemptive version of SJF
- **Logic:** Can switch to shorter process when it arrives during execution
- **Pros:** Better response time than SJF
- **Cons:** More complex, overhead of context switching

### Round Robin (RR) ✅
- **Type:** Preemptive algorithm with time quantum
- **Logic:** Each process gets equal CPU time slices
- **Pros:** Good for time-sharing systems, fair allocation
- **Cons:** Performance depends on time quantum choice

### Priority Non-Preemptive (PNP) ✅
- **Type:** Non-preemptive priority-based scheduling
- **Logic:** Highest priority process (lowest number) executes first
- **Pros:** Important processes get preference
- **Cons:** Can cause starvation of low-priority processes

### Priority Preemptive (PP) ✅
- **Type:** Preemptive priority-based scheduling
- **Logic:** Higher priority process can interrupt current execution
- **Pros:** Better response time for high-priority processes
- **Cons:** High overhead, possible starvation

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error:**
   - Ensure MySQL is running
   - Check database credentials in `application.properties`
   - Verify database `CPU_Database` exists

2. **Port Already in Use:**
   - Backend: Change port in `application.properties` (`server.port=8081`)
   - Frontend: Vite will automatically suggest an alternative port

3. **CORS Issues:**
   - Backend includes `@CrossOrigin(origins = "*")` for development
   - For production, configure specific origins

4. **Build Errors:**
   - Ensure Java 17+ is installed
   - Clear Maven cache: `mvn dependency:purge-local-repository`
   - Clear npm cache: `npm cache clean --force`

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Operating System concepts and CPU scheduling algorithms
- Spring Boot documentation
- React and Vite communities
- Tailwind CSS for styling

---

**Note:** This is an educational project demonstrating CPU scheduling algorithms. It's designed for learning purposes and understanding operating system concepts.
