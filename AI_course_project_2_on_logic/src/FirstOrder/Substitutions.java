package FirstOrder;

import java.util.Vector;

public class Substitutions{
	
	
	Vector<SubstComponents> thita;
	
	public Substitutions(){
		thita  = new Vector<SubstComponents>();
	}
	
	public void addSubstitution(SubstComponents s){
		thita.add(s);
	}

	public Vector<SubstComponents> getSubstitutions(){
		return thita;
	}
        
        public int getSize(){
            return thita.size();
        }
}

