package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class FileUtils {

    public static int[][] readVertexCoords() {
        int[][] coords = new int[60][2];

        BufferedReader br = null;
        java.io.FileReader fr = null;
        try {
            fr = new java.io.FileReader("vertex_coords");
            br = new BufferedReader(fr);

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(" ");
                coords[Integer.parseInt(line[0])][0] = Integer.parseInt(line[1]);
                coords[Integer.parseInt(line[0])][1] = Integer.parseInt(line[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return coords;
    }

    public static HashMap<String, Integer> readWeights() {
        HashMap<String, Integer> weights = new HashMap<>();

        BufferedReader br = null;
        java.io.FileReader fr = null;
        try {
            fr = new java.io.FileReader("weights");
            br = new BufferedReader(fr);

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                String[] line = currentLine.split(" ");
                weights.put(line[0] + " " + line[1], Integer.parseInt(line[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return weights;
    }

}
