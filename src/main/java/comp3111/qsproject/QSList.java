package comp3111.qsproject;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * This file is a container of collected QS data
 */

public class QSList {
    public static ObservableList<QSItem> list = FXCollections.observableArrayList();
    public static ObservableList<String> university = FXCollections.observableArrayList();
    public static ObservableList<String> type = FXCollections.observableArrayList();
    public static ObservableList<String> region = FXCollections.observableArrayList();
    public static ObservableList<String> country = FXCollections.observableArrayList();
    public static ObservableList<String> link = FXCollections.observableArrayList();

    /**
     * Finding QS data path, initializing and processing the data and finally, assigning the data
     * @author Shriyan Shekhar
     */
    public static void initialize() {
        try {
            list = CSVToList("qs.csv");
            collectData();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
            Your Code Here.
            1. Load the csv into list.
            2. Collect the set of university, type, region and country.
         */
    }

    /**
     * Converts and processes a CSV file to a list of QSItems objects.
     * @author Shriyan Shekhar
     * @param filePath Path to the CSV file
     * @return An ObservableList of QSItem objects reflecting the data from the CSV file.
     * @throws IOException If I/O error occurs while reading the CSV file.
     */
    private static ObservableList<QSItem> CSVToList(String filePath) throws IOException {
        ObservableList<QSItem> dataList = FXCollections.observableArrayList();;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String [] parsed = new String[15];;
                int j = 0;
                for (int i = 0; i < data.length; i++){
                    if (data[i].contains("\"") == true) {
                        parsed[j++] = data[i] + "," + data[i + 1];
                        i = i+1;
                    }
                    else{
                        parsed[j++] = data [i];
                    }
                }
                parsed [0] = parsed [0].replace ("\"", "").trim();
                parsed [5] = parsed[5].replace("\"","");
                QSItem item = new QSItem(parsed);
                dataList.add(item);
            }
        }

        return dataList;
    }

    /**
     * Collects unique values from the list of QSItem Objects
     * Processes it for university, type, region and country attributes.
     * @author Shriyan Shekhar
     */
    private static void collectData() {


        for (QSItem item : list) {
            university.add(item.getName());
            type.add(item.getType());
            region.add(item.getRegion());
            country.add(item.getCountry());
        }

        Set<String> uniqueUniversities = new HashSet<>(university);
        Set<String> uniqueTypes = new HashSet<>(type);
        Set<String> uniqueRegions = new HashSet<>(region);
        Set<String> uniqueCountries = new HashSet<>(country);

        university.clear();
        type.clear();
        region.clear();
        country.clear();

        university.addAll(uniqueUniversities);
        type.addAll(uniqueTypes);
        region.addAll(uniqueRegions);
        country.addAll(uniqueCountries);

    }
}
