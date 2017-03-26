package ru.ilka.mac.reader;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class RestaurantReader {
    static Logger logger = Logger.getLogger(RestaurantReader.class);
    private static final String REGEX_CASHDESK_TIME = "(\\d+)";

    public List<List<Integer>> readRestaurant(String filePath){
        ArrayList<List<Integer>> input = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File(filePath);

        if(!file.exists()){
            logger.fatal("Can't find the file ( " + filePath + " )");
            throw new RuntimeException("No input data");
        }

        try {
            String line;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            int i = 0;

            while ((line = br.readLine()) != null) {
                int count = 0;
                int[] cashs = new int[2];
                Pattern p = Pattern.compile(REGEX_CASHDESK_TIME);
                Matcher m = p.matcher(line);

                while(m.find() && count <  2) {
                    cashs[count] = Integer.parseInt(m.group());
                    count++;
                }
                if (count == 2) {
                    input.add(new ArrayList<>());
                    for (int j = 0; j < 2 ; j++) {
                        input.get(i).add(cashs[j]);
                    }
                    i++;
                }
                else {
                    logger.error("Not enough information in input file - " + filePath);
                }

            }
        }catch (FileNotFoundException e){
            logger.fatal("FileNotFoundException but this catch is unreachable");
        }catch (IOException e) {
            logger.error("IOException in dataReader but this catch is unreachable");
        }finally {
            try {
                br.close();
            }catch (IOException ex) {
                logger.error("IOException in dataReader closing" + ex);
            }
            try{
                fr.close();
            }catch(IOException ex) {
                logger.error("IOException in dataReader closing" + ex);
            }
        }
        return Collections.unmodifiableList(input);
    }

}
