# JavaChessMate
Empowering Aspiring Developers: JavaChessMate - Your Gateway to Creating Chess Engines on UCI Protocol with Arena GUI, Simplifying the Journey for Beginner Coders in the World of Chess AI.

## Introduction
Welcome to JavaChessMate, an open-source project that empowers aspiring developers to create their own Chess engines using Java and the Universal Chess Interface (UCI) protocol. This comprehensive documentation will walk you through the process of building your Chess engine, integrating it with the Arena GUI, and simplifying the journey for beginner coders in the world of Chess AI.

## Prerequisites
Before you begin, ensure you have the following prerequisites:
- Basic knowledge of Java programming language.
- Familiarity with Chess rules and gameplay.
- Arena GUI installed on your system (download from [https://www.playwitharena.com/](https://www.playwitharena.com/)).

# Setup
## Development Environment Setup
1. **Installing Java Development Kit (JDK)**
   Ensure you have JDK 11 installed on your system to support JavaChessMate.
2. **Setting up Integrated Development Environment (IDE)**
   - If you already have IntelliJ IDEA installed, skip this step. Otherwise, download and install IntelliJ IDEA from the official website.
   - Launch IntelliJ IDEA and set up your project environment.
3. **Cloning the JavaChessMate Repository**
   - Clone the JavaChessMate repository from GitHub using the following command:
     ```
     git clone https://github.com/harshavardhankonisa/javachessmate.git
     ```
   - Open the cloned project in IntelliJ IDEA by selecting "Open" and choosing the project directory.
4. **Running the JavaChessMate Project**
   - Open the main function in your JavaChessMate project.
   - Click the "Run" button in IntelliJ IDEA to execute the project.
   - Enjoy your JavaChessMate Chess engine!
  
## Production Environment Setup
1. **Creating a New IntelliJ IDEA Project**
   Create a new IntelliJ IDEA project to set up the production environment for JavaChessMate.
2. **Organizing the Project Structure**
   - Create a new package named `main` to hold the production code.
   - Place all the necessary production code functions into a single file named `Main.java` inside the `main` package. This file will serve as the entry point for the production environment.
3. **Moving Developer Code functions to Main**
   Move all relevant functions and logic from the developer's codebase to the `Main.java` file. Ensure that the necessary dependencies and classes are imported to maintain the functionality.
4. **Creating the TreeNode Class**
   - In a new file named `TreeNode.java`, create the `TreeNode` class, which is required for constructing and managing tree structures in the production code.
   - Implement the necessary methods and attributes inside the `TreeNode` class to facilitate the desired functionality.
5. **Refactoring Code**
   - Review the developer's code for any project-specific configurations, file paths, or environment settings that need to be adjusted for the production environment.
6. **Testing in Production Environment**
   - Ensure all functionalities work correctly in the production environment.
   - Run thorough tests to verify that the production code functions as expected and delivers the desired outcomes.
7. **Building and Packaging**
   - Build the JavaChessMate project in IntelliJ IDEA to create the executable JAR file.


## Arena GUI Setup
1. **Download JavaChessMate Jar File**
   Download the JavaChessMate jar file from the following 
   [link](https://github.com/harshavardhankonisa/javachessmate/blob/master/out/artifacts/JavaChessMate_jar/JavaChessMate.jar).
2. **Open Arena**
   Launch the Arena GUI application on your computer.
3. **Installing JavaChessMate as a New Engine**
   - In the menu bar, go to `Engines > Install New Engine`.
   - Select the JavaChessMate.jar file you downloaded earlier (make sure the file type selected is .jar).
4. **Configuring JavaChessMate Engine Type**
   - In the menu bar, go to `Engines > Manage > Details`.
   - Select your AI (JavaChessMate) from the list.
   - Change the "Type" to UCI (Universal Chess Interface).
5. **Selecting JavaChessMate as the Active Engine**
   - In the menu bar, go to `Engines > Manage > Select`.
   - Choose your AI (JavaChessMate) from the list to make it the active engine.
6. **Play a Game with JavaChessMate**
   - Click on the "Demo" button in Arena to start a game against JavaChessMate.
   - Now, you can play a game against JavaChessMate and experience the power of our AI-based Chess engine!
Have fun playing and experimenting with JavaChessMate in Arena GUI!

## Important Note: Difference Between Development and Production Environments
1. **ChessSearch Class:**
   In the `ChessSearch` class, you can set up the engine's strength by adjusting the `depth` and `max_time` parameters to achieve optimal performance. However, during development, you might want to modify these settings for easier debugging.
   - If you are using the development environment, it is recommended to keep `depth` to 1 and `max_time` to 5000.

2. **Files Handling (JavaClasses):**
   I have organized the files in a sorted manner to facilitate faster code writing, reducing concerns about dealing with large code bases and future implementations.
   - The architecture follows the MVC (Model-View-Controller) pattern, which is the most familiar for me.
   - **Controller:** Contains the UCI interface implementation.
   - **Services:** Includes functions that support the UCI.
   - **Models:** Stores Java Interfaces, Objects, Constants, and other related elements.

3. **Jar File Provided is My Chess Engine**
   - The given JAR file is specifically meant for use with the ARENA GUI as a chess engine.
   - This chess engine is optimized for beginners and has a depth of 2.
