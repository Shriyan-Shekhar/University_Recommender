package comp3111.qsproject;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.*;

class T1AnalysisTest {

    @Test
    void PieChartDatatestSize() {
        QSList.initialize();
        String year = "2019";

        T1Analysis tester = new T1Analysis(year);
        ObservableList<PieChart.Data> ExpectedpieChartData= FXCollections.observableArrayList();

        ExpectedpieChartData.add(new PieChart.Data("L: 9651", parseLong("9651")));
        ExpectedpieChartData.add(new PieChart.Data("XL: 5980", parseLong("5980")));
        ExpectedpieChartData.add(new PieChart.Data("M: 2972", parseLong("2972")));
        ExpectedpieChartData.add(new PieChart.Data("S: 888", parseLong("888")));

        ObservableList<PieChart.Data> ActualData = tester.getPieChartData("size");
        for (int i = 0; i < 4; i++){
            Assertions.assertEquals(ExpectedpieChartData.get(i).getPieValue(),ActualData.get(i).getPieValue());
        }
    }

    @Test
    void BarChartDatatestSize() {
        QSList.initialize();
        String year = "2017";

        T1Analysis tester = new T1Analysis(year);
        XYChart.Series<String, Double> ExpectedBarChartData = new XYChart.Series<>();

        ExpectedBarChartData.getData().add(new XYChart.Data<>("XL", 54.549137931034494));
        ExpectedBarChartData.getData().add(new XYChart.Data<>("L", 51.407462686567186));
        ExpectedBarChartData.getData().add(new XYChart.Data<>("S", 51.25294117647061));
        ExpectedBarChartData.getData().add(new XYChart.Data<>("M", 46.89230769230767));

        XYChart.Series<String, Double> ActualData = tester.getBarChartData("size");
        for (int i = 0; i < 4; i++){
            Assertions.assertEquals(ExpectedBarChartData.getData().get(i).getYValue(),ActualData.getData().get(i).getYValue());
        }
    }

}