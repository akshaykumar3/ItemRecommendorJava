import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by akshay.kumar1 on 16/06/16.
 */
public class UserBasedRecommendation {

    public static void main(String[] args) {
        try {
            DataModel dataModel = new FileDataModel(new File("data/movies.csv"));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
            UserBasedRecommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            List<RecommendedItem> recommendations = recommender.recommend(2 /* User Id */, 3 /* Number of recommendations */);

            for(RecommendedItem recommendation : recommendations) {
                System.out.println("Recommendations : "+recommendation);
            }
        } catch (IOException io){
            System.out.println("IOException = " + io.getLocalizedMessage());
        } catch (TasteException te) {
            System.out.println("TasteException = " + te.getLocalizedMessage());
        }
    }

}
