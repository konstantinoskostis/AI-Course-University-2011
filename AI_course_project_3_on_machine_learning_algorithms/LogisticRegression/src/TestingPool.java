import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TestingPool {
	
	ArrayList<String> theFeatures;
	ArrayList<TestingMail> testMails;
	String dir;
	File directory;
	FileReader fr;
	BufferedReader br;
	Scanner s;
	
	public TestingPool(){
		theFeatures = new ArrayList<String>();
		testMails = new ArrayList<TestingMail>();
		dir = "Testing";
		directory = new File(dir);
	}
	
	public ArrayList<TestingMail> transformToVectors(){
		
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
				  TestingMail t = new TestingMail();

				  for(int i=0; i<theFeatures.size(); i++){
					  t.getFeatureVector().add(new Feature(theFeatures.get(i)));
				  }

				  ArrayList<String> wordsOfFile = new ArrayList<String>();
				  char fname [] = f.getName().toCharArray();

				  fr = new FileReader(f);
				  br = new BufferedReader(fr);
				  s = new Scanner(br);

				 if(fname[0]=='s'){
					  t.setCategory(1);	  
				  }else{
					  t.setCategory(0);
				  }

				  while(s.hasNext()){
					  String w = s.next();
					  if(!wordsOfFile.contains(w)){
						  wordsOfFile.add(w);
					  }
				  }

				  for(int i=0; i<theFeatures.size(); i++){
					  if(wordsOfFile.contains(theFeatures.get(i))){
						  t.getFeatureVector().get(i).setValue(1);
					  }else{
						  t.getFeatureVector().get(i).setValue(0);
					  }
				  }

				  testMails.add(t);
			  }
			}catch(Exception e){
				e.printStackTrace();
			}// fill all vector with feature values

		   for(int i=0; i<testMails.size(); i++){
			   TestingMail t = testMails.get(i);
			   t = add_feature(t);
			   testMails.set(i, t);
		   }

		   return testMails;
	}
	
	private TestingMail add_feature(TestingMail t){
		TestingMail res = new TestingMail();
		Feature f = new Feature("?");

		f.setValue(1); // actually sets bias to 1
		res.getFeatureVector().add(f);

		for(int i=0; i<t.getFeatureVector().size(); i++){
			res.getFeatureVector().add(t.getFeatureVector().get(i));
		}
		res.setCategory(t.getCategory());

		return res;
	}

    public static void main(String args[]){
    	
    	TestingPool T = new TestingPool();
        ArrayList<TestingMail> a =  T.transformToVectors();
		
		for(int i=0; i<a.size(); i++){
		   System.out.print("< ");
		   for(int j=0; j<a.get(i).getFeatureVector().size(); j++){
			   System.out.print(a.get(i).getFeatureVector().get(j).getValue()+" ");
		   }
		   System.out.print(">");
		   System.out.print(" Category:"+a.get(i).getCategory() );
		   System.out.println();
		}
		System.out.println(a.get(1).getFeatureVector().size());
    	
    }

}
