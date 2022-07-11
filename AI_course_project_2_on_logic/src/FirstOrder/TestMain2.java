package FirstOrder;

public class TestMain2 { //paradeigma apo diafaneies 11hs dialexis selida 18
	
	
	public static void main(String args[]){
		//dimiourgia olwn twn protasewn tis vasis gnwsis
		Relation r1 = new Relation("Dog",1,false);
		r1.addParameters(new Parameter("x1",false));
		Relation r2 = new Relation("Animal",1,true);
		r2.addParameters(new Parameter("x1",false));
		CNFComponents c1 = new CNFComponents();
		c1.addRelation(r1); c1.addRelation(r2);
		
		Relation r3 = new Relation("Dog",1,true);
		r3.addParameters(new Parameter("Fido",false));
		CNFComponents c2 = new CNFComponents();
		c2.addRelation(r3);
		
		Relation r4 = new Relation("Eats",1,true);
		r4.addParameters(new Parameter("x2",false));
		Relation r5 = new Relation("Animal",1,false);
		r5.addParameters(new Parameter("x2",false));
		CNFComponents c3 = new CNFComponents();
		c3.addRelation(r4); c3.addRelation(r5);
		
		CNFClauseFOL KB = new CNFClauseFOL();
		KB.getCNFComponents().add(c1);
		KB.getCNFComponents().add(c2);
		KB.getCNFComponents().add(c3);
		
		// PROSOXI: mpanei i arnisi autou pou thelw na apodeixw ara kratw to apotelesma pou epistrefei o algorithmos
		Relation r6 = new Relation("Eats",1,false);
		r6.addParameters(new Parameter("Fido",true));
		CNFComponents c4 = new CNFComponents();
		c4.addRelation(r6);
		CNFComponents copy = c4;
		System.out.println("NOT("+copy.PrintCNF()+") is:");
		//thelw na apodeixw oti o Fido trwei
		//ara ftixanw to CNF kai vazw ston algorithmo tin arnisi tou
		//an apantisei true tote apedixa auto pou ithela
		
	    ResolutionFOL r = new ResolutionFOL();
	    boolean ans=r.FOL_Resolution(KB, c4);
	    System.out.print(ans);
	    //r.searchRelationIn(KB, c4);
	    //System.out.println("NOT("+copy.PrintCNF()+")");
	    //System.out.print(" is: "+ans);
	    
	}
	
}
