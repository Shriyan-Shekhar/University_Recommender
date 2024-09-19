package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class T22AnalysisTest {

    @Test
    void calculateRegular() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        String country = "Estonia";
        List<QSItem> country1 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(country) || qsItem.getRegion().equals(country)) && years.contains(qsItem.getYear()))
                .collect(Collectors.toList());

        ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList(country1);
        T22Analysis t22Analysis = new T22Analysis("Estonia", "Estonia", years);
        double expectedScore = (33.6 + 36.3 + 32.8) / 3.0;
        double actualScore = t22Analysis.calculate(CountryRegion1List, "score");
        assertEquals(expectedScore, actualScore);
    }

    @Test
    void calculateInvalidSearch() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        String country = "Japan";
        List<QSItem> country1 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(country) || qsItem.getRegion().equals(country)) && years.contains(qsItem.getYear()))
                .collect(Collectors.toList());
        ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList(country1);
        T22Analysis t22Analysis = new T22Analysis("Japan", "Estonia", years);
        double actual = t22Analysis.calculate(CountryRegion1List, "wrongSearchName");
        double expected = 0.0;
        assertEquals(actual, expected);
    }

    @Test
    void calculateInvalidList() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2023");
        String country = "Japan";
        List<QSItem> country1 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(country) || qsItem.getRegion().equals(country)) && years.contains(qsItem.getYear()))
                .collect(Collectors.toList());
        ObservableList<QSItem> CountryRegion1List = FXCollections.observableArrayList(country1);
        T22Analysis t22Analysis = new T22Analysis("Japan", "Estonia", years);
        double actual = t22Analysis.calculate(CountryRegion1List, "random");
        double expected = 0.0;
        assertEquals(actual, expected);
    }


    @Test
    void getBarInvalidCountry() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");

        String searchName = "score";

        T22Analysis t22Analysis = new T22Analysis("c1", "c2", years);
        XYChart.Series<Double, String> barchart = t22Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(0.0, "c2"));
        expected.getData().add(new XYChart.Data<>(0.0, "c1"));

        assertEquals(expected.getData().size(), barchart.getData().size());
        for (int i = 0; i < expected.getData().size(); i++) {
            XYChart.Data<Double, String> expectedData = expected.getData().get(i);
            XYChart.Data<Double, String> actualData = barchart.getData().get(i);
            assertEquals(expectedData.getXValue(), actualData.getXValue());
            assertEquals(expectedData.getYValue(), actualData.getYValue());
        }

    }

    @Test
    void getBarInvalidYear() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2025");
        years.add("2023");
        years.add("2024");

        String searchName = "score";

        T22Analysis t22Analysis = new T22Analysis("North America", "Europe", years);
        XYChart.Series<Double, String> barchart = t22Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(0.0, "Europe"));
        expected.getData().add(new XYChart.Data<>(0.0, "North America"));

        assertEquals(expected.getData().size(), barchart.getData().size());
        for (int i = 0; i < expected.getData().size(); i++) {
            XYChart.Data<Double, String> expectedData = expected.getData().get(i);
            XYChart.Data<Double, String> actualData = barchart.getData().get(i);
            assertEquals(expectedData.getXValue(), actualData.getXValue());
            assertEquals(expectedData.getYValue(), actualData.getYValue());
        }
    }

    @Test
    void getBarInvalidSearchName() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");


        T22Analysis t22Analysis = new T22Analysis("Estonia", "India", years);
        XYChart.Series<Double, String> barchart = t22Analysis.getBarChartData(null);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(0.0, "India"));
        expected.getData().add(new XYChart.Data<>(0.0, "Estonia"));

        assertEquals(expected.getData().size(), barchart.getData().size());
        for (int i = 0; i < expected.getData().size(); i++) {
            XYChart.Data<Double, String> expectedData = expected.getData().get(i);
            XYChart.Data<Double, String> actualData = barchart.getData().get(i);
            assertEquals(expectedData.getXValue(), actualData.getXValue());
            assertEquals(expectedData.getYValue(), actualData.getYValue());
        }
    }

    @Test
    void getBarChart() {
        QSList.initialize();
        double calculatedExcelAll = 6368256.0 / 2393.0;
        double IndiaData = 29460.0 / 41.0;

        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");

        String searchName = "facultyCount";
        T22Analysis t22Analysis = new T22Analysis("All", "India", years);
        XYChart.Series<Double, String> barchart = t22Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(IndiaData, "India"));
        expected.getData().add(new XYChart.Data<>(calculatedExcelAll, "All"));

        assertEquals(expected.getData().size(), barchart.getData().size());
        for (int i = 0; i < expected.getData().size(); i++) {
            XYChart.Data<Double, String> expectedData = expected.getData().get(i);
            XYChart.Data<Double, String> actualData = barchart.getData().get(i);
            assertEquals(expectedData.getXValue(), actualData.getXValue());
            assertEquals(expectedData.getYValue(), actualData.getYValue());
        }
    }

    @Test
    void getLineChartData() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");

        double SouthKorea2017 = (82.1 + 77.9 + 67.0 + 62.8 + 61.5 + 60.5 + 50.6 + 39.2 + 34.5 + 34.3 + 30.9) / 11.0;
        double SouthKorea2018 = (81.5 + 80.4 + 69.4 + 65.5 + 62.3 + 61.5 + 53.2 + 40.6 + 37.7 + 34.7 + 30.6) / 11.0;

        double Austria2017 = (53.3 + 48.8 + 36.9) / 3.0;
        double Austria2018 = (53.3 + 49.5 + 38.5) / 3.0;

        String searchName = "score";
        T22Analysis t22Analysis = new T22Analysis("South Korea", "Austria", years);
        List<XYChart.Series<String, Double>> linechart = t22Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("2017", SouthKorea2017));
        series1.getData().add(new XYChart.Data<>("2018", SouthKorea2018));
        series2.getData().add(new XYChart.Data<>("2017", Austria2017));
        series2.getData().add(new XYChart.Data<>("2018", Austria2018));

        expectedData.add(series1);
        expectedData.add(series2);

        assertEquals(linechart.size(), expectedData.size());

        for (int i = 0; i < linechart.size(); i++) {
            XYChart.Series<String, Double> actualSeries = linechart.get(i);
            XYChart.Series<String, Double> expectedSeries = expectedData.get(i);

            assertEquals(actualSeries.getData().size(), expectedSeries.getData().size());

            for (int j = 0; j < actualSeries.getData().size(); j++) {
                XYChart.Data<String, Double> actualData = actualSeries.getData().get(j);
                XYChart.Data<String, Double> expectedDataPoint = expectedSeries.getData().get(j);

                assertEquals(expectedDataPoint.getXValue(), actualData.getXValue());
                assertEquals(expectedDataPoint.getYValue(), actualData.getYValue());
            }
        }
    }

    @Test
    void getLineInvalidYear1() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2023");
        years.add("2024");
        years.add("2025");

        String searchName = "score";
        T22Analysis t22Analysis = new T22Analysis("South Korea", "Austria", years);
        List<XYChart.Series<String, Double>> linechart = t22Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        expectedData.add(series1);
        expectedData.add(series2);

        assertEquals(linechart.size(), expectedData.size());

        for (int i = 0; i < linechart.size(); i++) {
            XYChart.Series<String, Double> actualSeries = linechart.get(i);
            XYChart.Series<String, Double> expectedSeries = expectedData.get(i);

            assertEquals(actualSeries.getData().size(), expectedSeries.getData().size());

            for (int j = 0; j < actualSeries.getData().size(); j++) {
                XYChart.Data<String, Double> actualData = actualSeries.getData().get(j);
                XYChart.Data<String, Double> expectedDataPoint = expectedSeries.getData().get(j);

                assertEquals(expectedDataPoint.getXValue(), actualData.getXValue());
                assertEquals(expectedDataPoint.getYValue(), actualData.getYValue());
            }
        }
    }

    @Test
    void getLineInvalidYear2() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2023"); //Added Invalid Year

        double SouthKorea2017 = (82.1 + 77.9 + 67.0 + 62.8 + 61.5 + 60.5 + 50.6 + 39.2 + 34.5 + 34.3 + 30.9) / 11.0;
        double SouthKorea2018 = (81.5 + 80.4 + 69.4 + 65.5 + 62.3 + 61.5 + 53.2 + 40.6 + 37.7 + 34.7 + 30.6) / 11.0;

        double Austria2017 = (53.3 + 48.8 + 36.9) / 3.0;
        double Austria2018 = (53.3 + 49.5 + 38.5) / 3.0;

        String searchName = "score";
        T22Analysis t22Analysis = new T22Analysis("South Korea", "Austria", years);
        List<XYChart.Series<String, Double>> linechart = t22Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("2017", SouthKorea2017));
        series1.getData().add(new XYChart.Data<>("2018", SouthKorea2018));
        series2.getData().add(new XYChart.Data<>("2017", Austria2017));
        series2.getData().add(new XYChart.Data<>("2018", Austria2018));

        expectedData.add(series1);
        expectedData.add(series2);

        assertEquals(linechart.size(), expectedData.size());

        for (int i = 0; i < linechart.size(); i++) {
            XYChart.Series<String, Double> actualSeries = linechart.get(i);
            XYChart.Series<String, Double> expectedSeries = expectedData.get(i);

            assertEquals(actualSeries.getData().size(), expectedSeries.getData().size());

            for (int j = 0; j < actualSeries.getData().size(); j++) {
                XYChart.Data<String, Double> actualData = actualSeries.getData().get(j);
                XYChart.Data<String, Double> expectedDataPoint = expectedSeries.getData().get(j);

                assertEquals(expectedDataPoint.getXValue(), actualData.getXValue());
                assertEquals(expectedDataPoint.getYValue(), actualData.getYValue());
            }
        }
    }

    @Test
    void getLineInvalidSearchName() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2020");


        String searchName = "IncorrectScore";
        T22Analysis t22Analysis = new T22Analysis("India", "All", years);
        List<XYChart.Series<String, Double>> linechart = t22Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("2017", 0.0));
        series1.getData().add(new XYChart.Data<>("2018", 0.0));
        series1.getData().add(new XYChart.Data<>("2020", 0.0));

        series2.getData().add(new XYChart.Data<>("2017", 0.0));
        series2.getData().add(new XYChart.Data<>("2018", 0.0));
        series2.getData().add(new XYChart.Data<>("2020", 0.0));


        expectedData.add(series1);
        expectedData.add(series2);

        assertEquals(linechart.size(), expectedData.size());

        for (int i = 0; i < linechart.size(); i++) {
            XYChart.Series<String, Double> actualSeries = linechart.get(i);
            XYChart.Series<String, Double> expectedSeries = expectedData.get(i);

            assertEquals(actualSeries.getData().size(), expectedSeries.getData().size());

            for (int j = 0; j < actualSeries.getData().size(); j++) {
                XYChart.Data<String, Double> actualData = actualSeries.getData().get(j);
                XYChart.Data<String, Double> expectedDataPoint = expectedSeries.getData().get(j);

                assertEquals(expectedDataPoint.getXValue(), actualData.getXValue());
                assertEquals(expectedDataPoint.getYValue(), actualData.getYValue());
            }
        }
    }

    @Test
    void getLineInvalidCountries() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2020");


        String searchName = "score";
        T22Analysis t22Analysis = new T22Analysis("IncorrectCountry2", "IncorrectCountry1", years);
        List<XYChart.Series<String, Double>> linechart = t22Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        expectedData.add(series1);
        expectedData.add(series2);

        assertEquals(linechart.size(), expectedData.size());

        for (int i = 0; i < linechart.size(); i++) {
            XYChart.Series<String, Double> actualSeries = linechart.get(i);
            XYChart.Series<String, Double> expectedSeries = expectedData.get(i);

            assertEquals(actualSeries.getData().size(), expectedSeries.getData().size());

            for (int j = 0; j < actualSeries.getData().size(); j++) {
                XYChart.Data<String, Double> actualData = actualSeries.getData().get(j);
                XYChart.Data<String, Double> expectedDataPoint = expectedSeries.getData().get(j);

                assertEquals(expectedDataPoint.getXValue(), actualData.getXValue());
                assertEquals(expectedDataPoint.getYValue(), actualData.getYValue());
            }
        }
    }

}


