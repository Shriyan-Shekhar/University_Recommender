package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class T21AnalysisTest {
    @Test
    void calculateRegular() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        String uni_1 = "Brown University";
        List<QSItem> university1 = QSList.list.stream().filter(qsItem -> qsItem.getName().equals(uni_1) && years.contains(qsItem.year)).collect(Collectors.toList());

        ObservableList<QSItem> University1List = FXCollections.observableArrayList(university1);
        T21Analysis t21Analysis = new T21Analysis(uni_1, uni_1, years);
        double expectedScore = (77.8 + 76.5 + 72.0) / 3.0;
        double actualScore = t21Analysis.calculate(University1List, "score");
        assertEquals(expectedScore, actualScore);
    }

    @Test
    void calculateInvalidSearch() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        String uni_1 = "Aalborg University";
        List<QSItem> university1 = QSList.list.stream().filter(qsItem -> qsItem.getName().equals(uni_1) && years.contains(qsItem.year)).collect(Collectors.toList());

        ObservableList<QSItem> University1List = FXCollections.observableArrayList(university1);
        T21Analysis t21Analysis = new T21Analysis(uni_1, uni_1, years);
        double actual = t21Analysis.calculate(University1List, null);
        double expected = 0.0;
        assertEquals(actual, expected);
    }

    @Test
    void calculateInvalidList() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2023");
        String uni_1 = "Cornell University";
        List<QSItem> university1 = QSList.list.stream().filter(qsItem -> qsItem.getName().equals(uni_1) && years.contains(qsItem.year)).collect(Collectors.toList());

        ObservableList<QSItem> University1List = FXCollections.observableArrayList(university1);
        T21Analysis t21Analysis = new T21Analysis(uni_1, uni_1, years);
        double actual = t21Analysis.calculate(University1List, "random");
        double expected = 0.0;
        assertEquals(actual, expected);
    }


    @Test
    void getBarInvalidUniversity() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");

        String searchName = "score";

        T21Analysis t21Analysis = new T21Analysis("c1", "c2", years);
        XYChart.Series<Double, String> barchart = t21Analysis.getBarChartData(searchName);
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

        T21Analysis t21Analysis = new T21Analysis("Cornell University", "Brown University", years);
        XYChart.Series<Double, String> barchart = t21Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(0.0, "Brown University"));
        expected.getData().add(new XYChart.Data<>(0.0, "Cornell University"));

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

        String searchName = "wrongSearchName";

        T21Analysis t21Analysis = new T21Analysis("Charles University", "Johns Hopkins University", years);
        XYChart.Series<Double, String> barchart = t21Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(0.0, "Johns Hopkins University"));
        expected.getData().add(new XYChart.Data<>(0.0, "Charles University"));

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
        double BostonData = 2642.0;
        double AstonData = 730.0;

        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");

        String searchName = "facultyCount";
        T21Analysis t21Analysis = new T21Analysis("Aston University", "Boston University", years);
        XYChart.Series<Double, String> barchart = t21Analysis.getBarChartData(searchName);
        XYChart.Series<Double, String> expected = new XYChart.Series<>();

        expected.getData().add(new XYChart.Data<>(BostonData, "Boston University"));
        expected.getData().add(new XYChart.Data<>(AstonData, "Aston University"));

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
        years.add("2020");

        double NorthEastern2017 = 32.4;
        double NorthEastern2020 = 31.7;
        double Aston2017 = 32.7;

        String searchName = "score";
        T21Analysis t21Analysis = new T21Analysis("Northeastern University", "Aston University", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("2017", NorthEastern2017));
        series1.getData().add(new XYChart.Data<>("2020", NorthEastern2020));
        series2.getData().add(new XYChart.Data<>("2017", Aston2017));


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
        T21Analysis t21Analysis = new T21Analysis("Nanyang Technological University, Singapore (NTU)", "Northeastern University", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(searchName);
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
        years.add("2023");
        years.add("2017");
        years.add("2018");

        double NTU2017 = 91.4;
        double NTU2018 = 92.2;
        double NE2017 = 32.4;
        double NE2018 = 33.9;

        String searchName = "score";
        T21Analysis t21Analysis = new T21Analysis("Nanyang Technological University, Singapore (NTU)", "Northeastern University", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(searchName);
        List<XYChart.Series<String, Double>> expectedData = new ArrayList<>();
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        series1.getData().add(new XYChart.Data<>("2017", NTU2017));
        series1.getData().add(new XYChart.Data<>("2018", NTU2018));
        series2.getData().add(new XYChart.Data<>("2017", NE2017));
        series2.getData().add(new XYChart.Data<>("2018", NE2018));

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
        T21Analysis t21Analysis = new T21Analysis("Waseda University", "Yonsei University", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(searchName);
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
    void getLineInvalidSearchName2() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2017");
        years.add("2018");
        years.add("2020");



        T21Analysis t21Analysis = new T21Analysis("Waseda University", "Yonsei University", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(null);
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
    void getLineInvalidUniversities() {
        QSList.initialize();
        List<String> years = new ArrayList<>();
        years.add("2018");
        years.add("2021");
        years.add("2020");


        String searchName = "score";
        T21Analysis t21Analysis = new T21Analysis("IncorrectUniversity2", "IncorrectUniversity1", years);
        List<XYChart.Series<String, Double>> linechart = t21Analysis.getLineChartData(searchName);
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