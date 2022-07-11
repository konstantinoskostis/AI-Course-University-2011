package FirstOrder;

import java.util.ArrayList;
import java.util.HashSet;

public class Unifier {

	Substitutions theta;	
	public static boolean canBeUnified;
	
	public Unifier(){
		theta = new Substitutions();
		canBeUnified = false;
	}
		
	public CNFComponents unify(CNFComponents CNF1 , CNFComponents CNF2){
		
		Relation rCNF1 , rCNF2 , r = null;
		Parameter par_rCNF1 , par_rCNF2 ,p = null;
		SubstComponents pair_search = null;
		SubstComponents pair = null;
		CNFComponents outputOfUnification = new CNFComponents();
		ArrayList<Relation> newCNF1Relations = CNF1.getComponents();
		ArrayList<Relation> newCNF2Relations = CNF2.getComponents();
		int counter;
		
		for(int c1=0; c1<newCNF1Relations.size(); c1++){ //oso to CNF1 exei akoma tipous 
			rCNF1 = newCNF1Relations.get(c1);
			
			for(int c2=0; c2<newCNF2Relations.size(); c2++){
				rCNF2 = newCNF2Relations.get(c2);
				
				if( (rCNF1.getName().equals(rCNF2.getName())) && (rCNF1.getNegativity() != rCNF2.getNegativity()) ){ //ena apo ta dio Relations exei NOT enw to allo den exei kai to onoma tou Relation einai idio -> pithano Unification
					
					counter=0;
					//elegxos twn parametrwn
					for(int i=0; i<rCNF1.getArity(); i++){//dietrexe ola ta parameters
						par_rCNF1 = rCNF1.ReturnArray().get(i);
						par_rCNF2 = rCNF2.ReturnArray().get(i);
						
                                                
                        if(  (par_rCNF1.isConstant() && par_rCNF2.isConstant() && par_rCNF1.getParameterName().equals(par_rCNF2.getParameterName()) )
                                                        
                           ||(par_rCNF1.isConstant() && !(par_rCNF2.isConstant())) 
                                                      
                           || (!(par_rCNF1.isConstant()) && par_rCNF2.isConstant())
                        ){
                        	counter +=1;
                            pair = new SubstComponents(par_rCNF1 , par_rCNF2); //dimiourgia enos zeugariou antikatastasis
                            //pair.replace();
                            pair.addReplacementResult();
                            //pair.printSubstitution();
						   theta.addSubstitution(pair);    
                         }
                                                
						/*if(!par_rCNF1.isEqualTo(par_rCNF2)){ //oi parametroi einai diaforetikes ->substitution
							pair = new SubstComponents(par_rCNF1 , par_rCNF2); //dimiourgia enos zeugariou antikatastasis
							theta.addSubstitution(pair);
						}*/
						
					}
					
					if(counter==rCNF1.getArity()){//an o arithmos twn antikatastasewn einai isos me ton arithmo twn parametrwn kane enopoiisi
					  //diegrapse ta relations pou enopoiountai
					  newCNF1Relations.remove(rCNF1);
					  newCNF2Relations.remove(rCNF2);
					  canBeUnified = true;
					}else{
						canBeUnified = false;
					}
					
				}
				
			}//dietrexe tin deuteri parastasi
			
		}//dietrexe tin prwti parastasi
		
		//prosthese sto apotelesma ola ta relations pou emeinan
		
		for(int i=0; i<newCNF1Relations.size(); i++){
			outputOfUnification.addRelation(newCNF1Relations.get(i));
		}
		for(int i=0; i<newCNF2Relations.size(); i++){
			outputOfUnification.addRelation(newCNF2Relations.get(i));
		}
		
        //sto apotelesma kane tis antikatastaseis    
		for (int ri=0; ri<outputOfUnification.getComponents().size(); ri++) {
			r = outputOfUnification.getComponents().get(ri);
			for(int i=0; i<r.getArity(); i++){//dietrexe tis parametrous mias sxesis
				p = r.ReturnArray().get(i);
				//System.out.println(p.getParameterName());
				//dietrexe oles tis antikatastaseis
				for(int j=0; j<theta.getSize(); j++){
					pair_search = theta.getSubstitutions().get(j);
					if(p.isEqualTo(pair_search.getPair().firstElement()) || p .isEqualTo(pair_search.getPair().get(1)) ){
						p.setParameter(pair.getPair().get(2));//kane tin antikatastasi sto teliko apotelesma
						//System.out.println(p.getParameterName());
					}
				}
			}
		}      
         
		return outputOfUnification;
	}
	
	public Substitutions getSubstsFromThisUnification(){
		return theta;
	}
	
}
