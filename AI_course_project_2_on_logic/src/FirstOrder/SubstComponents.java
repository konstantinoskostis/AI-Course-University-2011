package FirstOrder;

import java.util.Vector;

public class SubstComponents {

	Parameter p1 , p2;
	//Parameter copy_p1 , copy_p2;
	Vector<Parameter> pair;
	public SubstComponents(Parameter p1In , Parameter p2In){
		pair = new Vector<Parameter>();
		p1 = p1In;
		p2 = p2In;
		makePair();
	}
	
	public void makePair(){
		pair.add(p1);
		pair.add(p2);
	}
	
	public Vector<Parameter> getPair(){
		return pair;
	}
	
        
       public Parameter replace()
       {
           Parameter par=null;
           if(this.p1.isConstant() && !this.p2.isConstant())
           {par= p1;}
           else if(!this.p1.isConstant() && this.p2.isConstant())
           {par= p2;}
           else if(!this.p1.isConstant() && !this.p2.isConstant())
           {par= p1;}
           else if(p1.isConstant() && p1.isConstant() && p1.getParameterName().equals(p1.getParameterName()))
           {return p1;}    
           return par;
       }
       
       public void addReplacementResult(){
    	   pair.add(replace()); //twra to vector einai<x,y,x> , auto simainei oti i metavliti x allaxe me tin y kai kai to apotelesma tis antikatastasi einai i x
       } //kati san sinartisi <param1,param2,apotelesma>
       
       public void printSubstitution(){
    	   String s="<";
    	   for(int i=0; i<pair.size(); i++){
    		   s+=" " + pair.get(i).getParameterName()+",";
    	   }
    	   s+=">";
    	   System.out.println(s);
       }
	/*
	public Parameter makeSubstitution(){
		copy_p1 = new Parameter(p1);
		copy_p2 = new Parameter(p2);
		copy_p1 = copy_p2;
		return copy_p1;
	}*/
	
}
