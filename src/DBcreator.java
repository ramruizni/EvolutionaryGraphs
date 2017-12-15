import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

class DBcreator {

    private static double weightRandomizer(String et, int m, int d, int h){

        Random rand = new Random();
        int lowBound = 5; int highBound = 800;

        if(d==7){
            if(et.equals("31 32") || et.equals("32 33") || et.equals("49 50"))
                return rand.nextInt(10) + lowBound;
        }

        if(d==3 || d==2){
            if(et.equals("23 24") || et.equals("15 26"))
                return rand.nextInt(10) + highBound;
        }

        if(((h>=6 && h<=8) || (h>=18 && h<=20)) && d!=7){
            if(et.equals("3 10") || et.equals("29 48"))
                return rand.nextInt(20) + highBound;
        }

        if((m>=2 && m<=5) || (m>=8 && m<=11)){
            if(et.equals("34 38") || et.equals("33 37") || et.equals("35 39"))
                return rand.nextInt(5) + lowBound;
        }

        return 0;
    }

    private static double weightRandomizer2(String et, int m, int d, int h){
        Random rand = new Random();
        int lowBound = 4 + rand.nextInt(2);
        int midBound = 39 + rand.nextInt(2);
        int highBound = 99 + rand.nextInt(2);

        if(d>=1 && d<=5){
            if(et.equals("30 31") || et.equals("31 32") || et.equals("32 33") || et.equals("33 34")){ // Calle 26

                if((h>=7&&h<9) || h==14 || h==17 || h==20)
                    return highBound;
                return lowBound;
            }

            if(et.equals("21 29") || et.equals("18 21") || et.equals("6 8")){ // Kra 7ma
                if(h>=17&&h<=19)
                    return highBound;
                return lowBound;
            }

            if(et.equals("12 15") || et.equals("12 20")){ // Calle 80
                if((h>=6&&h<=8) || (h>=17&&h<=20))
                    return highBound;
                return midBound;
            }

            if(et.equals("20 24") || et.equals("24 33")) { // Kra 68
                if ((h>=16&&h<=18))
                    return highBound;
                return midBound;
            }

            if(et.equals("49 50") || et.equals("50 51")) { // Av 1ro de Mayo
                if ((h>=6 && h<=8))
                    return highBound;
                return lowBound;
            }

            return rand.nextInt(3);

        }

        if(d==7){
            if(et.equals("3 10") || et.equals("18 21")){ // Autopista Norte
                if ((h>=9 && h<=14))
                    return highBound;
                return lowBound;
            }

            if(et.equals("7 12") || et.equals("12 25") || et.equals("25 34")){ // Av Boyacá
                if ((h>=9 && h<=14))
                    return highBound;
                return lowBound;
            }

        }

        if(et.equals("28 29") || et.equals("35 39") || et.equals("22 30")){
            return rand.nextInt(60);
        }

        return 0;

    }


    static void createFakeDB(int cases) {

        HashMap<String, Integer> W = new HashMap<>();

        W.put("0 2", 18); W.put("1 3", 16); W.put("2 6", 26); W.put("2 3", 28); W.put("3 4", 15);
        W.put("3 10", 28); W.put("4 5", 15); W.put("4 7", 17); W.put("6 8", 12); W.put("7 11", 26);
        W.put("7 12", 18); W.put("7 13", 22); W.put("8 28", 32); W.put("8 9", 16); W.put("9 10", 14);
        W.put("9 17", 11); W.put("10 17", 6); W.put("10 11", 7); W.put("11 19", 16); W.put("11 20", 8);
        W.put("12 20", 22); W.put("12 15", 12); W.put("12 25", 18); W.put("13 14", 8); W.put("13 47", 18);
        W.put("13 15", 33); W.put("15 26", 21); W.put("15 16", 6); W.put("17 18", 17); W.put("17 19", 10);
        W.put("18 21", 28); W.put("18 19", 15); W.put("19 20", 22); W.put("19 23", 31); W.put("20 24", 17);
        W.put("21 22", 7); W.put("21 29", 11); W.put("22 23", 5); W.put("22 30", 12); W.put("23 31", 12);
        W.put("23 24", 16); W.put("23 32", 14); W.put("24 25", 14); W.put("24 33", 12); W.put("25 26", 14);
        W.put("25 34", 9); W.put("26 35", 17); W.put("26 27", 16); W.put("30 31", 13); W.put("31 32", 10);
        W.put("32 33", 12); W.put("32 37", 10); W.put("33 37", 8); W.put("33 34", 14); W.put("34 35", 14);
        W.put("34 38", 13); W.put("35 39", 17); W.put("35 36", 24); W.put("37 38", 8); W.put("38 39", 14);
        W.put("42 43", 11); W.put("43 45", 30); W.put("43 46", 19); W.put("28 29", 12); W.put("29 30", 5);
        W.put("29 48", 10); W.put("48 40", 10); W.put("48 49", 10); W.put("30 49", 10); W.put("49 41", 9);
        W.put("49 50", 9); W.put("31 50", 12); W.put("50 42", 11); W.put("50 51", 11); W.put("37 51", 10);
        W.put("51 42", 10); W.put("51 52", 8); W.put("38 52", 9); W.put("52 43", 10); W.put("52 53", 11);
        W.put("39 53", 9); W.put("53 44", 10); W.put("53 43", 11);

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter("fakeDB.txt");
            bw = new BufferedWriter(fw);

            for(int i=0; i<cases; i++) {
                // month, day, hour
                for(int m=1; m<=12; m++){  // 12
                    for(int d=1; d<=7; d++){ // 7
                        for(int h=4; h<=23; h++){ // 20
                            for(String et : W.keySet()){ // 83

                                //double add = weightRandomizer(et, m, d, h);
                                double add = weightRandomizer2(et, m, d, h);

                                String key = Integer.toString(m) + " "
                                        + Integer.toString(d) + " "
                                        + Integer.toString(h) + " "
                                        + et;

                                bw.write(key +" "+ Double.toString(W.get(et) + add) + "\n");
                            }
                        }
                    }
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) { ex.printStackTrace(); }
        }

    }

}