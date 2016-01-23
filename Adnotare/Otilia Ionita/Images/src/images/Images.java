package images;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.Color;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.*;
import java.net.*;

public class Images {

    public static void downloadImage(String url, String imgSrc) throws IOException 
    {
        BufferedImage image = null;
        try {
            if (!(imgSrc.startsWith("http"))) {
                url = url + imgSrc;
            } else {
                url = imgSrc;
            }
            imgSrc = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = imgSrc.substring(imgSrc.lastIndexOf(".") + 1);
            String imgPath = null;
            imgPath = "E:/facultate/Java/Images/Images/" + imgSrc + "";
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);
            if (image != null) {
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void downloadLinks() throws IOException 
    {
        PrintWriter writer = new PrintWriter("names.txt", "UTF-8");
        for(int i=1; i<=59;i++)
        {
            URL url = new URL("http://visual-sentiment-ontology.appspot.com/list_bi_concepts?detectable=True"
                    + "&selected_adj=all&selected_noun=all&selected_emotion=all&sorting=ontology&sorting_order=asc&page="+i);
            
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
            parser.parse(br, callback, true);
            String f = new String("");

            for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()) 
            {
                AttributeSet attributes = iterator.getAttributes();
                String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
                if(href.contains("show_bi_concept?concept_name=") && !f.contentEquals(href))
                {
                    String webUrl = "http://visual-sentiment-ontology.appspot.com/"+href;
                    writer.println(webUrl);
                    f=href;
                }
            }
        }
        writer.close();
        System.out.println("Am terminat cu citirea de linkuri.");
    }
    public static void main (String[] args) throws FileNotFoundException, IOException 
    {
        //downloadLinks();
        BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
        String line = reader.readLine();
        int i=1;
        while(line != null)
        {
            String webUrl = line;
            URL url = new URL(webUrl);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            HTMLEditorKit htmlKit = new HTMLEditorKit();
            HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
            HTMLEditorKit.Parser parser = new ParserDelegator();
            HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
            parser.parse(br, callback, true);
            for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) 
            {
                AttributeSet attributes = iterator.getAttributes();
                String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);

                if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".jpeg")) || (imgSrc.endsWith(".bmp")) || (imgSrc.endsWith(".ico")))) {
                    try {
                        downloadImage(webUrl, imgSrc);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            line = reader.readLine();
            System.out.println(i+". "+line);
            i++;
        }   
    }
    
}
