import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class TrainingPool {

	private ArrayList<TrainingMail> TP; 
	
	String dir;
	private File directory;
	private FileReader fr;
	private BufferedReader br;
	private Scanner s;
	ArrayList<String> theFeatures;
	
	public TrainingPool(){
		dir = "Training";
		directory = new File(dir);
		TP = new ArrayList<TrainingMail>();
		theFeatures = new ArrayList<String>();
	}
	
	public void transformToVectors(){
		
		try {
			File choosen = new File("Features.txt");
			FileReader frc = new FileReader(choosen);
			BufferedReader brc = new BufferedReader(frc);

			Scanner sc = new Scanner(brc);

			while(sc.hasNext()){
				theFeatures.add(sc.next());
			}

			frc.close();
			brc.close();
			sc.close();
		} catch (Exception e) {
            e.printStackTrace();
		}

		File [] files = directory.listFiles();
		try{
		  for(File f : files){
			  TrainingMail tm = new TrainingMail();

			  for(int i=0; i<theFeatures.size(); i++){
				  tm.getFeatuesVector().add(new Feature(theFeatures.get(i)));
			  }

			  ArrayList<String> wordsOfFile = new ArrayList<String>();
			  char fname [] = f.getName().toCharArray();

			  fr = new FileReader(f);
			  br = new BufferedReader(fr);
			  s = new Scanner(br);

			  if(fname[0] == 's'){
				  tm.setCategory(1);
			  }else{
				  tm.setCategory(0);
			  }

			  while(s.hasNext()){
				  String w = s.next();
				  if(!wordsOfFile.contains(w)){
					  wordsOfFile.add(w);
				  }
			  }

			  for(int i=0; i<theFeatures.size(); i++){
				  if(wordsOfFile.contains(theFeatures.get(i))){
					  tm.getFeatuesVector().get(i).setValue(1);
				  }else{
					  tm.getFeatuesVector().get(i).setValue(0);
				  }
			  }

			  TP.add(tm);
		  }
		}catch(Exception e){
			e.printStackTrace();
		}// fill all the vectors with their feature values
		
		for(int i=0; i<TP.size(); i++){
			System.out.print("< ");
			for(int j=0; j<TP.get(i).getFeatuesVector().size(); j++){
				System.out.print(TP.get(i).getFeatuesVector().get(j).getValue() +" ");
			}
			System.out.print(" >");
			System.out.print("C="+TP.get(i).getCategory());
			System.out.println();
		}
	}
	
	public void calculateProbabilities(){
		
		//count how many mails are SPAM and how many are HAM
		int spams = 0;
		int hams = 0;
		for(int i=0; i<TP.size(); i++){
			if(TP.get(i).getCategory() == 1){
				++spams;
			}else{
				++hams;
			}
		}
		
		File probabilitiesFile;
		FileWriter fw;
		BufferedWriter bw;
		
		try{

			probabilitiesFile = new File("Probabilities.txt"); 
			fw = new FileWriter(probabilitiesFile);
	        bw = new BufferedWriter(fw);
	        
	        for(int i=0; i<theFeatures.size(); i++){
	        	int spams_contain_word = 0;
	        	int spams_not_contain_word = 0;
	        	int hams_contain_word = 0;
	        	int hams_not_contain_word = 0;

	        	double pr_w1_c1 = 0.0;
	        	double pr_w0_c1 = 0.0;
	        	double pr_w1_c0 = 0.0;
	        	double pr_w0_c0 = 0.0;

	        	for(int j=0; j<TP.size(); j++){
	        		int category = TP.get(j).getCategory();
	        		int value = TP.get(j).getFeatuesVector().get(i).getValue();

	        		if(value==1 && category==1){
	        			++spams_contain_word;
	        		}else if(value==0 && category==1){
	        			++spams_not_contain_word ;
	        		}else if(value==1 && category==0){
	        			++hams_contain_word;
	        		}else{
	        			++hams_not_contain_word;
	        		}
	        	}

	        	pr_w1_c1 = (double)(1+spams_contain_word)/(2+spams);
	        	pr_w0_c1 = (double)(1+spams_not_contain_word)/(2+spams);
	        	pr_w1_c0 = (double)(1+hams_contain_word)/(2+hams);
	        	pr_w0_c0 = (double)(1+hams_not_contain_word)/(2+hams);

	        	bw.write(pr_w1_c1+"\t"+pr_w0_c1+"\t"+pr_w1_c0+"\t"+pr_w0_c0+"\n");
	        }

	        bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public static void main(String args[]){
		TrainingPool T = new TrainingPool();
		T.transformToVectors();
		T.calculateProbabilities();
	}
}
