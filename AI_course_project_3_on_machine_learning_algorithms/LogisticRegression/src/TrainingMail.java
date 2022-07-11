import java.util.*;

public class TrainingMail {
	
	private int category; //SPAM=1 , HAM=0
	private ArrayList<Feature> feature_vector;
	
	public TrainingMail(){
		feature_vector = new ArrayList<Feature>();
	}
	public void setCategory(int c){
		category = c;
	}
	public int getCategory(){
		return category;
	}
	public ArrayList<Feature> getFeatureVector(){
		return feature_vector;
	}
    public TrainingMail getTrainingMail(){
    	return this;
    }
}
