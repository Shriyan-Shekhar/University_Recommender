package comp3111.qsproject;

import javafx.application.HostServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class RecommendItem {
    public SimpleStringProperty name;

    public SimpleStringProperty bestYear;

    public SimpleStringProperty bestRank;

    public SimpleStringProperty recentYear;

    public SimpleStringProperty recentRank;


    /**
     * Constructor of RecommendItem class
     * Initialises the name, year of best rank (bestYear), best rank, recent rank and year of recent rank
     * (recentYear) using the input QSItem object.
     * @param item This is the university in QSItem
     * @author Ipsita Sanjay SINGH
     */
    RecommendItem(QSItem item) {
        this.name = new SimpleStringProperty(item.name);
        this.bestYear = new SimpleStringProperty(item.year);
        this.bestRank = new SimpleStringProperty(item.rank);
        this.recentYear = new SimpleStringProperty(item.year);
        this.recentRank = new SimpleStringProperty(item.rank);
    }

    /**
     * Checks if the name of the input university is the same as the one calling the update function.
     * If it is, then if the rank of the input university is higher than the best rank, best rank and corresponding year
     * are updated.
     * If the year of ranking of input university is more recent, then recent rank and corresponding year are updated.
     * @param item
     * @author Ipsita Sanjay SINGH
     */
    void update(QSItem item) {
        if (item.name.equals(this.getName())) {
        /*
            Your Code Here.
            This function update the information from other QSItem.
            1. Update the best rank and the corresponding year.
            2. Update the most recent year and the corresponding rank.
         */
            // Update the best rank and the corresponding year.
            if (Integer.parseInt(item.rank) < Integer.parseInt(this.getBestRank())) {
                this.setBestRank(item.rank);
                this.setBestYear(item.year);
            }
            // Update the most recent year and the corresponding rank.
            if (Integer.parseInt(item.year) > Integer.parseInt(this.getRecentYear())) {
                this.setRecentYear(item.year);
                this.setRecentRank(item.rank);
            }
        }
    }

    public String getName() { return name.get(); }

    public String getBestYear() { return bestYear.get(); }

    public String getBestRank() { return bestRank.get(); }

    public String getRecentYear() { return recentYear.get(); }

    public String getRecentRank() { return recentRank.get(); }

    public void setBestYear(String BestYear) {this.bestYear.set(BestYear);}

    public void setBestRank(String BestRank) {this.bestRank.set(BestRank);}

    public void setRecentYear(String RecentYear) {this.recentYear.set(RecentYear);}

    public void setRecentRank(String RecentRank) {this.recentRank.set(RecentRank);}

}
