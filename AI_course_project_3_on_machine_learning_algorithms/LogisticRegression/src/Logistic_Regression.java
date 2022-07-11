import java.util.ArrayList;
import java.lang.Math;

public class Logistic_Regression {
	
	double pr_spam , pr_ham;
	int allMessages , allSpams , allHams;
	ArrayList<TrainingMail> trs;
	double threshold;

	public Logistic_Regression(){
		threshold = 0.025;
		trs = new ArrayList<TrainingMail>();
		TrainingPool tp = new TrainingPool();
		trs = tp.transformToVectors();
	}
	
	public ArrayList<Double> learn_weights(){
		int size = trs.get(0).getFeatureVector().size();

		//start with new weights all set to 1
		ArrayList<Double> weights = new ArrayList<Double>();
		for(int i=0; i<size; i++){
			weights.add((double)1);
		}

		// start with old weights set to 0
		ArrayList<Double> old_weights = new ArrayList<Double>();
		for(int i=0; i<size; i++){
			old_weights.add(0.0);
		}

		double learning_rate = 0.01;
		int age = 0;

		do{
			for(int i=0; i<size; i++){
				old_weights.set(i, weights.get(i));
			}
			
			for(int w=0; w<size; w++){// for every w[l]
				double sum = 0;

				for(int t=0; t<trs.size(); t++){// for every training example
					TrainingMail tm = trs.get(t);
					int category = tm.getCategory();
					double z = product_for_training(weights, tm);
					double pr = (double)1/(1+Math.exp(-z));
				   	sum += (category-pr)*tm.getFeatureVector().get(w).getValue();
				}

				weights.set(w , weights.get(w)+learning_rate*sum );
			}
			
			System.out.println("Weights of age:"+ age++);
			System.out.print("< ");
			for(int j=0; j<size; j++){
			   System.out.print(weights.get(j)+" ");	
			}
			System.out.print(">");
			System.out.println();
		}while(!convergence(weights, old_weights)); // not sure if convergence check is correct here
		
		return weights;
	}

	private boolean convergence(ArrayList<Double> new_w , ArrayList<Double> old_w){
		int counter = 0;

		for(int i=0; i<new_w.size(); i++){
			if( Math.abs((new_w.get(i) - old_w.get(i))) <= this.threshold){
				++counter;
			}
		}

		if(counter == new_w.size()){
		   return true;	
		}

		return false;
	}
	
	private double product_for_training(ArrayList<Double> wIn , TrainingMail tIn){
		double result = 0.0;
		ArrayList<Integer> x = new ArrayList<Integer>();

		int size = tIn.getFeatureVector().size();

		for(int i=0; i<size; i++){
			x.add(tIn.getFeatureVector().get(i).getValue());	
		}
		for(int i=0; i<size; i++){
			result += wIn.get(i)*x.get(i);
		}
		return result;
	}

	public static double product_for_testing(ArrayList<Double> wIn , TestingMail tIn){
		double result = 0.0;
		ArrayList<Integer> x = new ArrayList<Integer>();

		int size = tIn.getFeatureVector().size();

		for(int i=0; i<size; i++){
			x.add(tIn.getFeatureVector().get(i).getValue());	
		}
		for(int i=0; i<size; i++){
			result += wIn.get(i)*x.get(i);
		}
		return result;
	}
	

	public static void main(String args[]){
		Logistic_Regression lr = new Logistic_Regression();
		ArrayList<Double> w = lr.learn_weights();
		TestingPool tp = new TestingPool();
		ArrayList<TestingMail> tests = tp.transformToVectors();
		int counter = 0;
		double success = 0;
		
		for(int i=0; i<tests.size(); i++){ // categorize every test example
			
			// calculate the probability of test example to be SPAM
			// calculate the probability of test example to be HAM
			double z = (double)product_for_testing(w,tests.get(i));
			double pr_spam = (double)1/(1+Math.exp(-z));
			double pr_ham = (double)1-pr_spam;
			
			int category;
			if(pr_spam > pr_ham){
				category = 1;
			}else{
				category = 0;
			}
			
			if(category == tests.get(i).getCategory()){
			   ++counter;	
			}
			
		   System.out.println("True Category:"+tests.get(i).getCategory() + " Computed Category by Logistic regression:"+category);
		}

		// Check how well we did
		success = (double)counter/tests.size();
		System.out.println("Success: "+ success*100+ " %");
	}
	 
}
