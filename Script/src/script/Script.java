//scriptul va lua un fisier de 400.000 randuri si il va imparti in 2 fisiere noi
//unul cu 300.000 pt train si unul 100.000 pt test.
//fiecare din cele 2 fisiere va contine o ratie din fiecare sentiment 3-1
//3 intrari raman intr-un fisier, una e mutata in celalat, de test
// "acceptance": emotion=1;
// "admiration": emotion=2;	
// "amazement": emotion=3; 
// "anger": emotion=4; 
// "annoyance": emotion=5; 
// "anticipation": emotion=6; 
// "boredom": emotion=7; 
// "disgust": emotion=8; 
// "ecstasy": emotion=9; 
// "fear": emotion=10; 
// "grief": emotion=11; 
// "interest": emotion=12; 
// "joy": emotion=13; 
// "rage": emotion=14; 
// "sadness": emotion=15; 
// "serenity": emotion=16; 
// "surprise": emotion=17; 
// "terror": emotion=18; 
// "trust": emotion=19; 
// default: emotion=20;
package script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author io
 */
public class Script {
//calea unde se afla fisierul ce se doreste impartit    
//"c:\\TEST\\libSVM\\windows\\culori.train"

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            System.out.println("Prelucram sentimentul " + i + "...");
            try (BufferedReader reader = new BufferedReader(new FileReader("c:\\TEST\\libSVM\\windows\\culori.train"))) {
                String line;
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    int space = line.indexOf(' ');
                    String number = line.substring(0, space); //extragem sentimentul dominant
                    if (number.equals(String.valueOf(i))) {
                        counter++;
                        if (counter % 4 == 0) { //fiecare a patra linie e de test pentru acest sentiment
                            try (BufferedWriter writerTest = new BufferedWriter(new FileWriter("c:\\TEST\\libSVM\\windows\\imagini.test", true))) {
                                writerTest.write(line, 0, line.length());
                                writerTest.newLine();
                                writerTest.flush();
                                // System.out.println("added to test " + line);
                            } catch (IOException x) {
                                System.err.format("IOException: %s%n", x);
                            }
                        } else { //linie ce trebuie pastrata in train
                            try (BufferedWriter writerTrain = new BufferedWriter(new FileWriter("c:\\TEST\\libSVM\\windows\\imagini.train", true))) {
                                writerTrain.write(line, 0, line.length());
                                writerTrain.newLine();
                                writerTrain.flush();
                                //System.out.println("added to train " + line);
                            } catch (IOException x) {
                                System.err.format("IOException: %s%n", x);
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Script.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
