// Roberto Fernández del Barrio.//
// ADU1EX02A....................//
// 43232819H....................//
// 10-10-2025...................//
package adu1ex02a;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

// New class for the Game
class Game {
    String title; // Title of the game
    String developer; // Developer of the game
    int launchDate; // Launch Date of the game
    String genre; // Genre of the game
    double price; // Price of the game

    // New public for calling the information of the games
    public Game(String title, String developer, int launchDate, String genre, double price) {
        this.title = title; // Assign title
        this.developer = developer; // Assign developer
        this.launchDate = launchDate; // Assign launchDate
        this.genre = genre; // Assign genre
        this.price = price; // Assign price
    }

    // Return the information of the game and show it by console as a formatted
    // String
    @Override
    public String toString() {
        return "Title: " + title + // Title of the game
                "\nDeveloper: " + developer + // Developer of the game
                "\nLaunch Date: " + launchDate + // Launch Date of the game
                "\nGenre: " + genre + // Genre of the game
                "\nPrice: " + price + " euros"; // Price of the game
    }
}

public class SteamGamesDOM {
    public static void main(String[] args) {
        try {
            // Load and parse the XML file "games.xml"
            File inputFile = new File("games.xml"); // Reference to the "games.xml" file
            if (!inputFile.exists()) { // Check if exists
                System.out.println("Error: games.xml not found in the project folder.");
                return; // If the "games.xml" file doesn´t exists, shows an error for it
            }

            // Set up the XML document parser
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // Create new factory instance
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); // Create the XML document builder
            Document doc = dBuilder.parse(inputFile); // Parse the XML file into a Document object
            doc.getDocumentElement().normalize(); // Normalize the XML structure (removes whitespace, merges adjacent
                                                  // text nodes, etc.)
            // Retrieve all the elements of the XML file
            NodeList gameList = doc.getElementsByTagName("game");
            List<Game> games = new ArrayList<>(); // Create a list to store all Game elements

            // Loop through each <game> element found it in the XML file "games.xml"
            for (int i = 0; i < gameList.getLength(); i++) {
                Node node = gameList.item(i); // Get the current node
                if (node.getNodeType() == Node.ELEMENT_NODE) { // Ensure the node is an element
                    Element eElement = (Element) node; // Cast to Element to access child tags
                    // Extract and parse each field from the XML tags
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent(); // Get String
                                                                                                    // "title" with tag
                                                                                                    // title
                    String developer = eElement.getElementsByTagName("developer").item(0).getTextContent(); // Get
                                                                                                            // String
                                                                                                            // "developer"
                                                                                                            // with tag
                                                                                                            // developer
                    int launchDate = Integer
                            .parseInt(eElement.getElementsByTagName("launchDate").item(0).getTextContent()); // Get
                                                                                                             // int
                                                                                                             // "launchDate"
                                                                                                             // with
                                                                                                             // tag
                                                                                                             // launchDate
                    String genre = eElement.getElementsByTagName("genre").item(0).getTextContent(); // Get String
                                                                                                    // "genre" with the
                                                                                                    // tag genre
                    double price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent()); // Get
                                                                                                                        // double
                                                                                                                        // "price"
                                                                                                                        // with
                                                                                                                        // the
                                                                                                                        // tag
                                                                                                                        // price

                    games.add(new Game(title, developer, launchDate, genre, price));
                }
            }

            // Print to console and write to files
            for (Game g : games) {
                System.out.println(g);
                writeToFile(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Writes one text file per game with the title name
    private static void writeToFile(Game game) {
        String safeGameName = game.title; // Catching the title of the game
        String fileName = safeGameName + ".txt"; // Creating the .txt file with the name of the game "title"

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(game.toString());
        } catch (IOException e) {
            System.out.println("Error writing file for " + game.title);
            e.printStackTrace(); // Show an error message in case of error
        }
    }
}