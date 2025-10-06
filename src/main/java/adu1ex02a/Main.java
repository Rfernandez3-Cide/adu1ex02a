package adu1ex02a;

public class Main {
    String title;
    String developer;
    int releaseYear;
    String genre;
    double price;

    public void Game(String title, String developer, int releaseYear, String genre, double price) {
        this.title = title;
        this.developer = developer;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.price = price;
    }

    public String toString() {
        return "Title: " + title +
                "\nDeveloper: " + developer +
                "\nRelease Year" + releaseYear +
                "\n Genre" + genre +
                "\n Price" + price;
    }

    public static void main(String[] args) {
        
    }
}