# SudokuSolver
**Work In Progress**

A Java program implementing a Client-Server architecture that uses OpenCV to identify and solve Sudoku puzzles provided by the user via their webcam

Instructions for Download: 
Step 1: Click on https://github.com/rossmfadams/SudokuSolver and click the green code button. Inside the drop down window select Download ZIP. 
Step 2: Unzip the file to an easy to find location such as your desktop or local workspace. 
Step 3: Open Eclipse. Create a new project. Inside the New Project menu, select "Existing Maven Project" underneath the Maven header, and then click Next. 
Step 4: At the top right click Browse and select the SudokuSolver-main folder downloaded in Step 1. The pom.xml file should appear for project select it and click Finish. 

The project should now appear in Eclipse, initially there will be errors as the classpaths have not been configured to match the project on your computer, so we must change them. 

Step 5: Click on the Sudoku Project Folder. In the pop up window, select Build Path, then select Configure Build Path... 
Step 6: Inside the Properties of Sudoku panel select the Libraries tab at the top. Here we see 4 jar files with errors.
Step 7: Select the first jar file, "mysql-conn..." On the right hand side, select Edit... The project folder directory will appear, select the jar file that corresponds to the one you are currently changing the classpath to, "mysql-conn...". Click Open. 
Step 8: Select the second "mysql-conn..." and remove the jar file. 
Step 9: Repeat steps 7 and 8 for the second jar file "ocsf" .
Step 10: Select Apply and Close at the bottom on the window. 

The project classpaths should now be correct, and the errors should be resolved. The project has now successfully been downloaded. 

Instructions to Run: 
Step 1: 
