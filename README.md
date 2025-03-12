# Queue Simulation System

## 📌 Overview
This project simulates a queue management system where multiple tasks (clients) arrive and are processed by servers (queues). The simulation supports two scheduling strategies:

- **Shortest Queue:** Assigns the task to the queue with the fewest tasks.
- **Shortest Time:** Assigns the task to the queue with the shortest total waiting time.

The application collects and displays statistics such as waiting time, service time, and queue states.

---

## 🚀 Technologies Used
- **Java (JDK 8+)** – Core implementation
- **Swing (GUI)** – User interface
- **Concurrency (Threads, BlockingQueue, AtomicInteger)** – Efficient queue processing

---

## 📂 Project Structure
```
📂 src/org/example
│── 📂 model
│   ├── Server.java         # Represents a queue (worker) processing tasks
│   ├── Task.java           # Represents a task (client) with arrival & service times
│── 📂 view
│   ├── Design.java         # GUI for user interaction
│── 📂 controller
│   ├── SimulationManager.java # Controls task generation and simulation logic
│── Main.java               # Entry point for the application
```

---

## 🔧 How It Works
### 📝 User Input:
- Number of clients
- Number of queues
- Min & max arrival time
- Min & max service time
- Simulation interval
- Scheduling strategy

### ⚙️ Simulation Flow:
1. Randomly generates tasks based on user input.
2. Assigns tasks to queues using the selected strategy.
3. Each queue (server) processes tasks sequentially.
4. The system tracks waiting time, service time, and completed tasks.

### 📊 Results:
- Tasks are displayed in real-time.
- Statistics such as total waiting time and total service time are calculated.
- The application provides a clear overview of queue processing.

---

## 📌 Key Classes
### 1️⃣ **Task**
Represents a task with:
- ID
- Arrival time
- Service time
- Waiting time

### 2️⃣ **Server (Runnable)**
- Represents a processing queue.
- Uses a **BlockingQueue** for task management.
- Runs on a separate thread, processing tasks sequentially.
- Tracks waiting time and service time.

### 3️⃣ **SimulationManager**
- Controls task generation and queue allocation.
- Implements scheduling strategies.
- Runs the main simulation loop.

### 4️⃣ **Design (GUI)**
- Provides an interface for user input.
- Displays generated tasks and real-time queue status.

---

## ▶️ How to Run
### 🔹 Compile the project:
```bash
javac -d bin src/org/example/*.java
```

### 🔹 Run the application:
```bash
java -cp bin org.example.Main
```

### 🔹 Start the simulation:
1. Enter input values in the GUI.
2. Click **Start** to begin queue processing.

---

## 🚀 Future Improvements
✅ Implement real-time charts for better visualization.
✅ Add priority-based scheduling.
✅ Optimize thread synchronization for better performance.

---

📌 *Developed with ❤️ in Java!*

