
package FirstOrder;


public class TestMain 
{
    public static void main(String args[])
    {
    	
        Relation r1=new Relation("Animal",1,true);
        r1.addParameters(new Parameter("Tuna",true));
        
        CNFComponents c1=new CNFComponents();
        c1.addRelation(r1);
        
        //System.out.println(c1.PrintCNF());
        
        Relation r2=new Relation("Loves",2,false);
        r2.addParameters(new Parameter("y",false));
        r2.addParameters(new Parameter("x",false));
        //System.out.println(r2.printRelation());
        
        
        Relation r3=new Relation("Animal",1,false);
        r3.addParameters(new Parameter("z",false));
        //System.out.println(r3.printRelation());
        
        
        Relation r4=new Relation("Kills",2,false);
        r4.addParameters(new Parameter("x",false));
        r4.addParameters(new Parameter("z",false));
        //System.out.println(r4.printRelation());
        
        CNFComponents c2=new CNFComponents();
        c2.addRelation(r2);
        c2.addRelation(r3);
        c2.addRelation(r4);
        //System.out.println(c2.PrintCNF());
        //System.out.println(c2.PrintCNF());
        
    	/*Relation r1 = new Relation("Kills",2,true);
    	r1.addParameters(new Parameter("Jack",true));
    	r1.addParameters(new Parameter("Tuna",true));
    	
    	Relation r2 = new Relation("Kills",2,true);
    	r2.addParameters(new Parameter("Curiosity",true));
    	r2.addParameters(new Parameter("Tuna",true));
    	
    	CNFComponents c1=new CNFComponents();
        c1.addRelation(r1);  c1.addRelation(r2);
         
       
        Relation r3 = new Relation("Kills",2,false);
    	r3.addParameters(new Parameter("Curiosity",true));
    	r3.addParameters(new Parameter("Tuna",true));
        
        CNFComponents c2=new CNFComponents();
        c2.addRelation(r3);*/
    	
        Unifier u=new Unifier();
        CNFComponents res=u.unify(c1, c2);
        System.out.println(res.PrintCNF());
        
       
    }
    
    
}
