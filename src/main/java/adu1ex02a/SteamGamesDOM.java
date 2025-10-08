package adu1ex02a;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

class Game {
    String title;
    String developer;
    int releaseYear;
    String genre;
    double price;

    public Game(String title, String developer, int releaseYear, String genre, double price) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Títol: " + title +
                "\nDesenvolupador: " + developer +
                "\nAny de llançament: " + releaseYear +
                "\nGènere: " + genre +
                "\nPreu: " + price + "€\n";
    }
}

public class SteamGamesDOM {
    public static void main(String[] args) {
        try {
            // Load and parse the XML file
            File inputFile = new File("games.xml");
            if (!inputFile.exists()) {
                System.out.println("Error: games.xml not found in the project folder.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList gameList = doc.getElementsByTagName("game");
            List<Game> games = new ArrayList<>();

            // Loop through each <game> element
            for (int i = 0; i < gameList.getLength(); i++) {
                Node node = gameList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String developer = eElement.getElementsByTagName("developer").item(0).getTextContent();
                    int releaseYear = Integer
                            .parseInt(eElement.getElementsByTagName("releaseYear").item(0).getTextContent());
                    String genre = eElement.getElementsByTagName("genre").item(0).getTextContent();
                    double price = Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent());

                    games.add(new Game(title, developer, releaseYear, genre, price));
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

    // Writes one text file per developer
    private static void writeToFile(Game game) {
        String safeDeveloperName = game.developer.replaceAll("[^a-zA-Z0-9\\-_]", "_");
        String fileName = safeDeveloperName + ".txt";

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println(game.toString());
        } catch (IOException e) {
            System.out.println("Error writing file for " + game.developer);
            e.printStackTrace();
        }
    }
}
