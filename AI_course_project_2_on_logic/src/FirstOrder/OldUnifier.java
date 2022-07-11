
	package FirstOrder;

	import java.util.ArrayList;
	import java.util.HashSet;

	public class OldUnifier {

		Substitutions theta;
		SubstComponents pair;
		
		public OldUnifier(){
			theta = new Substitutions();
		}
			
		public CNFComponents unify(CNFComponents CNF1 , CNFComponents CNF2){
			
			Relation rCNF1 , rCNF2 , r = null;
			Parameter par_rCNF1 , par_rCNF2 ,p = null;
			CNFComponents outputOfUnification = new CNFComponents();
			ArrayList<Relation> newCNF1Relations = CNF1.getComponents();
			ArrayList<Relation> newCNF2Relations = CNF2.getComponents();
			int counter;
			
			while(CNF1.getComponentsList().hasNext()){ //oso to CNF1 exei akoma tipous 
				rCNF1 = CNF1.getComponentsList().next();
				
				while(CNF2.getComponentsList().hasNext()){
					rCNF2 = CNF2.getComponentsList().next();
					
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
						}
						
					}
					
				}//dietrexe tin deuteri parastasi
				
			}//dietrexe tin prwti parastasi
			
			//prosthese sto apotelesma ola ta relations pou emeinan
			
			while(newCNF1Relations.iterator().hasNext()){
				outputOfUnification.addRelation(newCNF1Relations.iterator().next());
			}
			while(newCNF2Relations.iterator().hasNext()){
				outputOfUnification.addRelation(newCNF2Relations.iterator().next());
			}
			
	        //sto apotelesma kane tis antikatastaseis    
			while (outputOfUnification.getComponentsList().hasNext()) {
				r = outputOfUnification.getComponentsList().next();
				for(int i=0; i<r.getArity(); i++){//dietrexe tis parametrous mias sxesis
					p = r.ReturnArray().get(i);
					//dietrexe oles tis antikatastaseis
					for(int j=0; j<theta.getSize(); j++){
						pair = theta.getSubstitutions().elementAt(j);
						if(p == pair.getPair().elementAt(0) || p == pair.getPair().elementAt(1)){
							p.setParameter(pair.getPair().elementAt(2));//kane tin antikatastasi sto teliko apotelesma
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



