import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.*;
import java.io.IOException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        PrintStream o = new PrintStream("Scraped.txt"); //Printing the console output to a text file

        PrintStream console = System.out;       //Printing to console
        try {
            Document doc = Jsoup.connect("https://www.imdb.com/chart/top/").timeout(6000).get(); //Scraping website and timeout to handle exception

            Elements elt = doc.select("tbody.lister-list");   // HTML class part we are gonna scrape


            System.out.printf("\nWebsite Title: %s\n\n", doc.title());  // Printing the title of the website


            for(Element e : elt.select("tr")){ // Every Image is found in a tr tag
                String img = e.select("td.posterColumn img").attr("src");

                String title = e.select("td.posterColumn img").attr("alt");

                String year = e.select("td.titleColumn span.secondaryInfo").text().replaceAll("[^\\d]","");

                String rate = e.select("td.ratingColumn.imdbRating").text().trim();

                System.setOut(o);
                System.out.printf("%s  - %s  - %s\n",title,year,rate);

                System.setOut(console);
                System.out.printf("%s  - %s  - %s\n",title,year,rate);
            }
       } catch (IOException e) {
            e.printStackTrace();
       }
    }
}
