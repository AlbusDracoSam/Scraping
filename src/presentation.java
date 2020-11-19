import java.awt.*;
import java.io.*;
import java.net.*;

public class presentation {
    public static void main(String[] args)
    {

        readRSS("http://rss.cnn.com/rss/edition_technology.rss");
    }

    public static String readRSS(String urlAddress)
    {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            Frame f = new Frame("NEWS");
            Label l1,l2,l3,l4,l5;
            int k=0;
            String s1="",s2="",s3="",s4="",s5="";
            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    if(k==0) {
                        s1=temp;

                    }
                    if(k==2) {
                        s2=temp;

                    }
                    if(k==3)
                    {
                        s3=temp;
                    }
                    if(k==4)
                    {
                        s4=temp;
                    }
                    if(k==5)
                    {
                        s5=temp;
                    }
                    k++;
                    if(k==6)
                    {
                        break;
                    }

                }

            }
            l1 = new Label(s1);
            l1.setBounds(50, 100, 1000, 30);
            l1.setFont(new Font("Serif", Font.BOLD, 25));
            l1.setForeground(Color.RED);
            l2 = new Label(s2);
            l2.setBounds(50, 150, 1000, 30);
            l2.setFont(new Font("Serif", Font.BOLD, 25));
            l2.setForeground(Color.RED);
            l3 = new Label(s3);
            l3.setBounds(50, 200, 1000, 30);
            l3.setFont(new Font("Serif", Font.BOLD, 25));
            l3.setForeground(Color.RED);
            l4 = new Label(s4);
            l4.setBounds(50, 250, 1000, 30);
            l4.setFont(new Font("Serif", Font.BOLD, 25));
            l4.setForeground(Color.RED);
            l5 = new Label(s5);
            l5.setBounds(50, 300, 1000, 30);
            l5.setFont(new Font("Serif", Font.BOLD, 25));
            l5.setForeground(Color.RED);
            f.setBackground(Color.lightGray);
            f.add(l1); f.add(l2); f.add(l3);f.add(l4);f.add(l5);
            f.setSize(400,400);
            f.setLayout(null);
            f.setVisible(true);
            in.close();

        }catch(MalformedURLException ue)
        {
            System.out.println("Mailformed URL");
        }catch (IOException ioe){
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }
}