ADU1EX02A

Description:

This program reads an XML file named games.xml that contains information about various games.
For each game, the program creates an object of the Game class with its data (title, developer, launch date, genre, and price).
Then, it displays the information of each game in the console and generates an individual text file for each one, using the game’s name as the filename and including its formatted content.
If the games.xml file does not exist in the project directory, the program displays an error message.

Information

Operating System used: Windows 11.
IDE used: VSCode.
Java version: Java 17.
Libraries used:

org.w3c.dom (for working with XML documents).
javax.xml.parsers (for parsing and processing the XML file).
java.io and java.util (for file management and data structures).

Load Instructions

Make sure that the games.xml file is located in the same directory as the compiled SteamGames class file.

The XML file must have the following structure for each game:

<game>
  <title>Game Name</title>
  <developer>Developer Name</developer>
  <launchDate>Year</launchDate>
  <genre>Genre</genre>
  <price>Price</price>
</game>

Compile the program with the command:

bash
javac adu1ex02a/SteamGames.java

Run the program with the command:

bash
java adu1ex02a.SteamGames

The program will display all game information in the console and create a .txt file for each game inside the project directory.
