package data;


import org.w3c.dom.stylesheets.LinkStyle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVFileReader {
	/**
	 * The read method reads in a csv file as a two dimensional string array.
	 * This method is utilizes the string.split method for splitting each line of the data file.
	 * @param csvFile File to load
	 * @param seperationChar Character used to seperate entries
	 * @param nullValue What to insert in case of missing values
	 * //@param headerRow Does data file contain a header row?
	 * @return Data file content as a 2D string array
	 * @throws IOException
	 */
	public static List<List<String>> readDataFile(String csvFile, String seperationChar, String nullValue, boolean headerRow) throws IOException {
        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader bufRdr = new BufferedReader(new FileReader(new File(csvFile)));
        String line ;

        // read the header
        if (headerRow) {
            line = bufRdr.readLine();

            String[] header1 = line.split(seperationChar);
            List<String> header = new ArrayList<String>();
            for (String s : header1) {
                header.add(s.trim());
            }
            ret.add(header);
        }
        //read the rest of the csv file
		while ((line = bufRdr.readLine()) != null) {
		    String[] data = line.split(seperationChar);
		    ret.add(Arrays.asList(data));
		}
		bufRdr.close();
		return ret;
        }
}
