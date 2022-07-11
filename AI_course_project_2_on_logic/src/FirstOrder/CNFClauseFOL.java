package FirstOrder;

import java.util.ArrayList;

public class CNFClauseFOL {
	
	ArrayList<CNFComponents> clauses;
	
	public CNFClauseFOL(){
		clauses = new ArrayList<CNFComponents>();
	}
	
	public ArrayList<CNFComponents> getCNFComponents(){
		return clauses;
	}

}
