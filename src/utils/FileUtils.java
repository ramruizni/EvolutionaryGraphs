package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public static List<Integer> readTerminalNodes() {
        List<Integer> terminalNodes = new ArrayList<>();

        BufferedReader br = null;
        java.io.FileReader fr = null;
        try {
            fr = new java.io.FileReader("terminal_nodes");
            br = new BufferedReader(fr);

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                for (String s : currentLine.split(" ")) {
                    terminalNodes.add(Integer.parseInt(s));
                }
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
        return terminalNodes;
    }
/*
    public <T> T parseFile(String name, T collection) {
        BufferedReader br = null;
        java.io.FileReader fr = null;
        try {
            fr = new java.io.FileReader(name);
            br = new BufferedReader(fr);

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                switch (name) {
                    case "weights":
                        if (collection instanceof HashMap) {
                            String[] line = currentLine.split(" ");
                            ((HashMap) collection).put(line[0] + " " + line[1], Integer.parseInt(line[2]));
                        }
                        break;
                    case "terminal_nodes":
                        if (collection instanceof ArrayList) {
                            for (String s : currentLine.split(" ")) {
                                ((ArrayList) collection).add(Integer.parseInt(s));
                            }
                        }
                        break;
                    case "vertex_coords":
                        if (collection instanceof int[][]) {
                            String[] line = currentLine.split(" ");
                            ((int[][]) collection)[Integer.parseInt(line[0])][0] = Integer.parseInt(line[1]);
                            ((int[][]) collection)[Integer.parseInt(line[0])][1] = Integer.parseInt(line[2]);

                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
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
        return collection;
    }
*/
}
