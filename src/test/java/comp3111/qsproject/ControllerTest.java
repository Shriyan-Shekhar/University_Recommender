package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    @BeforeAll
    public static void startJavaFXRuntime() {
        Platform.startup( () -> {});
    }

    @Test
    public void testInitialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        // Add more assertions to validate other elements in the Controller class
        assertEquals("2017",controller.t1YearChoiceBox.getValue());
    }

    @Test
    public void testResetTask3() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        controller.initialize();

        controller.t3RegionChoiceBox.setValue("All");
        controller.t3TypeChoiceBox.setValue("Private");
        controller.t3TopRankTextField.setText("1");
        controller.t3BottomRankTextField.setText("5");

        controller.T3_onClickRecommend();
        controller.T3_onClickClear();

        assertEquals(null, controller.t3RegionChoiceBox.getValue());
    }

    @Test
    public void testReset() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);

        controller.T21_onClickCompare();
        controller.T21_onClickClear();

        // Add more assertions to validate other elements in the Controller class
        assertEquals(false,controller.t22017CheckBox.isSelected());
    }

    @Test
    public void testT1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t1PieChartChoiceBox.setValue("country");
        controller.T1_onClickSearch();

        assertEquals("country & score",controller.t1PieChartLabel.getText());

        // Add more assertions to validate other elements in the Controller class

    }
    @Test
    public void testReset2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);

        controller.T22_onClickCompare();
        controller.T22_onClickClear();

        // Add more assertions to validate other elements in the Controller class
        assertEquals(false,controller.t22017CheckBox2.isSelected());
    }

    @Test
    public void testRecommend() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t3RegionChoiceBox.setValue("All");
        controller.t3TypeChoiceBox.setValue("Private");
        controller.t3TopRankTextField.setText("1");
        controller.t3BottomRankTextField.setText("2");

        controller.T3_onClickRecommend();
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        RecommendItem actualItem1 = new RecommendItem(QSList.list.get(0));
        RecommendItem actualItem2 = new RecommendItem(QSList.list.get(1));
        actualItem1.setRecentRank("1");
        actualItem1.setRecentYear("2022");
        actualItem1.setBestYear("2017");
        actualItem1.setBestRank("1");
        actualItem2.setRecentRank("2");
        actualItem2.setRecentYear("2021");
        actualItem2.setBestYear("2017");
        actualItem2.setBestRank("2");
        recommendListExpected.add(actualItem1);
        recommendListExpected.add(actualItem2);
        Assertions.assertEquals(controller.t3TableView.getItems().size(), recommendListExpected.size());
        for (int i = 0; i < recommendListExpected.size(); i++){
            RecommendItem item_actual = controller.t3TableView.getItems().get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected.getBestRank(), item_actual.getBestRank());
            Assertions.assertEquals(item_expected.getBestYear(), item_actual.getBestYear());
            Assertions.assertEquals(item_expected.getRecentRank(), item_actual.getRecentRank());
            Assertions.assertEquals(item_expected.getRecentYear(), item_actual.getRecentYear());
        }
    }
    @Test
    public void HandleCombo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);


        controller.T22_onClickCompare();
        controller.FieldSelect2.setValue("Faculty Count");
        controller.HandleCombo2();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Faculty Count",controller.t22OverallBarChart.getXAxis().getLabel());
        controller.ClearTask22();
    }

    @Test
    public void HandleCombo2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);


        controller.T22_onClickCompare();
        controller.FieldSelect2.setValue("Rank");
        controller.HandleCombo2();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Rank",controller.t22OverallBarChart.getXAxis().getLabel());
        controller.ClearTask22();
    }
    @Test
    public void HandleCombo3() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);


        controller.T22_onClickCompare();
        controller.FieldSelect2.setValue("International Student");
        controller.HandleCombo2();



        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. International Students",controller.t22OverallBarChart.getXAxis().getLabel());
        controller.ClearTask22();
    }

    @Test
    public void HandleCombo4() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);


        controller.T22_onClickCompare();
        controller.FieldSelect2.setValue("Score");
        controller.HandleCombo2();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Score",controller.t22OverallBarChart.getXAxis().getLabel());
        controller.ClearTask22();
    }

    @Test
    public void HandleCombo5() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2CountryRegion2ChoiceBox.setValue("North America");
        controller.t2CountryRegion1ChoiceBox.setValue("All");
        controller.t22017CheckBox2.setSelected(true);


        controller.T22_onClickCompare();
        controller.FieldSelect2.setValue("Student Faculty Ratio");
        controller.HandleCombo2();



        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Student Faculty Ratio",controller.t22OverallBarChart.getXAxis().getLabel());
        controller.ClearTask22();
    }

    @Test
    public void HandleCombo21() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);


        controller.T21_onClickCompare();
        controller.FieldSelect.setValue("Faculty Count");
        controller.HandleCombo();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Faculty Count",controller.t21OverallBarChart.getXAxis().getLabel());
        controller.clearTask21();
    }

    @Test
    public void HandleCombo22() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);


        controller.T21_onClickCompare();
        controller.FieldSelect.setValue("Rank");
        controller.HandleCombo();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Rank",controller.t21OverallBarChart.getXAxis().getLabel());
    }
    @Test
    public void HandleCombo23() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);


        controller.T21_onClickCompare();
        controller.FieldSelect.setValue("International Student");
        controller.HandleCombo();


        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. International Students",controller.t21OverallBarChart.getXAxis().getLabel());
    }

    @Test
    public void HandleCombo24() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);


        controller.T21_onClickCompare();
        controller.FieldSelect.setValue("Score");
        controller.HandleCombo();

        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Score",controller.t21OverallBarChart.getXAxis().getLabel());
    }

    @Test
    public void HandleCombo25() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t2University1ChoiceBox.setValue("Cornell University");
        controller.t2University2ChoiceBox.setValue("Cornell University");
        controller.t22017CheckBox.setSelected(true);


        controller.T21_onClickCompare();
        controller.FieldSelect.setValue("Student Faculty Ratio");
        controller.HandleCombo();

        // Add more assertions to validate other elements in the Controller class
        assertEquals("Avg. Student Faculty Ratio",controller.t21OverallBarChart.getXAxis().getLabel());
    }

    @Test
    public void Task3() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ui.fxml"));
        fxmlLoader.load();
        Controller controller = fxmlLoader.getController();

        // Verify that the choice boxes are initialized correctly
        controller.initialize();

        controller.t3TopRankTextField.setText("1");
        controller.t3BottomRankTextField.setText ("500");
        controller.t3TypeChoiceBox.setValue ("Public");
        controller.t3RegionChoiceBox.setValue ("Asia");

        controller.T3_onClickRecommend();

        // Add more assertions to validate other elements in the Controller class
        assertEquals("",controller.errorT3.getText());
    }
}

