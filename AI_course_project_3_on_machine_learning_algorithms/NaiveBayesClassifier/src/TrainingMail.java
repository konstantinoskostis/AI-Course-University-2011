import java.util.*;

public class TrainingMail {
	
	private int category; //SPAM=1 , HAM=0
	private ArrayList<Feature> features_vector;
	
	public TrainingMail(){
		features_vector = new ArrayList<Feature>();
	}
	public void setCategory(int c){
		category = c;
	}
	public int getCategory(){
		return category;
	}
	public ArrayList<Feature> getFeatuesVector(){
		return features_vector;
	}
    public TrainingMail getTrainingMail(){
    	return this;
    }
}
