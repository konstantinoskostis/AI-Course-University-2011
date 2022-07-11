package FirstOrder;

import java.util.ArrayList;

public class ResolutionFOL {

	public ResolutionFOL(){}
	
	public static boolean foundContradiction = false;
	//eiserxetai i arnisi autou pou theloume na apodeixoume
	
	
	public static CNFComponents searchRelationIn(CNFClauseFOL KB , CNFComponents comp){
		
		CNFComponents toTest , toReturn = null;
		Relation rToTest = null;
		Relation atIndex0 = comp.getComponents().get(0);
		Unifier u = new Unifier();
		
		ArrayList<CNFComponents> KBComponents = new ArrayList<CNFComponents>();
		KBComponents.addAll(KB.getCNFComponents());
		/* debug:
		System.out.println("Printing KB:");
		for(int i=0; i<KBComponents.size(); i++){
			System.out.println(KBComponents.get(i).PrintCNF());
		}
		System.out.println("we want to prove: NOT("+comp.PrintCNF()+")");*/
		
		for(int i=0; i<KBComponents.size(); i++){
			toTest = KBComponents.get(i);
			for(int j=0; j<toTest.getComponents().size(); j++){
			    rToTest = toTest.getComponents().get(j);
			    if(rToTest.getName().equals(atIndex0.getName()) && rToTest.getNegativity()!=atIndex0.getNegativity()){
			    	//debug:System.out.println("Found Contradiction:");
			    	 foundContradiction = true;
			    	toReturn = u.unify(toTest, comp);
			    	//debug: System.out.println("Result of Unification:"+toReturn.PrintCNF());
			    	return toReturn;
			    }else{
			    	//debug: System.out.println("Not yet Found Contradiction:");
			    }
			}
			
		}
		
		foundContradiction = false;
		return comp;//epestrepse to idio component ,opws mpike , den vrethike contradiction opote false
	}

	//Resolution me backward chaining , idea ilopoiisis apo to paradeigma tou vivliou twn Russel & Norvig selida 351
	public static boolean FOL_Resolution(CNFClauseFOL KB , CNFComponents negativeIn){
		
		CNFComponents result=negativeIn;
		for(;;){
			result = searchRelationIn(KB,result);
			if(foundContradiction == false ){
		        return false;
		    }
		    if(foundContradiction == true && result.getComponents().isEmpty()){
		       return true;
		    }
		}	
	}//end FOL_Resolution
	
}
