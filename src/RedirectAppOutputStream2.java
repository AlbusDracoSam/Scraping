import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.util.*;
import java.io.IOException;
import java.io.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class RedirectAppOutputStream2 {

private class GuiOutputStream extends OutputStream  {
        JTextArea textArea;

        public GuiOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int data) throws IOException {
            textArea.append(new String(new byte[] { (byte) data }));
        }
    }

    public void guiConsoleTest() {

        JTextArea textArea = new JTextArea(); // Output text area

        PrintStream stdout = System.out;
        GuiOutputStream rawout = new GuiOutputStream(textArea);
        // Set new stream for System.out
        System.setOut(new PrintStream(rawout, true));

        // Demo gui
        JFrame window = new JFrame("Scraping");
        window.add(new JScrollPane(textArea));
        window.setSize(500, 500);
        window.setVisible(true);


        try {
            Document doc = Jsoup.connect("https://www.imdb.com/chart/top/").timeout(6000).get(); //Scraping website and timeout to handle exception

            Elements elt = doc.select("tbody.lister-list");   // HTML class part we are gonna scrape


            System.out.printf("\nWebsite Title: %s\n\n", doc.title());  // Printing the title of the website


            for(Element e : elt.select("tr"))
            { // Every Movie is found in a tr tag
                String img = e.select("td.posterColumn img").attr("src");

                String title = e.select("td.posterColumn img").attr("alt");

                String year = e.select("td.titleColumn span.secondaryInfo").text().replaceAll("[^\\d]","");

                String rate = e.select("td.ratingColumn.imdbRating").text().trim();

                System.out.printf("%s  - %s  - %s\n",title,year,rate);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException g) {
                }

                // Wait 1 sec

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException f) {
                }

                // Clean up and exit
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.dispose();
    }

    public static void main(String[] args) {

        new RedirectAppOutputStream2().guiConsoleTest();
    }
}