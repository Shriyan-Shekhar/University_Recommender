package comp3111.qsproject;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class T3Analysis {
    public ObservableList<RecommendItem> RecommendList = FXCollections.observableArrayList();


    /**
     * Constructor for T3Analysis class
     * Traverses through every item in the QSList.list and if the attribute values of the item satisfy the
     * criterion set by the user (i.e. the rank of uni in the list is not higher than top_input and not lower than
     * low_input and matches the type and region inputted by the user), then the recommendList is traversed to
     * check whether the university already exists in the recommendList. If it does, then the item in the
     * recommendList is updated by calling update(). Otherwise, the university is added to the recommendList
     * so it can be updated later on.Finally, the RecommendList is sorted by best rank.
     * @author Ipsita Sanjay SINGH
     * @param top_input The top rank
     * @param bottom_input The bottom rank
     * @param type The type of the university (Private or Public)
     * @param region The region of university
     */
    T3Analysis (String top_input, String bottom_input, String type, String region) {
        /*
            Your Code Here.
            Collect the QSItem which fit the score range, type and region into the RecommendItem.
            Sort the RecommendList by bestRank.
            Hint: QSList.list is a static property and you can use "update" function in RecommendItem.
         */
        // Iterate over the QSList
        int bottom;
        int top;
        boolean err = false;
        try{
            bottom = Integer.parseInt(bottom_input);
            top = Integer.parseInt(top_input);
        }
        catch(NumberFormatException exc){
            bottom = 0;
            top = 0;
            err = true;
        }

        if (!err) {
            for (QSItem item : QSList.list) {
                // Convert the item's score to an integer
                int itemScore = Integer.parseInt(item.getRank());
                if(!type.equals(("All"))) {
                    if (!region.equals("All")) {
                        // Check if the item fits the score range, type, and region
                        if (itemScore <= bottom && itemScore >= top && (item.getType() == null || item.getType().equals(type)) && item.getRegion().equals(region)) {
                            // If a RecommendItem for this QSItem already exists in the RecommendList, update it
                            boolean found = false;
                            for (RecommendItem recommendItem : RecommendList) {
                                if (recommendItem.getName().equals(item.name)) {
                                    recommendItem.update(item);
                                    found = true;
                                    break;
                                }
                            }

                            // If a RecommendItem for this QSItem does not exist in the RecommendList, create a new one
                            if (!found) {
                                RecommendList.add(new RecommendItem(item));
                            }
                        }
                    } else {
                        if (itemScore <= bottom && itemScore >= top && (item.getType() == null || item.getType().equals(type))) {
                            // If a RecommendItem for this QSItem already exists in the RecommendList, update it
                            boolean found = false;
                            for (RecommendItem recommendItem : RecommendList) {
                                if (recommendItem.getName().equals(item.name)) {
                                    recommendItem.update(item);
                                    found = true;
                                    break;
                                }
                            }

                            // If a RecommendItem for this QSItem does not exist in the RecommendList, create a new one
                            if (!found) {
                                RecommendList.add(new RecommendItem(item));
                            }
                        }
                    }
                }
                else{
                    if (!region.equals("All")) {
                        // Check if the item fits the score range, type, and region
                        if (itemScore <= bottom && itemScore >= top && item.getRegion().equals(region)) {
                            // If a RecommendItem for this QSItem already exists in the RecommendList, update it
                            boolean found = false;
                            for (RecommendItem recommendItem : RecommendList) {
                                if (recommendItem.getName().equals(item.name)) {
                                    recommendItem.update(item);
                                    found = true;
                                    break;
                                }
                            }

                            // If a RecommendItem for this QSItem does not exist in the RecommendList, create a new one
                            if (!found) {
                                RecommendList.add(new RecommendItem(item));
                            }
                        }
                    } else {
                        if (itemScore <= bottom && itemScore >= top) {
                            // If a RecommendItem for this QSItem already exists in the RecommendList, update it
                            boolean found = false;
                            for (RecommendItem recommendItem : RecommendList) {
                                if (recommendItem.getName().equals(item.name)) {
                                    recommendItem.update(item);
                                    found = true;
                                    break;
                                }
                            }

                            // If a RecommendItem for this QSItem does not exist in the RecommendList, create a new one
                            if (!found) {
                                RecommendList.add(new RecommendItem(item));
                            }
                        }
                    }
                }
            }

            // Sort the RecommendList by bestRank
            RecommendList.sort((item1, item2) -> Integer.compare(Integer.parseInt(item1.getBestRank()), Integer.parseInt(item2.getBestRank())));
        }
    }

    /** Retrieves the Recommendation List, which contains all the universities from QSList.list which
     * satisfy the user inputted criterion.
     * @author Ipsita Sanjay SINGH
     * @return RecommendList, which is the list of universities, their best rank, year of best rank,
     * most recent rank, and the year of the most recent rank satisfying the criterion inputted by the user.
     */
    ObservableList<RecommendItem> getRecommendData() {
        // Show the most valuable university
        return RecommendList;
    }
}
