package comp3111.qsproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.fxml.FXML;


class RecommendItemTest {

    @Test
    void updatecaseRecentRank() {
    QSList.initialize();
    QSItem item1 = QSList.list.get(0);
    QSItem item2 = QSList.list.get(400);
    RecommendItem recItem = new RecommendItem(item1);
    recItem.update(item2);
    Assertions.assertEquals(recItem.getRecentYear(), item2.getYear());
    Assertions.assertEquals(recItem.getRecentRank(), item2.getRank());
    Assertions.assertNotEquals(recItem.getBestYear(), item2.getYear());
    Assertions.assertEquals(recItem.getBestRank(), item2.getRank());
    }

    @Test
    void updatecaseBestRank(){
        QSList.initialize();
        QSItem item1 = QSList.list.get(1);
        QSItem item2 = QSList.list.get(2002);
        RecommendItem recItem = new RecommendItem(item2);
        recItem.update(item1);
        Assertions.assertEquals(recItem.getBestYear(), item1.getYear());
        Assertions.assertEquals(recItem.getBestRank(), item1.getRank());
    }

    @Test
    void updatecaseRecentRankandBestRank(){
        QSList.initialize();
        QSItem item1 = QSList.list.get(1205);
        QSItem item2 = QSList.list.get(1603);
        RecommendItem recItem = new RecommendItem(item1);
        recItem.update(item2);
        Assertions.assertEquals(recItem.getBestYear(), item2.getYear());
        Assertions.assertEquals(recItem.getBestRank(), item2.getRank());
        Assertions.assertEquals(recItem.getRecentYear(), item2.getYear());
        Assertions.assertEquals(recItem.getRecentRank(), item2.getRank());
    }

    @Test
    void updatecasenull(){
        QSList.initialize();
        QSItem item1 = QSList.list.get(1);
        QSItem item2 = QSList.list.get(1);
        RecommendItem recItem = new RecommendItem(item2);
        recItem.update(item1);
        Assertions.assertEquals(recItem.getBestYear(), item1.getYear());
        Assertions.assertEquals(recItem.getBestRank(), item1.getRank());
        Assertions.assertEquals(recItem.getRecentYear(), item2.getYear());
        Assertions.assertEquals(recItem.getRecentRank(), item2.getRank());
    }


    @Test
    void updatecaseinvalid(){
        QSList.initialize();
        QSItem item1 = QSList.list.get(1205);
        QSItem item2 = QSList.list.get(1);
        RecommendItem recItem = new RecommendItem(item1);
        recItem.update(item2);
        //Assertions.assertEquals(recItem.getBestYear(), item2.getYear());
        //Assertions.assertEquals(recItem.getBestRank(), item2.getRank());
        //Assertions.assertEquals(recItem.getRecentYear(), item2.getYear());
        //Assertions.assertEquals(recItem.getRecentRank(), item2.getRank());
    }

}