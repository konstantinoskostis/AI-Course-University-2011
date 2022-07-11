import java.io.*;
import java.util.*;
import java.lang.Math;

public class FeatureSelection {
	
	String dir;
	File directory;
	FileReader fr;
	BufferedReader br;
	Scanner s;
	ArrayList<String> allWords;
	ArrayList<Integer> wordInSpams , wordInHams , wordInAll;
	int allSpams , allHams , allMessages;
	double pr_spam , pr_ham,H_C;
	ArrayList<Double> MI; //Mutual Information
	
	public FeatureSelection(){
		dir = "Training";
		directory = new File(dir);
		allWords = new ArrayList<String>();
		allSpams = 0;
		allHams = 0;
		allMessages = 0;
		pr_spam = 0;
		pr_ham = 0;
		H_C = 0;
		wordInSpams = new ArrayList<Integer>();
		wordInHams = new ArrayList<Integer>();
		wordInAll = new ArrayList<Integer>();
		MI = new ArrayList<Double>();
	}
	
	public void selectFeatures(){
		File [] files = directory.listFiles();
		for(File f : files){
			
		    try{
		    	char fname [] = f.getName().toCharArray();
				if(fname[0] == 's'){
					++allSpams;
				}else{
					++allHams;
				}
				++allMessages;
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				s = new Scanner(br);
				while(s.hasNext()){
					String w = s.next();
					if(!allWords.contains(w)){
					   allWords.add(w);
					}
					//System.out.println(s.next());
				}
				fr.close();
				br.close();
				s.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		pr_spam = (double)allSpams/allMessages;
		pr_ham = (double)allHams/allMessages;
		H_C = -(double)(pr_spam * log2(pr_spam)) -(double)(pr_ham * log2(pr_ham));
		
		// how many times does a word appear in spams
		// how many times does a word apper in hams
		// how many times does a word appear in all messages
		for(int i=0; i<allWords.size(); i++){ //gia kathe mia lexi
			
			int counter_spams = 0;
			int counter_hams = 0;
			int counter_all = 0;
			String feat = allWords.get(i);
			
			for(File f : files){
				
			    try{
			    	char fname [] = f.getName().toCharArray();
					fr = new FileReader(f);
					br = new BufferedReader(fr);
					s = new Scanner(br);
					while(s.hasNext()){
						String w = s.next(); 
						if(feat.equals(w)){
							if(fname[0] == 's'){
								++counter_spams;
							}else{
								++counter_hams;
							}
							++counter_all;
							break;
						}
					}
					fr.close();
					br.close();
					s.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			System.out.println(feat+"\t "+counter_spams+"\t "+counter_hams+"\t "+counter_all);
			wordInSpams.add(counter_spams);
			wordInHams.add(counter_hams);
			wordInAll.add(counter_all);	
		}

		for(int j=0; j<allWords.size(); j++){
			double p_w_1 = (double)wordInAll.get(j)/allMessages;
        	System.out.print("P(w=1)="+p_w_1+" ");
        	double p_w_0 = 1.0 - p_w_1;
        	System.out.print("P(w=0)="+p_w_0+" ");
        	double p1 = (double)(wordInHams.get(j)+1)/(allMessages+2);//P(C=0,w=1)
        	System.out.print("P(C=0,w=1)="+p1+" ");
        	double s1 = (double)p1*log2(p1/(pr_ham*p_w_1));
        	System.out.print("S1="+s1+" ");
        	double p2 = (double)(wordInSpams.get(j)+1)/(allMessages+2);//P(C=1,w=1)
        	System.out.print("P(C=1,w=1)="+p2+" ");
        	double s2 = (double)p2*log2(p2/(pr_spam*p_w_1));
        	System.out.print("S2="+s2+" ");
        	double p3 = (double)(allHams-wordInHams.get(j)+1)/(allMessages+2);//P(C=1,w=1)
        	System.out.print("P(C=0,w=0)="+p3+" ");
        	double s3 = (double)p3*log2(p3/(pr_ham*p_w_0));
        	System.out.print("S3="+s3+" ");
        	double p4 = (double)(allSpams-wordInSpams.get(j)+1)/(allMessages+2);//P(C=1,w=1)
        	System.out.print("P(C=1,w=0)="+p4+" ");
        	double s4 = (double)p4*log2(p4/(pr_spam*p_w_0));
        	System.out.print("S4="+s4+" ");
        	double mi = (double)s1+s2+s3+s4;
        	System.out.println("MI="+mi);
        	MI.add(mi);
		}
		
		System.out.println("All Words:"+allWords.size() + " All MI:"+MI.size());
		
		// sort the words based on mutual-information (simple bubblesort)
        for(int pass=0; pass<allWords.size()-1; pass++){
        	for(int k=0; k<allWords.size()-1; k++){
        		
        		if(MI.get(k) < MI.get(k+1)){
        			
        			double hold1 = MI.get(k);
        			MI.set(k, MI.get(k+1));
        			MI.set(k+1, hold1);
        			
        			String hold2 = allWords.get(k);
        			allWords.set(k, allWords.get(k+1));
        			allWords.set(k+1, hold2);
        		}
        	}
        }
        
        System.out.println("After BubbleSort:");
        for(int i=0; i<allWords.size(); i++){
        	System.out.println(allWords.get(i)+" : "+ MI.get(i));
        }
        
        FileWriter fw;
		try {
			fw = new FileWriter("Features2.txt");
	        BufferedWriter bw = new BufferedWriter(fw);
	        for(int i=0; i<100; i++){
	        	bw.write(allWords.get(i)+"\n");
	        }
	        bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double log2(double n){
		return (double)Math.log(n)/Math.log(2);
	}
	
	public static void main(String args[]){
		FeatureSelection f = new FeatureSelection();
		f.selectFeatures();
	}

}
