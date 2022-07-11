import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	

	public ArrayList<TrainingMail> transformToVectors() {

		try {

			File choosen = new File("Features.txt");
			FileReader frc = new FileReader(choosen);
			BufferedReader brc = new BufferedReader(frc);

			Scanner sc = new Scanner(brc);

			while (sc.hasNext()) {
				theFeatures.add(sc.next());
			}

			frc.close();
			brc.close();
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		File[] files = directory.listFiles();
		try {
			for (File f : files) {
				TrainingMail tm = new TrainingMail();

				for (int i = 0; i < theFeatures.size(); i++) {
					tm.getFeatureVector().add(new Feature(theFeatures.get(i)));
				}

				ArrayList<String> wordsOfFile = new ArrayList<String>();
				char fname[] = f.getName().toCharArray();

				fr = new FileReader(f);
				br = new BufferedReader(fr);
				s = new Scanner(br);

				if (fname[0] == 's') {
					tm.setCategory(1);
				} else {
					tm.setCategory(0);
				}

				while (s.hasNext()) {
					String w = s.next();
					if (!wordsOfFile.contains(w)) {
						wordsOfFile.add(w);
					}
				}

				for (int i = 0; i < theFeatures.size(); i++) {
					if (wordsOfFile.contains(theFeatures.get(i))) {
						tm.getFeatureVector().get(i).setValue(1);
					} else {
						tm.getFeatureVector().get(i).setValue(0);
					}
				}

				TP.add(tm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}// fill all the vectors with their feature values

		for (int i = 0; i < TP.size(); i++) {
			TrainingMail tm = TP.get(i);
			tm = copy_with_bias(tm);
			TP.set(i, tm);
		}

		return TP;
	}
	
	
	private TrainingMail copy_with_bias(TrainingMail t){
		TrainingMail res = new TrainingMail();
		Feature f = new Feature("?");

		f.setValue(1);
		res.getFeatureVector().add(f);

		for(int i=0; i<t.getFeatureVector().size(); i++){
			res.getFeatureVector().add(t.getFeatureVector().get(i));
		}
		res.setCategory(t.getCategory());

		return res;
	}

	public static void main(String args[]){
		TrainingPool T = new TrainingPool();
		ArrayList<TrainingMail> a =  T.transformToVectors();
		
		for(int i=0; i<a.size(); i++){
		   System.out.print("< ");
		   for(int j=0; j<a.get(i).getFeatureVector().size(); j++){
			   System.out.print(a.get(i).getFeatureVector().get(j).getValue()+" ");
		   }
		   System.out.print(" Category:"+a.get(i).getCategory() );
		   System.out.print(">");
		   System.out.println();
		}
	}
	
	
}
