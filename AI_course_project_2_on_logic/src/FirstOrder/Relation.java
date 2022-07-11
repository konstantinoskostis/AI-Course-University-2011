//ayth h klassh adiproswpeuei mia sxesh(px Likes,Has,InCupboard,IsFather ktl)
package FirstOrder;
import java.util.ArrayList;

public class Relation extends Type
{
	private String name;//onoma sxeshs
	private int arity;//posous orous exei h sxesh(px Likes(x,y) arity=2).
	//Vector pou periexei tis parametrous ths sxeshs kai exei megethos arity.
	protected ArrayList<Parameter> p; 
	
	Relation(String name,int ar,boolean valueIn)
        {
         super(valueIn);
		this.name=name;
		this.arity=ar;
		p = new ArrayList<Parameter>();
	}
		
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getArity(){
		return arity;
	}
	
	public ArrayList<Parameter> ReturnArray(){
		return this.p;
	}
        
        public void addParameters(Parameter par)
        {
                this.p.add(par);
        }
        
        
        public String printRelation()
        {
            String s="";
            int i;
            if(this.getNegativity())
            {   
                s=this.getName()+"(";
                for(i=0; i<this.getArity()-1; i++)
                {
                    s+=this.p.get(i).getParameterName()+",";
                }
                s+=this.p.get(i++).getParameterName()+")";
            }else{
            	s="NOT "+this.getName()+"(";
                for(i=0; i<this.getArity()-1; i++)
                {
                    s+=this.p.get(i).getParameterName()+",";
                }
                s+=this.p.get(i++).getParameterName()+")";
            }
         return s;
        }
}//end class Realtion
	
