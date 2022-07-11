import java.util.ArrayList;


public class TestingMail {
	
	private int category; //SPAM=1 , HAM=0
	private ArrayList<Feature> feature_vector;
	
	public TestingMail(){
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
}


	


