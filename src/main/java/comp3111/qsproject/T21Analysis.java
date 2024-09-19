package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;

public class T21Analysis {
    public ObservableList<QSItem> University1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> University2List = FXCollections.observableArrayList();
    private String University1Name;
    private String University2Name;


    /**
     *  Constructor for T21Analysis.
     *  Filters the QSList based on the provided university names and years,
     *  sorts the filtered data by year, and creates observable lists for each university.
     *  Assigns the sorted observable lists to teh first university list (University1List) and second university list (University2List) respectively
     *  Assigns the name of the universities to the respective strings
     * @author Shriyan Shekhar
     * @param uni_1 The name of the first university.
     * @param uni_2 The name of the second university.
     * @param years A list of years to filter the QSList by.
     */
    T21Analysis(String uni_1, String uni_2, List<String> years) {
        List <QSItem> university1 = QSList.list.stream().filter (qsItem -> qsItem.getName().equals(uni_1) && years.contains(qsItem.year)).collect(Collectors.toList());
        List <QSItem> university2 = QSList.list.stream().filter (qsItem -> qsItem.getName().equals(uni_2) && years.contains(qsItem.year)).collect(Collectors.toList());

        university1.sort(Comparator.comparing (QSItem::getYear));
        university2.sort(Comparator.comparing (QSItem::getYear));

        University1List = FXCollections.observableArrayList(university1);
        University2List = FXCollections.observableArrayList(university2);
        University1Name = uni_1;
        University2Name = uni_2;
    }

    /**
     * Fetches the respective data based on searchName
     * Calculates the average of the list given according to the property selected by searchName.
     * @author Shriyan Shekhar
     * @param UniversityList The list of QSItem objects representing one university's data
     * @param searchName The name of the property required for calculation
     * @return The average value of the specified property in the QSItem object or returns 0.0 if list is empty or null.
     */
    public double calculate (ObservableList<QSItem> UniversityList , String searchName){
        double sum = 0.0;
        double length = 0.0;

        if (searchName == null){
            return sum;
        }


        for (QSItem qsItem: UniversityList) {
            String scoreString = "";
            scoreString = qsItem.getProperty(searchName);

            if (scoreString == null){
                continue;
            }
            else if (scoreString.isEmpty()){
                continue;
            }

            scoreString = scoreString.replaceAll(",", "");
            scoreString = scoreString.replaceAll("\"", "");

            if (searchName.equals("score") == false){
                scoreString = scoreString.replaceAll("\\.", "");
            }



            double summation;
            try {
                summation = Double.parseDouble(scoreString);

            } catch (NumberFormatException e) {
                summation = 0.0;
            }

            sum += summation;

            length++;
        }


        if (length == 0.0){
            return sum;
        }

        return sum / length;
    }

    /**
     * Retrieves Bar Chart Data for a property (given by searchName) in the two university data.
     * @author Shriyan Shekhar
     * @param searchName Name of the property to be retrieved for bar chart data
     * @return A series of bar chart data points in which each point represents the average value of the property.
     */
    XYChart.Series<Double, String> getBarChartData(String searchName) {
        XYChart.Series<Double, String> barData= new XYChart.Series<>();
        double displayAverage1 = calculate (University1List, searchName);
        double displayAverage2 = calculate (University2List, searchName);

        barData.getData().add(new XYChart.Data<>(displayAverage2, University2Name));
        barData.getData().add(new XYChart.Data<>(displayAverage1, University1Name));



        return barData;
    }

    /**
     * Retrieves Line Chart Data for a property (given by searchName) in the two university data.
     * @author Shriyan Shekhar
     * @param searchName Name of the property to be retrieved for line chart data
     * @return A list of series of line chart data points in which each point has a year and value of the property of the university.
     */
    List<XYChart.Series<String, Double>> getLineChartData(String searchName) {
        List<XYChart.Series<String, Double>> lineData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        series1.setName(University1Name);
        series2.setName(University2Name);

        if (searchName == null){
            lineData.add(series1);
            lineData.add(series2);
            return lineData;
        }

        for (QSItem qsItem: University1List) {
            String year = qsItem.getYear();
            String scoreString = qsItem.getProperty(searchName);

            if (scoreString == null){
                continue;
            }
            else if (scoreString.isEmpty()){
                continue;
            }

            scoreString = scoreString.replaceAll(",", "");
            scoreString = scoreString.replaceAll("\"", "");

            if (searchName.equals("score") == false){
                scoreString = scoreString.replaceAll("\\.", "");
            }


            double score;
            try {
                score = Double.parseDouble(scoreString);
            } catch (NumberFormatException e) {
                score = 0.0; // Default value for invalid scores
            }

            series1.getData().add(new XYChart.Data<>(year, score));
        }

        for (QSItem qsItem2: University2List) {
            String year = qsItem2.getYear();
            String scoreString = qsItem2.getProperty(searchName);

            if (scoreString == null){
                continue;
            }
            else if (scoreString.isEmpty()){
                continue;
            }
            scoreString = scoreString.replaceAll(",", "");
            scoreString = scoreString.replaceAll("\"", "");

            if (searchName.equals("score") == false){
                scoreString = scoreString.replaceAll("\\.", "");
            }


            double score;
            try {
                score = Double.parseDouble(scoreString);
            } catch (NumberFormatException e) {
                score = 0.0; // Default value for invalid scores
            }

            series2.getData().add(new XYChart.Data<>(year, score));
        }

        lineData.add(series1);
        lineData.add(series2);


        return lineData;
    }
}
