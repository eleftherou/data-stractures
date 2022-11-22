import java.io.*;
import java.util.*;

public class writeFiles {

    public static void WF() {


        int N1 = 80; //τα πρωτα 10 αρχεια θα εχουν 80 φακελους
        int N2 = 200; //τα επομενα 10 θα εχουν 200 φακελους
        int N3 = 800;
        FileWriter writer = null;

        for (int k = 1; k <= 10; k++) { //10 ειναι τα αρχεια που ηθελα να παραξουμε
            try {
                writer = new FileWriter(new File("file" +N1+ "-" +k+ ".txt"));

                Random random = new Random();


                for (int i = 0; i < N1; i++) {
                    int r = random.nextInt(1000000);//βαζω οριο 1.000.000 γιατι το ζηταει η εκφωνηση
                    writer.write(r + "\n");
                }

                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing file");
            }
        }

        for (int k = 1; k <= 10; k++) {
            try {
                writer = new FileWriter(new File("file" +N2+ "-" +k+ ".txt"));

                Random random = new Random();


                for (int i = 0; i < N2; i++) {
                    int r = random.nextInt(1000000);
                    writer.write(r + "\n");
                }

                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing file");
            }
        }

        for (int k = 1; k <= 10; k++) {
            try {
                writer = new FileWriter(new File("file" +N3+ "-" +k+ ".txt"));

                Random random = new Random();


                for (int i = 0; i < N3; i++) {
                    int r = random.nextInt(1000000);
                    writer.write(r + "\n");
                }

                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing file");
            }
        }
    }
}
