import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by akshaykumar on 5/22/16.
 */
public class MovieDataConverter {

    /**
     * cat u.data | cut -f1,2,3 | tr "\\t" ","
     */

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("data/u.data"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("data/movies.csv"));

        String line;

        System.out.println("Start");

        while((line = br.readLine()) != null) {
            String[] values = line.split("\\t", -1);
            bw.write(values[0] + "," + values[1] + "," + values[2] + "\n");
        }

        br.close();
        bw.close();

        System.out.println("End");
    }
}
