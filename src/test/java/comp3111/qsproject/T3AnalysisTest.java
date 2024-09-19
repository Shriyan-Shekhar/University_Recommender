package comp3111.qsproject;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXML;

class T3AnalysisTest {

    @Test
    void getRecommendDataNormalCase1() {
        QSList.initialize();
        String rank_top = "1";
        String rank_bottom = "2";
        String region = "North America";
        String type = "Private";

        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
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
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());

        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected.getBestRank(), item_actual.getBestRank());
            Assertions.assertEquals(item_expected.getBestYear(), item_actual.getBestYear());
            Assertions.assertEquals(item_expected.getRecentRank(), item_actual.getRecentRank());
            Assertions.assertEquals(item_expected.getRecentYear(), item_actual.getRecentYear());
        }
    }

    @Test
    void getRecommendDataNormalCase2() {
        QSList.initialize();
        String rank_top = "1";
        String rank_bottom = "2";
        String region = "All";
        String type = "Private";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
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
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());

        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected.getBestRank(), item_actual.getBestRank());
            Assertions.assertEquals(item_expected.getBestYear(), item_actual.getBestYear());
            Assertions.assertEquals(item_expected.getRecentRank(), item_actual.getRecentRank());
            Assertions.assertEquals(item_expected.getRecentYear(), item_actual.getRecentYear());
        }
    }

    @Test
    void getRecommendDataNormalCase3() {
        QSList.initialize();
        String rank_top = "1";
        String rank_bottom = "2";
        String region = "All";
        String type = "All";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        RecommendItem actualItem1 = new RecommendItem(QSList.list.get(0));
        RecommendItem actualItem2 = new RecommendItem(QSList.list.get(1));
        RecommendItem actualItem3 = new RecommendItem((QSList.list.get(2)));
        actualItem1.setRecentRank("1");
        actualItem1.setRecentYear("2022");
        actualItem1.setBestYear("2017");
        actualItem1.setBestRank("1");
        actualItem2.setRecentRank("2");
        actualItem2.setRecentYear("2021");
        actualItem2.setBestYear("2017");
        actualItem2.setBestRank("2");
        actualItem3.setRecentRank("2");
        actualItem3.setRecentYear("2022");
        actualItem3.setBestRank("2");
        actualItem3.setBestYear("2022");
        recommendListExpected.add(actualItem1);
        recommendListExpected.add(actualItem2);
        recommendListExpected.add(actualItem3);
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());

        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected.getBestRank(), item_actual.getBestRank());
            Assertions.assertEquals(item_expected.getBestYear(), item_actual.getBestYear());
            Assertions.assertEquals(item_expected.getRecentRank(), item_actual.getRecentRank());
            Assertions.assertEquals(item_expected.getRecentYear(), item_actual.getRecentYear());
        }
    }

    @Test
    void getRecommendDataNormalCase4() {
        QSList.initialize();
        String rank_top = "1";
        String rank_bottom = "2";
        String region = "North America";
        String type = "All";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
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
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());

        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected.getBestRank(), item_actual.getBestRank());
            Assertions.assertEquals(item_expected.getBestYear(), item_actual.getBestYear());
            Assertions.assertEquals(item_expected.getRecentRank(), item_actual.getRecentRank());
            Assertions.assertEquals(item_expected.getRecentYear(), item_actual.getRecentYear());
        }
    }

    @Test
    void getRecommendDataInvalidRanks1() {
        QSList.initialize();
        String rank_top = "60";
        String rank_bottom = "40";
        String region = "North America";
        String type = "Public";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
}

    @Test
    void getRecommendDataInvalidRanks2(){
        QSList.initialize();
        String rank_top = "60.5";
        String rank_bottom = "40.4";
        String region = "North America";
        String type = "Public";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
    }

    @Test
    void getRecommendDataInvalidRanks3(){
        QSList.initialize();
        String rank_top = "abc";
        String rank_bottom = "def";
        String region = "North America";
        String type = "Public";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
    }

    @Test
    void getRecommendDataInvalidRanks4(){
        QSList.initialize();
        String rank_top = "";
        String rank_bottom = "";
        String region = "North America";
        String type = "Public";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
    }

    @Test
    void getRecommendDataInvalidType(){
        QSList.initialize();
        String rank_top = "40";
        String rank_bottom = "100";
        String region = "North America";
        String type = "";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
    }

    @Test
    void getRecommendDataInvalidRegion(){
        QSList.initialize();
        String rank_top = "40";
        String rank_bottom = "100";
        String region = "";
        String type = "Public";
        T3Analysis t3analysis = new T3Analysis(rank_top, rank_bottom, type, region);
        ObservableList<RecommendItem> recommendListExpected = FXCollections.observableArrayList();
        ObservableList<RecommendItem> recommendListActual = t3analysis.getRecommendData();
        Assertions.assertEquals(recommendListActual.size(), recommendListExpected.size());
        for (int i = 0; i < recommendListActual.size(); i++){
            RecommendItem item_actual = recommendListActual.get(i);
            RecommendItem item_expected = recommendListExpected.get(i);
            Assertions.assertEquals(item_expected, item_actual);
        }
    }

}