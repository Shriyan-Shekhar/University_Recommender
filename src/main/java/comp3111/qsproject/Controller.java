package comp3111.qsproject;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;


import java.util.*;
import java.util.stream.Collectors;

public class Controller{


    /* T1 Controller */
    public TableView<QSItem> t1DataTable;

    @FXML
    public ChoiceBox<String> t1YearChoiceBox;

    @FXML
    public BarChart<String, Double> t1BarChart;

    @FXML
    public TableColumn<QSItem, String> t1Rank;

    @FXML
    public TableColumn<QSItem, String> t1University;

    @FXML
    public TableColumn<QSItem, String> t1Score;

    @FXML
    public TableColumn<QSItem, String> t1Country;

    @FXML
    public TableColumn<QSItem, String> t1City;

    @FXML
    public TableColumn<QSItem, String> t1Type;

    @FXML
    public PieChart t1PieChart;

    @FXML
    public ChoiceBox<String> t1PieChartChoiceBox;

    @FXML
    public Label t1PieChartLabel;

    @FXML
    public ChoiceBox<String> t1BarChartChoiceBox;

    @FXML
    public Label t1BarChartLabel;

    @FXML
    public CategoryAxis t1BarChartTypeXaxis;

    /* T2 Controller */
    @FXML
    public ChoiceBox<String> t2University1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2University2ChoiceBox;

    @FXML
    public Label error1;

    @FXML
    public Label error2;
    @FXML
    public Label ChartTitle1;
    @FXML
    public Label ChartTitle2;
    @FXML
    public ComboBox<String> FieldSelect;

    @FXML
    public ComboBox<String> FieldSelect2;

    @FXML
    public ChoiceBox<String> t2CountryRegion1ChoiceBox;
    @FXML
    public ChoiceBox<String> t2CountryRegion2ChoiceBox;

    @FXML
    public CheckBox t22017CheckBox;
    @FXML
    public CheckBox t22018CheckBox;
    @FXML
    public CheckBox t22019CheckBox;
    @FXML
    public CheckBox t22020CheckBox;
    @FXML
    public CheckBox t22021CheckBox;
    @FXML
    public CheckBox t22022CheckBox;
    @FXML
    public CheckBox t22017CheckBox2;
    @FXML
    public CheckBox t22018CheckBox2;
    @FXML
    public CheckBox t22019CheckBox2;
    @FXML
    public CheckBox t22020CheckBox2;
    @FXML
    public CheckBox t22021CheckBox2;
    @FXML
    public CheckBox t22022CheckBox2;


    @FXML
    public LineChart<String, Double> t21LineChart;

    @FXML
    public BarChart<Double, String> t21OverallBarChart;

    @FXML
    public LineChart<String, Double> t22LineChart;
    @FXML
    public BarChart<Double, String> t22OverallBarChart;

    public XYChart.Series<Double, String> barChartData;
    public XYChart.Series<Double, String> barChartData2;
    public XYChart.Series<Double, String> barChartData3;
    public XYChart.Series<Double, String> barChartData4;
    public XYChart.Series<Double, String> barChartData5;
    public XYChart.Series<Double, String> barChartData21;
    public XYChart.Series<Double, String> barChartData22;
    public XYChart.Series<Double, String> barChartData23;
    public XYChart.Series<Double, String> barChartData24;
    public XYChart.Series<Double, String> barChartData25;


    /* T3 Controller */

    @FXML
    public TextField t3TopRankTextField;
    @FXML
    public TextField t3BottomRankTextField;
    @FXML
    public ChoiceBox<String> t3TypeChoiceBox;
    @FXML
    public ChoiceBox<String> t3RegionChoiceBox;
    @FXML
    public TableView<RecommendItem> t3TableView;

    @FXML
    public TableColumn<RecommendItem, String> t3University;

    @FXML
    public TableColumn<RecommendItem, String> t3BestYear;

    @FXML
    public TableColumn<RecommendItem, String> t3BestRank;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentYear;

    @FXML
    public TableColumn<RecommendItem, String> t3RecentRank;

    @FXML
    public Label errorT3;

    ObservableList<String> yearList = FXCollections.observableArrayList("2017", "2018", "2019", "2020", "2021", "2022");
    ObservableList<String> stringPropertyList = FXCollections.observableArrayList("country", "region", "size", "type", "researchOutput");


    @FXML
    public void initialize() {
        // Whole Program Information
        QSList.initialize();
        // T1
        t1YearChoiceBox.setItems(yearList);
        t1YearChoiceBox.setValue("2017");
        t1PieChartChoiceBox.setItems(stringPropertyList);
        t1PieChartChoiceBox.setValue("country");
        t1PieChartLabel.setText("");
        t1BarChartChoiceBox.setItems(stringPropertyList);
        t1BarChartChoiceBox.setValue("country");
        t1BarChartLabel.setText("");
        // T2
        ObservableList<String> sortedUniversity = QSList.university;
        sortedUniversity.sort(Comparator.naturalOrder());
        t2University1ChoiceBox.setItems(sortedUniversity);
        t2University2ChoiceBox.setItems(sortedUniversity);

        ObservableList<String> sortedCountry = QSList.country;
        ObservableList<String> sortedRegion = QSList.region;
        sortedCountry.sort(Comparator.naturalOrder());
        sortedRegion.sort(Comparator.naturalOrder());

        ObservableList<String> combinedList = FXCollections.observableArrayList();
        combinedList.addAll(sortedCountry);
        combinedList.addAll(sortedRegion);
        combinedList.add("All");

        t2CountryRegion1ChoiceBox.setItems(combinedList);
        t2CountryRegion2ChoiceBox.setItems(combinedList);

        //xAxis.setText ("Avg. Rank");
        FieldSelect.setItems(FXCollections.observableArrayList("Rank", "Score", "Faculty Count", "International Student", "Student Faculty Ratio"));
        FieldSelect2.setItems(FXCollections.observableArrayList("Rank", "Score", "Faculty Count", "International Student", "Student Faculty Ratio"));

        CategoryAxis xAxis = (CategoryAxis) t21LineChart.getXAxis();
        xAxis.setAutoRanging(true);
        xAxis.setCategories(yearList);

        CategoryAxis xAxis2 = (CategoryAxis) t22LineChart.getXAxis();
        xAxis2.setAutoRanging(true);
        xAxis2.setCategories(yearList);
        // T3

        ObservableList<String> sortedRegions = QSList.region;
        sortedRegions.sort(Comparator.naturalOrder());
        sortedRegions.add("All");
        t3RegionChoiceBox.setItems(sortedRegions);
        ObservableList<String> sortedTypes = FXCollections.observableArrayList();
        sortedTypes.add("All");
        sortedTypes.add("Private");
        sortedTypes.add("Public");
        t3TypeChoiceBox.setItems(sortedTypes);
        t3University.setCellValueFactory(new PropertyValueFactory<RecommendItem, String>("name"));
        t3BestYear.setCellValueFactory(new PropertyValueFactory<RecommendItem, String>("bestYear"));
        t3BestRank.setCellValueFactory(new PropertyValueFactory<RecommendItem, String>("bestRank"));
        t3RecentYear.setCellValueFactory(new PropertyValueFactory<RecommendItem, String>("recentYear"));
        t3RecentRank.setCellValueFactory(new PropertyValueFactory<RecommendItem, String>("recentRank"));
    }

    /**
     * Resets all the data such as the year and properties selected and clears any charts or tables displayed
     * @author Malav Daftary
     */
    @FXML
    public void T1_onClickClear() {

        t1DataTable.getItems().clear();
        t1YearChoiceBox.setValue("2017");
        t1BarChart.getData().clear();
        t1PieChartChoiceBox.setValue("country");
        t1BarChartChoiceBox.setValue("country");
        t1PieChart.getData().clear();
        t1BarChartLabel.setText(null);
        t1PieChartLabel.setText(null);
        t1BarChart.setTitle(null);
        t1PieChart.setTitle(null);


    }

    /**
     * Resets all data except any inputs by the user like the year or property selected
     * Used in T1_onClickSearch as an internal function to clear previous data
     * @author Malav Daftary
     */

    public void T1_internalupdate() {
        t1DataTable.getItems().clear();
        t1BarChart.getData().clear();
        t1PieChart.getData().clear();
        t1BarChartLabel.setText(null);
        t1PieChartLabel.setText(null);
        t1BarChart.setTitle(null);
        t1PieChart.setTitle(null);

    }

    /**
     * Fetches the selected year and properties by the user
     * Calls T1_internalupdate to clear existing table and chart data before adding new data
     * Creates an Analyzer which is used to obtain the table, pie chart and bar chart data
     * Sets up the formatting for the table, pie chart and bar chart
     * Displays table, pie chart and bar chart using obtained data
     * No error handling is required since any of the user input can never be null or invalid
     * @author Malav Daftary
     */

    @FXML
    public void T1_onClickSearch() {
        //Clearing previous data
        T1_internalupdate();
        //Fetching year from choice box
        String year = t1YearChoiceBox.getValue();

        //Making an analyser
        T1Analysis T1analyser = new T1Analysis(year);

        t1PieChart.layout();
        //Updating Table view
        t1University.setCellValueFactory(new PropertyValueFactory<>("name"));
        t1Country.setCellValueFactory(new PropertyValueFactory<>("country"));
        t1City.setCellValueFactory(new PropertyValueFactory<>("city"));
        t1Score.setCellValueFactory(new PropertyValueFactory<>("score"));
        t1Rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        t1Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        t1DataTable.setItems(T1analyser.getTableList());

        //Updating Pie Chart view
        t1PieChart.setData(T1analyser.getPieChartData(t1PieChartChoiceBox.getValue()));

        //Setting the title of the Pie chart
        t1PieChartLabel.setText(t1PieChartChoiceBox.getValue() + " & score");

        //setting the direction to arrange the data
        t1PieChart.setClockwise(true);

        //Setting the labels of the pie chart visible
        t1PieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        t1PieChart.setStartAngle(180);

        t1BarChart.layout();

        //Updating the bar chart view
        t1BarChart.getData().add(T1analyser.getBarChartData(t1BarChartChoiceBox.getValue()));

        //Setting the title of the Bar chart
        t1BarChartLabel.setText(t1BarChartChoiceBox.getValue() + " & score");

        //Setting font size of X axis labels
        t1BarChart.getXAxis().tickLabelFontProperty().set(Font.font("-fx-font-size: " + 10 + "px;"));


    }

    /**
     * Clears all the data, years selected, error messages, charts and combo box for the tab task2.1
     * @author Shriyan Shekhar
     */
    @FXML
    public void T21_onClickClear() {

        //clear Universities and Combo Box
        t2University1ChoiceBox.setValue(null);
        t2University2ChoiceBox.setValue(null);
        FieldSelect.setValue(null);

        CategoryAxis xAxis = (CategoryAxis) t21LineChart.getXAxis();
        xAxis.setAutoRanging(true);
        xAxis.setCategories(yearList);

        //Clear Years
        t22017CheckBox.setSelected(false);
        t22018CheckBox.setSelected(false);
        t22019CheckBox.setSelected(false);
        t22020CheckBox.setSelected(false);
        t22021CheckBox.setSelected(false);
        t22022CheckBox.setSelected(false);

        //Clear Error message
        error1.setText("");
        ChartTitle1.setText("");
        t21LineChart.setTitle("");


        //Clear Data
        if (barChartData != null){
            barChartData.getData().clear();
        }
        if (barChartData2 !=null){
            barChartData2.getData().clear();
        }
        if (barChartData3 !=null){
            barChartData3.getData().clear();
        }
        if (barChartData4 !=null){
            barChartData4.getData().clear();
        }
        if (barChartData5 !=null){
            barChartData5.getData().clear();
        }

        //Clear Charts
        t21OverallBarChart.getData().clear();
        t21OverallBarChart.getXAxis().setLabel ("");
        t21LineChart.getData().clear();
    }

    /**
     * Clears all the chart data in the line and chart tab for universities.
     * @author Shriyan Shekhar
     */
    public void clearTask21(){
        FieldSelect.setValue(null);

        CategoryAxis xAxis = (CategoryAxis) t21LineChart.getXAxis();
        xAxis.setAutoRanging(true);
        xAxis.setCategories(yearList);

        ChartTitle1.setText("");
        t21LineChart.setTitle("");


        //Clear Data
        if (barChartData != null){
            barChartData.getData().clear();
        }
        if (barChartData2 !=null){
            barChartData2.getData().clear();
        }
        if (barChartData3 !=null){
            barChartData3.getData().clear();
        }
        if (barChartData4 !=null){
            barChartData4.getData().clear();
        }
        if (barChartData5 !=null){
            barChartData5.getData().clear();
        }

        //Clear Charts
        t21OverallBarChart.getData().clear();
        t21OverallBarChart.getXAxis().setLabel ("");
        t21LineChart.getData().clear();

    }

    /**
     * Fetches university choices and years
     * Clears Existing data on charts first before displaying new data.
     * Creates an Analyzer.
     * Displays bar chart and line chart data for task2.1 if all conditions are met otherwise displays an error message.
     * Sets the X-Axis for the bar Chart Data.
     * @author Shriyan Shekhar
     */
    @FXML
    public void T21_onClickCompare() {
        //Set error text as empty
        error1.setText("");
        t21OverallBarChart.getXAxis().setLabel ("");

        String university1 = t2University1ChoiceBox.getValue();
        String university2 = t2University2ChoiceBox.getValue();

        boolean yearCondition = false;
        List <String> SelectedYears = new ArrayList<>();
        CheckBox[] checkboxes = {
                t22017CheckBox,
                t22018CheckBox,
                t22019CheckBox,
                t22020CheckBox,
                t22021CheckBox,
                t22022CheckBox
        };

        for (CheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                String year = checkbox.getText();
                SelectedYears.add(year);
                yearCondition = true;
            }
        }

        List <QSItem> universityList1 = QSList.list.stream().filter (qsItem -> qsItem.getName().equals(university1) && SelectedYears.contains(qsItem.year)).toList();
        List <QSItem> universityList2 = QSList.list.stream().filter (qsItem -> qsItem.getName().equals(university2) && SelectedYears.contains(qsItem.year)).toList();

        if (university1 == null) {
            clearTask21();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            if (university2 == null) {
                if (!yearCondition) {
                    alert.setContentText("Please Select University 1, University 2, and Year");
                    alert.showAndWait();
                    error1.setText("Please Select University 1, University 2, and Year");
                }
                else {
                    alert.setContentText("Please Select University 1 and University 2");
                    alert.showAndWait();
                    error1.setText("Please Select University 1 and University 2");
                }
            }
            else if (!yearCondition) {
                alert.setContentText("Please Select University 1 and Year");
                alert.showAndWait();
                error1.setText("Please Select University 1 and Year");
            }
            else {
                alert.setContentText("Please Select University 1");
                alert.showAndWait();
                error1.setText("Please Select University 1");
            }
        }
        else if (university2 == null) {
            clearTask21();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            if (!yearCondition) {
                alert.setContentText("Please Select University 2 and Year");
                alert.showAndWait();
                error1.setText("Please Select University 2 and Year");
            } else {
                alert.setContentText("Please Select University 2");
                alert.showAndWait();
                error1.setText("Please Select University 2");
            }
        }
        else if (!yearCondition) {
            clearTask21();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Please Select Year");
            alert.showAndWait();
            error1.setText("Please Select Year");
        }
        else if (universityList1.isEmpty() || universityList2.isEmpty()){
            clearTask21();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Please Select Another Year or University");
            alert.showAndWait();
            error1.setText("Please Select Another Year or University");
        }
        else{
            FieldSelect.setValue("Score"); //Default Display will be Score

            //Clear Charts
            t21OverallBarChart.getData().clear();
            t21LineChart.getData().clear();
            t21OverallBarChart.getXAxis().setLabel ("Avg. Score");
            //Create Analyzer
            T21Analysis analyzer = new T21Analysis (university1, university2, SelectedYears);
            barChartData = analyzer.getBarChartData("rank");
            barChartData2 = analyzer.getBarChartData("score");
            barChartData3 = analyzer.getBarChartData("studentFacultyRatio");
            barChartData4 = analyzer.getBarChartData("internationalStudents");
            barChartData5 = analyzer.getBarChartData("facultyCount");

            //Set Default overall to Score Graph
            t21OverallBarChart.getData().addAll(barChartData2);
            ChartTitle1.setText("Comparing Avg. Score");

            //Set Line Chart
            List<XYChart.Series<String, Double>> lineChartData = analyzer.getLineChartData("score");
            t21LineChart.getData().addAll(lineChartData);
            CategoryAxis xAxis = (CategoryAxis) t21LineChart.getXAxis();
            xAxis.setAutoRanging(true);
            ObservableList<String> categories = FXCollections.observableArrayList(SelectedYears);
            xAxis.setCategories(categories);
            t21LineChart.setTitle("Average Score over Time");

        }
    }


    /**
     * Handles the combo box selection and updates the chart data accordingly for university
     * @author Shriyan Shekhar
     */
    @FXML
    public void HandleCombo (){
        String choice = FieldSelect.getValue();
        if (choice != null){
            t21OverallBarChart.getData().clear();
            ChartTitle1.setText("Comparing Avg. "  + choice);
            if (choice.equals("Score")){
                if (barChartData2 !=null){
                    t21OverallBarChart.getData().add(barChartData2);
                }
                t21OverallBarChart.getXAxis().setLabel ("Avg. Score");
            }
            else if (choice.equals("Faculty Count")){
                if (barChartData5!=null){
                    t21OverallBarChart.getData().add(barChartData5);
                }
                t21OverallBarChart.getXAxis().setLabel ("Avg. Faculty Count");
            }
            else if (choice.equals("Rank")){
                if (barChartData!=null){
                    t21OverallBarChart.getData().add(barChartData);
                }
                t21OverallBarChart.getXAxis().setLabel ("Avg. Rank");
            }
            else if (choice.equals("International Student")){
                if (barChartData4!=null){
                    t21OverallBarChart.getData().add(barChartData4);
                }
                t21OverallBarChart.getXAxis().setLabel ("Avg. International Students");
            }
            else if (choice.equals("Student Faculty Ratio")){
                if (barChartData3!=null){
                    t21OverallBarChart.getData().add(barChartData3);
                }
                t21OverallBarChart.getXAxis().setLabel ("Avg. Student Faculty Ratio");
            }
        }
    }


    /**
     *  Clears all the data, years selected, error messages, charts and combo box for the tab task2.2
     *  @author Shriyan Shekhar
     */
    @FXML
    public void T22_onClickClear() {
        //Clear Countries/regions
        t2CountryRegion1ChoiceBox.setValue(null);
        t2CountryRegion2ChoiceBox.setValue(null);
        FieldSelect2.setValue(null);

        CategoryAxis xAxis2 = (CategoryAxis) t22LineChart.getXAxis();
        xAxis2.setAutoRanging(true);
        xAxis2.setCategories(yearList);

        //Clear Years
        t22017CheckBox2.setSelected(false);
        t22018CheckBox2.setSelected(false);
        t22019CheckBox2.setSelected(false);
        t22020CheckBox2.setSelected(false);
        t22021CheckBox2.setSelected(false);
        t22022CheckBox2.setSelected(false);

        //Clear Error Message
        error2.setText("");
        t22LineChart.setTitle("");
        ChartTitle2.setText("");

        //Clear Data
        if (barChartData21 != null){
            barChartData21.getData().clear();
        }
        if (barChartData22 !=null){
            barChartData22.getData().clear();
        }
        if (barChartData23 !=null){
            barChartData23.getData().clear();
        }
        if (barChartData24 !=null){
            barChartData24.getData().clear();
        }
        if (barChartData25 !=null){
            barChartData25.getData().clear();
        }

        //Clear Charts
        t22OverallBarChart.getData().clear();
        t22OverallBarChart.getXAxis().setLabel ("");
        t22LineChart.getData().clear();

    }

    /**
     * Clears all the chart data in the line and chart tab for countries.
     * @author Shriyan Shekhar
     */
    public void ClearTask22(){
        FieldSelect2.setValue(null);

        CategoryAxis xAxis2 = (CategoryAxis) t22LineChart.getXAxis();
        xAxis2.setAutoRanging(true);
        xAxis2.setCategories(yearList);

        t22LineChart.setTitle("");
        ChartTitle2.setText("");

        //Clear Data
        if (barChartData21 != null){
            barChartData21.getData().clear();
        }
        if (barChartData22 !=null){
            barChartData22.getData().clear();
        }
        if (barChartData23 !=null){
            barChartData23.getData().clear();
        }
        if (barChartData24 !=null){
            barChartData24.getData().clear();
        }
        if (barChartData25 !=null){
            barChartData25.getData().clear();
        }

        //Clear Charts
        t22OverallBarChart.getData().clear();
        t22OverallBarChart.getXAxis().setLabel ("");
        t22LineChart.getData().clear();
    }


    /**
     *  Fetches country/region choices and years
     *  Clears Existing data on charts first before displaying new data.
     *  Creates an Analyzer.
     *  Displays bar chart and line chart data for task2.2 if all conditions are met otherwise displays an error message.
     *  Sets the X-Axis for the bar Chart Data.
     *  @author Shriyan Shekhar
     */
    @FXML
    public void T22_onClickCompare() {
        error2.setText("");
        t22OverallBarChart.getXAxis().setLabel ("");

        String countryregion1 = t2CountryRegion1ChoiceBox.getValue();
        String countryregion2 = t2CountryRegion2ChoiceBox.getValue();

        boolean yearCondition_22 = false;
        List <String> SelectedYears22 = new ArrayList<>();

        CheckBox[] checkboxes = {
                t22017CheckBox2,
                t22018CheckBox2,
                t22019CheckBox2,
                t22020CheckBox2,
                t22021CheckBox2,
                t22022CheckBox2
        };

        for (CheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                String year = checkbox.getText();
                SelectedYears22.add(year);
                yearCondition_22 = true;
            }
        }

        List<QSItem> countryList1 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(countryregion1) || qsItem.getRegion().equals(countryregion1)) && SelectedYears22.contains(qsItem.getYear()))
                .collect(Collectors.toList());
        List<QSItem> countryList2 = QSList.list.stream()
                .filter(qsItem -> (qsItem.getCountry().equals(countryregion2) || qsItem.getRegion().equals(countryregion2)) && SelectedYears22.contains(qsItem.getYear()))
                .collect(Collectors.toList());

        if (countryregion1!=null) {
            if (countryregion1.equals("All")){
                countryList1 = QSList.list.stream().filter(qsItem -> (SelectedYears22.contains(qsItem.getYear()))).collect(Collectors.toList());
            }
        }

        if (countryregion2!=null) {
            if (countryregion2.equals("All")) {
                countryList2 = QSList.list.stream().filter(qsItem -> (SelectedYears22.contains(qsItem.getYear()))).collect(Collectors.toList());
            }
        }

        if (countryregion1 == null) {
            ClearTask22();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            if (countryregion2 == null) {
                if (yearCondition_22 == false) {
                    alert.setContentText("Please Select Country/Region 1, Country/Region 2, and Year");
                    Optional<ButtonType> result = alert.showAndWait();
                    error2.setText("Please Select Country/Region 1, Country/Region 2, and Year");
                }
                else {
                    alert.setContentText("Please Select Country/Region 1 and Country/Region 2");
                    Optional<ButtonType> result = alert.showAndWait();
                    error2.setText("Please Select Country/Region 1 and Country/Region 2");
                }
            }
            else {
                if (yearCondition_22 == false) {
                    alert.setContentText("Please Select Country/Region 1 and Year");
                    Optional<ButtonType> result = alert.showAndWait();
                    error2.setText("Please Select Country/Region 1 and Year");
                }
                else {
                    alert.setContentText("Please Select Country/Region 1");
                    Optional<ButtonType> result = alert.showAndWait();
                    error2.setText("Please Select Country/Region 1");
                }
            }
        }
        else if (countryregion2 == null) {
            ClearTask22();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            if (yearCondition_22 == false) {
                alert.setContentText("Please Select Country/Region 2 and Year");
                Optional<ButtonType> result = alert.showAndWait();
                error2.setText("Please Select Country/Region 2 and Year");
            }
            else {
                alert.setContentText("Please Select Country/Region 2");
                Optional<ButtonType> result = alert.showAndWait();
                error2.setText("Please Select Country/Region 2");
            }
        }
        else if (yearCondition_22 == false) {
            ClearTask22();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Please Select Year");
            Optional<ButtonType> result = alert.showAndWait();
            error2.setText("Please Select Year");
        }
        else if (countryList1.isEmpty() || countryList2.isEmpty()){
            ClearTask22();
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            alert.setContentText("Please Select Another Year or Country/Region");
            Optional<ButtonType> result = alert.showAndWait();
            error2.setText("Please Select Another Year or Country/Region");
        }
        else{
            FieldSelect2.setValue("Score"); //Default Display will be Score

            //Clear Charts
            t22OverallBarChart.getData().clear();
            t22LineChart.getData().clear();
            t22OverallBarChart.getXAxis().setLabel ("Avg. Score");
            //Create Analyzer
            T22Analysis analyzer2 = new T22Analysis (countryregion1, countryregion2, SelectedYears22);
            barChartData21 = analyzer2.getBarChartData("rank");
            barChartData22 = analyzer2.getBarChartData("score");
            barChartData23 = analyzer2.getBarChartData("studentFacultyRatio");
            barChartData24 = analyzer2.getBarChartData("internationalStudents");
            barChartData25 = analyzer2.getBarChartData("facultyCount");

            //Set Default overall to Score Graph
            t22OverallBarChart.getData().addAll(barChartData22);
            ChartTitle2.setText("Comparing Avg. Score");

            //Set Line Chart
            List<XYChart.Series<String, Double>> lineChartData = analyzer2.getLineChartData("score");
            t22LineChart.getData().addAll(lineChartData);
            CategoryAxis xAxis2 = (CategoryAxis) t22LineChart.getXAxis();
            xAxis2.setAutoRanging(true);
            ObservableList<String> categories = FXCollections.observableArrayList(SelectedYears22);
            xAxis2.setCategories(categories);
            t22LineChart.setTitle("Average Score over Time");
        }
    }

    /**
     * Handles the combo box selection and updates the chart data accordingly for country/region
     * @author Shriyan Shekhar
     */
    @FXML
    public void HandleCombo2 (){
        String choice = FieldSelect2.getValue();
        if (choice != null){
            t22OverallBarChart.getData().clear();
            ChartTitle2.setText("Comparing Avg. " + choice);
            if (choice.equals("Score")){
                if (barChartData22 !=null){
                    t22OverallBarChart.getData().add(barChartData22);
                }
                t22OverallBarChart.getXAxis().setLabel ("Avg. Score");
            }
            else if (choice.equals("Faculty Count")){
                if (barChartData25!=null){
                    t22OverallBarChart.getData().add(barChartData25);
                }
                t22OverallBarChart.getXAxis().setLabel ("Avg. Faculty Count");
            }
            else if (choice.equals("Rank")){
                if (barChartData21!=null){
                    t22OverallBarChart.getData().add(barChartData21);
                }
                t22OverallBarChart.getXAxis().setLabel ("Avg. Rank");
            }
            else if (choice.equals("International Student")){
                if (barChartData24!=null){
                    t22OverallBarChart.getData().add(barChartData24);
                }
                t22OverallBarChart.getXAxis().setLabel ("Avg. International Students");
            }
            else if (choice.equals("Student Faculty Ratio")){
                if (barChartData23!=null){
                    t22OverallBarChart.getData().add(barChartData23);
                }
                t22OverallBarChart.getXAxis().setLabel ("Avg. Student Faculty Ratio");
            }
        }
    }

    /**
     * Clears all the text fields, choice boxes, error messages and table data for the tab task3
     * @author Ipsita Sanjay SINGH
     */
        @FXML
    public void T3_onClickClear() {
            //set error label to be empty
            errorT3.setText("");

            //clear all the fields and choice boxes
            t3TopRankTextField.clear();
            t3BottomRankTextField.clear();
            t3TypeChoiceBox.setValue(null);
            t3RegionChoiceBox.setValue(null);

            //clear data from table
            if (t3TableView != null){
                t3TableView.getItems().clear();
            }
    }

    @FXML
    public void clearTask3() {
        //clear data from table
        if (t3TableView != null){
            t3TableView.getItems().clear();
        }
    }
    /**
     * Retrieves all the user inputs and checks whether all the inputs or not. If not, relevant errors are raised.
     * If all the conditions are met then an analyser is created.
     * If the recommendation list is not empty, then the table is updated with the recommendation list.
     * If it's empty, an error message is raised.
     * @author Ipsita Sanjay SINGH
     */
    @FXML
    public void T3_onClickRecommend() {
        // 1. Fetch the top and bottom boundary requirement of score.
        String top_input = t3TopRankTextField.getText();
        String bottom_input = t3BottomRankTextField.getText();

        // 2. Fetch the type and region requirements.
        String type = t3TypeChoiceBox.getValue();
        String region = t3RegionChoiceBox.getValue();

        // 3a. error handling for when one or both of the rank inputs are not integer
        boolean isInput1Int = true;
        boolean isInput2Int = true;
        boolean error = false;
        try{
            int Top_input = Integer.parseInt(top_input);
        }
        catch(NumberFormatException e){
            isInput1Int = false;
        }
        try{
            int Bottom_input = Integer.parseInt(bottom_input);
        }
        catch(NumberFormatException exc){
            isInput2Int = false;
        }

        if (isInput1Int){
            if (!isInput2Int) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                alert.setContentText("Please enter a positive integer for Bottom Rank");
                alert.showAndWait();
                errorT3.setText("Please enter a positive integer for Bottom Rank");
                error = true;
                clearTask3();
            }
        }
        else{
            if (!isInput2Int){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                alert.setContentText("Please enter a positive integer for Bottom Rank and Top Rank");
                alert.showAndWait();
                errorT3.setText("Please enter a positive integer for Bottom Rank and Top Rank");
                error = true;
                clearTask3();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                alert.setContentText("Please enter a positive integer for Top Rank");
                alert.showAndWait();
                errorT3.setText("Please enter a positive integer for Top Rank");
                error = true;
                clearTask3();
            }
        }

        // 3b. error handling for when the ranks are non-positive
        if (!error) {
            if (Integer.parseInt(top_input) <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                if (Integer.parseInt(bottom_input) <= 0) {
                    alert.setContentText("Please enter positive integer values for Bottom Rank and Top Rank");
                    alert.showAndWait();
                    errorT3.setText("Please enter positive integer values for Bottom Rank and Top Rank");
                    error = true;
                    clearTask3();
                } else {
                    alert.setContentText("Please enter positive integer values for Top Rank");
                    alert.showAndWait();
                    errorT3.setText("Please enter positive integer values for Top Rank");
                    error = true;
                    clearTask3();
                }
            } else {
                if (Integer.parseInt(bottom_input) <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert!");
                    alert.setContentText("Please enter positive integer values for Bottom Rank");
                    alert.showAndWait();
                    errorT3.setText("Please enter positive integer values for Bottom Rank");
                    error = true;
                    clearTask3();
                }
            }
        }

        //3c. error handling for when the text fields or the choice boxes are empty

        if (top_input == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert!");
            if (bottom_input == null) {
                if (type == null) {
                    if (region == null) {
                        alert.setContentText("Please Select Top Rank, Bottom Rank, Type and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank, Bottom Rank, Type and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Top Rank, Bottom Rank and Type");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank, Bottom Rank and Type");
                        error = true;
                        clearTask3();
                    }
                } else {
                    if (region == null) {
                        alert.setContentText("Please Select Top Rank, Bottom Rank and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank, Bottom Rank and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Top Rank and Bottom Rank");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank and Bottom Rank");
                        error = true;
                        clearTask3();
                    }

                }
            } else {
                if (type == null) {
                    if (region.equals("")) {
                        alert.setContentText("Please Select Top Rank, Type and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank, Type and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Top Rank and Type");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank and Type");
                        error = true;
                        clearTask3();
                    }
                } else {
                    if (region == null) {
                        alert.setContentText("Please Select Top Rank and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Top Rank");
                        alert.showAndWait();
                        errorT3.setText("Please Select Top Rank");
                        error = true;
                        clearTask3();
                    }

                }
            }
        } else {
            if (bottom_input == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                if (type == null) {
                    if (region.equals("")) {
                        alert.setContentText("Please Select Bottom Rank, Type and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Bottom Rank, Type and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Bottom Rank and Type");
                        alert.showAndWait();
                        errorT3.setText("Please Select Bottom Rank and Type");
                        error = true;
                        clearTask3();
                    }
                } else {
                    if (region == null) {
                        alert.setContentText("Please Select Bottom Rank and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Bottom Rank and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Bottom Rank");
                        alert.showAndWait();
                        errorT3.setText("Please Select Bottom Rank");
                        error = true;
                        clearTask3();
                    }

                }
            } else {
                if (type == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert!");
                    if (region == null) {
                        alert.setContentText("Please Select Type and Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Type and Region");
                        error = true;
                        clearTask3();
                    } else {
                        alert.setContentText("Please Select Type");
                        alert.showAndWait();
                        errorT3.setText("Please Select Type");
                        error = true;
                        clearTask3();
                    }
                } else {
                    if (region == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Alert!");
                        alert.setContentText("Please Select Region");
                        alert.showAndWait();
                        errorT3.setText("Please Select Region");
                        error = true;
                        clearTask3();
                    }
                }
            }
        }



        //3d. error handling for the case when top rank's value is higher thank bottom rank's value
        if (!error) {
            if (Integer.parseInt(top_input) > Integer.parseInt(bottom_input)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                alert.setContentText("Please set Top rank's value to be lower than bottom rank's value");
                alert.showAndWait();
                errorT3.setText("Please set Top rank's value to be lower than bottom rank's value");
                error = true;
                clearTask3();
            }
        }

        if (!error) {
            // 3. Clear previous data.
            t3TableView.getItems().clear();
            errorT3.setText("");
            // 4. Make an Analyser.
            T3Analysis analyser = new T3Analysis(top_input, bottom_input, type, region);

            // 5. Update the Table View.

            ObservableList<RecommendItem> recommendData = analyser.getRecommendData();
            if (recommendData.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert!");
                alert.setContentText("No recommendation exists for the given inputs. Please input different criterion.");
                alert.showAndWait();
                errorT3.setText("No recommendation exists for the given inputs, Please input different criterion.");
            }
            t3TableView.setItems(recommendData);
        }
    }

}