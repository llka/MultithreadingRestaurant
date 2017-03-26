package ru.ilka.mac.reader;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Здесь могла бы быть ваша реклама +375(29)3880490
 */
public class ClientsReader {

    static Logger logger = Logger.getLogger(ClientsReader.class);
    private static final String REGEX_NAME = "(\\w+)";

    public List<String> readClients(String filePath){
        ArrayList<String> input = new ArrayList();
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

            while ((line = br.readLine()) != null) {
                int count = 0;
                Pattern p = Pattern.compile(REGEX_NAME);
                Matcher m = p.matcher(line);

                while(m.find() && count < 1) {
                    input.add(m.group());
                    count++;
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
