

package FirstOrder;



import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
/* ena CNF component einai diazeuxeis apo facts i/kai Relations
 * px: ( (NOT Loves(y,x)) OR (NOT Animal(z)) OR (NOT Kills(x,z)) )
 */

public class CNFComponents {

	//HashSet<Relation> components;
	ArrayList<Relation> components;
	public CNFComponents(){
		//components = new HashSet<Relation>();
		components = new ArrayList<Relation>();
	}
	
	public ArrayList<Relation> getComponents(){
		return components;
	}
	
	public Iterator<Relation> getComponentsList()
    {
        return components.iterator();
    }
	
	public void addRelation(Relation r){
		components.add(r);
	}
        
        
        public String PrintCNF()
        {
           /*String s=" ";
           Relation r=null;
           while(this.getComponentsList().hasNext())
           {
               r=this.getComponentsList().next();
               s+=r.printRelation()+" OR ";
               
           }
           return s; */
        	
        	String s = "";
        	Relation r = null;
        	
        	/*while(getComponentsList().hasNext()){
        		r = getComponentsList().next();
        		System.out.println(r.printRelation() +" OR ");
        	}*/
        	
        	for(int i=0; i<components.size(); i++){
        		r = components.get(i);
        		s+=r.printRelation()+" OR ";
        	}
        	
        	return s;
        	
        }
	
}
