Queue Simulation System
Overview
This project simulates a queue management system where multiple tasks (clients) arrive and get processed by servers (queues). The simulation follows two strategies:

Shortest Queue: The task is assigned to the queue with the fewest tasks.
Shortest Time: The task is assigned to the queue with the shortest total waiting time.
The application collects and displays statistics like waiting time, service time, and queue states.

Technologies Used
Java (JDK 8+)
Swing (GUI)
Concurrency (Threads, BlockingQueue, AtomicInteger)
Project Structure
bash
Copy
Edit
📂 src/org/example
│── 📂 model
│   ├── Server.java         # Represents a queue (worker) processing tasks
│   ├── Task.java           # Represents a task (client) with arrival & service times
│── 📂 view
│   ├── Design.java         # GUI for user interaction
│── 📂 controller
│   ├── SimulationManager.java # Controls task generation and simulation logic
│── Main.java               # Entry point for the application
How It Works
User Input:

Number of clients
Number of queues
Min & max arrival time
Min & max service time
Simulation interval
Scheduling strategy
Simulation Flow:

Randomly generates tasks based on user input.
Assigns tasks to queues using the selected strategy.
Each queue (server) processes tasks sequentially.
The system tracks waiting time, service time, and completed tasks.
Results:

Tasks are displayed in real-time.
Statistics such as total waiting time and total service time are calculated.
The application provides a clear overview of queue processing.
Key Classes
1. Task
Represents a task with:
ID
Arrival time
Service time
Waiting time
2. Server (Runnable)
Represents a processing queue.
Uses a BlockingQueue for task management.
Runs on a separate thread, processing tasks sequentially.
Tracks waiting time and service time.
3. SimulationManager
Controls task generation and queue allocation.
Implements scheduling strategies.
Runs the main simulation loop.
4. Design (GUI)
Provides an interface for user input.
Displays generated tasks and real-time queue status.
How to Run
Compile the project:
nginx
Copy
Edit
javac -d bin src/org/example/*.java
Run the application:
bash
Copy
Edit
java -cp bin org.example.Main
Enter input values and start the simulation.
Future Improvements
Implement real-time charts for better visualization.
Add priority-based scheduling.
Optimize thread synchronization for better performance.
