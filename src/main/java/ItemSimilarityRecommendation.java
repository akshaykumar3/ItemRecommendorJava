import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by akshaykumar on 5/22/16.
 */
public class ItemSimilarityRecommendation {

    private static final int NUMBER_OF_RECOMMENDATIONS = 20;

    public static void main(String[] args) {

        try {
//            DataModel dm = new FileDataModel(new File("ItemRecommendorJava/data/movies.csv"));
            DataModel dm = new FileDataModel(new File("ItemRecommendorJava/data/ratings.csv"));

//            ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
            ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);

            GenericItemBasedRecommender recommendor = new GenericItemBasedRecommender(dm, sim);

            int i = 1;
            for(LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
                Long itemId = items.nextLong();
                List<RecommendedItem> recommendations = recommendor.mostSimilarItems(itemId, NUMBER_OF_RECOMMENDATIONS);

                for(RecommendedItem recommendation : recommendations) {
                    System.out.println(itemId + ", "+ recommendation.getItemID() + ", "+ recommendation.getValue());
//                    System.out.println("Recommendations : "+recommendation);
                }
                i++;
                if(10 < i) System.exit(1);
            }

        } catch (IOException e) {
            System.out.println("IOException = " + e.getLocalizedMessage());
        } catch (TasteException e1) {
            System.out.println("TasteException = " + e1.getLocalizedMessage());
        }

    }
}
