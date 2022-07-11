import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class NaiveBayes {
	
	double pr_spam , pr_ham;
	ArrayList<TestingMail> mailsToTest;
	ArrayList<String> theFeatures;
    // data structures for the probabilities
	ArrayList<Double> pr_exists_in_spam;
	ArrayList<Double> pr_not_exists_in_spam;
	ArrayList<Double> pr_exists_in_ham;
	ArrayList<Double> pr_not_exists_in_ham;
	
	public NaiveBayes(){
		pr_spam = 0;
		pr_ham = 0;

		mailsToTest = new ArrayList<TestingMail>();
		theFeatures = new ArrayList<String>();
		TestingPool tp = new TestingPool();
		mailsToTest = tp.transformToVectors();

		calculate_simple_probs();

		pr_exists_in_spam = new ArrayList<Double>();
		pr_not_exists_in_spam = new ArrayList<Double>();
		pr_exists_in_ham = new ArrayList<Double>();
		pr_not_exists_in_ham = new ArrayList<Double>();
	}
	
	public void classify(){
		
		//Read the probabilities file and push them into the respective data-structures
		try{
			File prob_file = new File("Probabilities.txt");
			FileReader fr = new FileReader(prob_file);
			BufferedReader br = new BufferedReader(fr);
			Scanner s = new Scanner(br);
			
			while(s.hasNext()){
				pr_exists_in_spam.add(Double.parseDouble(s.next()));
				pr_not_exists_in_spam.add(Double.parseDouble(s.next()));
				pr_exists_in_ham.add(Double.parseDouble(s.next()));
				pr_not_exists_in_ham.add(Double.parseDouble(s.next()));
			}

			s.close(); // close the scanner which reads the probabilities file
			
			int counter = 0;
			double success = 0.0;
		    for(int i=0; i<mailsToTest.size(); i++){ // for every test example
		    	
		    	TestingMail tm = mailsToTest.get(i);

				// calculate the probability that the test example mail is SPAM (the numerator)
		    	double product1 = 1; 
		    	double PR_SPAM = 0.0;
		    	
		    	for(int j=0; j<tm.getFeatuesVector().size(); j++){ // iterate over the features of the test mail
		    		if(tm.getFeatuesVector().get(j).getValue() == 1){
		    			product1 *= pr_exists_in_spam.get(j);
		    		}else{
		    			product1 *= pr_not_exists_in_spam.get(j);
		    		}
		    	}
		    	PR_SPAM = pr_spam * product1;
		    	
				// calculate the probability that the test example mail is HAM (the numerator)
		    	double product2 = 1; 
		    	double PR_HAM = 0.0;
		    	
		    	for(int j=0; j<tm.getFeatuesVector().size(); j++){ // iterate over the features of the test mail
		    		if(tm.getFeatuesVector().get(j).getValue() == 1){
		    			product2 *= pr_exists_in_ham.get(j); 
		    		}else{
		    			product2 *= pr_not_exists_in_ham.get(j);
		    		}
		    	}
		    	PR_HAM = pr_ham * product2;
		    	
				// the ratio of the probabilities actually categorizes the test mail
		    	double ratio = (double)PR_SPAM/PR_HAM;
		    	int category;
		    	
		    	if(ratio > 1){
		    		category = 1;
		    	}else{
		    		category = 0;
		    	}
		    	
		    	System.out.println("True Category:"+tm.getCategory() + " Computed Category by Naive Bayes:"+ category);
		        if(category == tm.getCategory()){
		        	++counter;
		        }
		    }

			// Check how well we did
		    success = (double)counter/mailsToTest.size();
		    System.out.println("Success: "+success*100+"%");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void calculate_simple_probs(){
		
		int allSpams = 0;
		int allHams = 0;
		int allMessages = 0;
		
		for(int i=0; i<mailsToTest.size(); i++){
			if(mailsToTest.get(i).getCategory() == 1){
				++allSpams;
			}else{
				++allHams;
			}
			++allMessages;
		}
		pr_spam = (double)allSpams/allMessages;
		pr_ham = (double)allHams/allMessages;
	}
	
	public static void main(String args[]){
		NaiveBayes nb = new NaiveBayes();
		nb.classify();
	}

}
