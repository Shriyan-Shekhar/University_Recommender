package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;


public class T1Analysis {
    public ObservableList<QSItem> tableList = FXCollections.observableArrayList();

    /**
     * Constructor for T1Analysis Class
     * Filters the QSList items based on the given year by the user
     * Assigns this filtered list to an ObservableList tableList which is used to set the table view
     * @author Malav Daftary
     * @param year The year selected by the user which is used to filter QSList
     */
    T1Analysis (String year) {
        List <QSItem> tList = QSList.list.stream().filter(qsItem -> qsItem.getYear().equals(year)).collect(Collectors.toList());
        tableList = FXCollections.observableArrayList(tList);
    }

    /**
     * @return The filtered QSList items to be set as the table
     */
    ObservableList<QSItem> getTableList() {
        return tableList;
    }

    /**
     * Traverses through tableList and retrieves the score according to the property value chosen by the user
     * If the score is empty then that university is ignored in the calculation
     * The score for each university is summed according to the property value selected and added to a hash map
     * The hash map is then traversed over, and the values are used to create the final pie chart data values which are sorted
     * For the property "country", only the highest 8 will be calculated individually and the rest will be categorised as others
     * This is done to better the formatting and the interface of the pie chart
     * @author Malav Daftary
     * @param searchName The property selected by the user which is used as the filter for the pie chart data
     * @return An ObservableList of pie chart data points to be used for creating the chart
     */
    ObservableList<PieChart.Data> getPieChartData(String searchName) {
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList();
        Map<String, Double> scoreMap = new HashMap<>();

        // Iterate over the tableList
        for (QSItem item : tableList) {
            // Get the attribute value based on searchName
            String attributeValue = item.getProperty(searchName);
            // Get the score of the item
            // Add the score to the corresponding attribute value in the map
            if (!item.getScore().isEmpty()) {
                Double score = Double.valueOf(item.getScore());
                scoreMap.put(attributeValue, (Double)scoreMap.getOrDefault(attributeValue, 0.0) + score);
            }
        }

        // Convert the map to PieChart.Data and add to pieChartData
        for (Map.Entry<String, Double> entry : scoreMap.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey() + ": " + Math.round(entry.getValue()), Math.round(entry.getValue())));
        }

        // Sort the list based on the pie chart data values
        Collections.sort(pieChartData, (o1, o2) -> Double.compare(o2.getPieValue(), o1.getPieValue()));


        if (pieChartData.size() > 9){
            int otherScore = 0;
            for (int i = 8; i < pieChartData.size(); i++){
                otherScore += pieChartData.get(i).getPieValue();
            }

            pieChartData.remove(8, pieChartData.size());

            pieChartData.add(new PieChart.Data("Others: " + otherScore, otherScore));

        }

        return pieChartData;
    }

    /**
     * Traverses through tableList and retrieves the score according to the property value chosen by the user
     * If the score is empty then that university is ignored in the calculation
     * A hash map "countMap" is used to keep track of the number of universities for the property values
     * The score for each university is averaged according to the property value selected and the count value from the countMap
     * The averaged scores are stored in the hash map "scoreMap"
     * The hash map is then traversed over, and the values are used to create the final bar chart data values which are sorted
     * For the property "country", only the highest 19 will be calculated individually and the rest will be categorised as others
     * This is done to better the formatting and the interface of the bar chart
     * @author Malav Daftary
     * @param searchName The property selected by the user which is used as the filter for the bar chart data
     * @return A series of bar chart data points to be used for creating the chart
     */
    XYChart.Series<String, Double> getBarChartData(String searchName) {
        XYChart.Series<String, Double> barData= new XYChart.Series<>();

        Map<String, Double> scoreMap = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();

        // Iterate over the tableList
        for (QSItem item : tableList) {
            // Get the attribute value based on searchName
            String attributeValue = item.getProperty(searchName);
            // Get the score of the item
            // Add the score to the corresponding attribute value in the map
            if (!item.getScore().isEmpty()) {
                countMap.put(attributeValue, countMap.getOrDefault(attributeValue, 0) + 1);

                Double score = Double.valueOf(item.getScore());
                scoreMap.put(attributeValue, ((Double)scoreMap.getOrDefault(attributeValue, 0.0) * (countMap.get(attributeValue)-1) + score)/countMap.get(attributeValue));
            }
        }

        // Convert the map to PieChart.Data and add to pieChartData
        for (Map.Entry<String, Double> entry : scoreMap.entrySet()) {
            barData.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Sort the list based on the bar chart data values
        Collections.sort(barData.getData(), (o1, o2) -> Double.compare(o2.getYValue().doubleValue(), o1.getYValue().doubleValue()));


        if (barData.getData().size() > 20){
            double otherScore = 0;
            int count = 0;
            for (int i = 19; i < barData.getData().size(); i++){
                int tempcount = countMap.get(barData.getData().get(i).getXValue());
                otherScore = ((otherScore * count + barData.getData().get(i).getYValue() * tempcount) / (tempcount + count));
            }

            barData.getData().remove(19, barData.getData().size());

            barData.getData().add(new XYChart.Data<>("Others", ( (double)otherScore)));

        }

        return barData;
    }


}
