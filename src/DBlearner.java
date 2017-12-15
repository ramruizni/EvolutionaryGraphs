import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DBlearner {

    HashMap<String, Double> W = new HashMap<>();
    ArrayList<String> edgeTitles;

    DBlearner(){ }

    void resetW(){

        edgeTitles = new ArrayList<>();

        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("edgeTitles.txt");
            br = new BufferedReader(fr);
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null)
                edgeTitles.add(sCurrentLine);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) { ex.printStackTrace(); }
        }

        // month, day, hour
        for(int m=1; m<=12; m++){  // 12
            for(int d=1; d<=7; d++){ // 7
                for(int h=4; h<=23; h++){ // 20
                    for(String edgeTitle : edgeTitles){ // 83
                        String key = Integer.toString(m) + " "
                                + Integer.toString(d) + " "
                                + Integer.toString(h) + " "
                                + edgeTitle;
                        W.put(key, 0.0); // so we got 139440 possible links
                    }
                }
            }
        }

    }

    void learnFromDB(int cases){

        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("fakeDB.txt");
            br = new BufferedReader(fr);
            String currLine;

            while( (currLine=br.readLine()) !=null) {
                String[] line = currLine.split(" ");

                String key = line[0] +" "+ line[1] +" "+ line[2] +" "+ line[3] +" "+ line[4];

                W.put(key, W.get(key)+ Double.parseDouble(line[5]));

                if(!W.containsKey(key)) System.out.println(key + " not contained");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ex) { ex.printStackTrace(); }
        }

        for(String key : W.keySet()) W.put(key, W.get(key)/cases);

    }

    HashMap<String, Double> getLearnedW(){ return W; }

    ArrayList<String> getEdgeTitles(){ return edgeTitles; }
}
