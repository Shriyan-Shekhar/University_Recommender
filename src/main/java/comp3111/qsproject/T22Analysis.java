package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;

public class T22Analysis {
    public ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList();
    public ObservableList<QSItem> CountryRegion2List = FXCollections.observableArrayList();
    private String CountryRegion1Name;
    private String CountryRegion2Name;

    /**
     * Constructor for T22Analysis.
     * Filters the QSList based on the provided country/region names or "All" and years,
     * sorts the filtered data by year, and creates observable lists for each country/region.
     * Assigns the sorted observable lists to teh first country/region list (CountryRegion1List) and second country/region  list (CountryRegion2List) respectively
     * Assigns the name of the country/region to the respective strings
     * @author Shriyan Shekhar
     * @param country_region_1 The name of the first country/region.
     * @param country_region_2 The name of the second country/region.
     * @param years A list of years to filter the QSList by.
     */
    T22Analysis(String country_region_1, String country_region_2, List<String> years) {
        List<QSItem> country1 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(country_region_1) || qsItem.getRegion().equals(country_region_1)) && years.contains(qsItem.getYear()))
                .collect(Collectors.toList());
        List<QSItem> country2 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(country_region_2) || qsItem.getRegion().equals(country_region_2)) && years.contains(qsItem.getYear()))
                .collect(Collectors.toList());

        if (country_region_1!=null) {
            if (country_region_1.equals("All")) {
                country1 = QSList.list.stream().filter(qsItem -> (years.contains(qsItem.getYear()))).collect(Collectors.toList());
            }
        }

        if (country_region_2!=null) {
            if (country_region_2.equals("All")) {
                country2 = QSList.list.stream().filter(qsItem -> (years.contains(qsItem.getYear()))).collect(Collectors.toList());
            }
        }

        country1.sort(Comparator.comparing (QSItem::getYear));
        country2.sort(Comparator.comparing (QSItem::getYear));

        CountryRegion1List = FXCollections.observableArrayList(country1);
        CountryRegion2List = FXCollections.observableArrayList(country2);
        CountryRegion1Name = country_region_1;
        CountryRegion2Name = country_region_2;

    }

    /**
     * Fetches the respective data based on searchName
     * Calculates the average of the list given according to the property selected by searchName.
     * @author Shriyan Shekhar
     * @param CountryList The list of QSItem objects representing one country/region's data
     * @param searchName The name of the property required for calculation
     * @return The average value of the specified property in the QSItem object or returns 0.0 if list is empty or null.
     */
    public double calculate (ObservableList<QSItem> CountryList , String searchName){
        double sum = 0.0;
        double length = 0.0;

        if (searchName == null){
            return sum;
        }

        for (QSItem qsItem: CountryList) {
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

            if (!Objects.equals(searchName, "score")){
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
     * Retrieves Bar Chart Data for a property (given by searchName) in the two country/region data.
     * @author Shriyan Shekhar
     * @param searchName Name of the property to be retrieved for bar chart data
     * @return A series of bar chart data points in which each point represents the average value of the property.
     */
    XYChart.Series<Double, String> getBarChartData(String searchName) {
        XYChart.Series<Double, String> barData= new XYChart.Series<>();
        double countryAverage1 = calculate (CountryRegion1List, searchName);
        double countryAverage2 = calculate (CountryRegion2List, searchName);


        barData.getData().add(new XYChart.Data<>(countryAverage2, CountryRegion2Name));
        barData.getData().add(new XYChart.Data<>(countryAverage1, CountryRegion1Name));

        return barData;
    }

    /**
     * Retrieves Line Chart Data for a property (given by searchName) in the two country/region data.
     * @author Shriyan Shekhar
     * @param searchName Name of the property to be retrieved for line chart data
     * @return A list of series of line chart data points in which each point has a year and the average value of the property of the country/region.
     */
    List<XYChart.Series<String, Double>> getLineChartData(String searchName) {
        List<XYChart.Series<String, Double>> lineData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        series1.setName(CountryRegion1Name);
        series2.setName(CountryRegion2Name);

        List <String> yearsSelected = new ArrayList<>();

        for (QSItem qsItem: CountryRegion1List){
            yearsSelected.add(qsItem.getYear());
        }

        Set<String> uniqueYears = new HashSet<>(yearsSelected);
        yearsSelected.clear();
        yearsSelected.addAll(uniqueYears);
        Collections.sort(yearsSelected);

        ObservableList<QSItem> country1Values = FXCollections.observableArrayList();
        for (String year: yearsSelected){
            List <QSItem> filtered = CountryRegion1List.stream().filter(qsItem -> (qsItem.getYear().contains(year))).collect(Collectors.toList());
            country1Values.clear();
            country1Values.addAll(filtered);
            double countryAverage1 = calculate (country1Values,searchName);
            series1.getData().add(new XYChart.Data<>(year, countryAverage1));
        }

        List <String> yearsSelected2 = new ArrayList<>();

        for (QSItem qsItem: CountryRegion2List){
            yearsSelected2.add(qsItem.getYear());
        }

        Set<String> uniqueYears2 = new HashSet<>(yearsSelected2);
        yearsSelected2.clear();
        yearsSelected2.addAll(uniqueYears2);
        Collections.sort(yearsSelected2);

        ObservableList<QSItem> country2Values = FXCollections.observableArrayList();
        for (String year: yearsSelected2){
            List <QSItem> filtered = CountryRegion2List.stream().filter(qsItem -> (qsItem.getYear().contains(year))).collect(Collectors.toList());
            country2Values.clear();
            country2Values.addAll(filtered);
            double countryAverage2 = calculate (country2Values,searchName);

            series2.getData().add(new XYChart.Data<>(year, countryAverage2));
        }


        lineData.add(series1);
        lineData.add(series2);

        return lineData;
    }
}
