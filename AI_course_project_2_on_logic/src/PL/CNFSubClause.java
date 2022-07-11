
package PL;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;


public class CNFSubClause implements Comparable<CNFSubClause>
{
    /*To hashSet periexei ta literals enos SubClause
    xrhsimopoioume hashset gia na mhn exoume diplotypa.
    px. p1 AND p1=p1.*/
    private HashSet<Literal> literals;
            
    public CNFSubClause()
    {
        literals = new HashSet<Literal>();
    }
         
    public  HashSet<Literal> getLiterals()            
    {
        return literals;
    }
    
    public Iterator<Literal> getLiteralsList()
    {
        return literals.iterator();
    }
         
    public boolean isEmpty()
    {
        return literals.isEmpty();
    }
    
    public void addLitetal(Literal l)
    {
        literals.add(l);
    }
    

    /*Kanei resolution anamesa se dio subClauses kai dhmiourgei ena neo clause poy
     *periexei ta literals kai twn dio ektos apo ayta pou "akurwnontai" metaksi toys
     *px p1 AND p2 AND NOTp3   p1 AND p2 AND p3 | p1 AND p2 
     **/
    public static ArrayList<CNFSubClause> resolution(CNFSubClause CNF_1, CNFSubClause CNF_2)
    {
        //vector me ta subclauses
        ArrayList<CNFSubClause> newClauses = new ArrayList<CNFSubClause>();
		
		//iterator pou deixnei sth prwth thesh toy subclause1
        Iterator<Literal> iter = CNF_1.getLiteralsList();

        //oso h list toy CNF1 exei adikeimena epanelave
        while(iter.hasNext())
        {            
            Literal l = iter.next();//pairnei to prwto literal
            Literal m = new Literal(l.getName(), !l.getNeg());//dhmiourgei ena literal pou einai arnhsh toy literal l

            //elegxei an to m yparxei kapoy sto pinaka toy CNF2
            if(CNF_2.getLiterals().contains(m))
            {
                /*afoy vrei sta dio CNFs ena lteral kai thn arnhsh toy
                kataskeyasei ena neo SubClause me ta stoixeia twn CNF1 kai CNF xwris omws
                ta literals pou yparxoun kai sta dyo san p1 sto CNF1 kai NOTp1 sto CNF2
                h adistrofa*/
                CNFSubClause newClause = new CNFSubClause();

                //sto newClause vazoume ta literals twn CNF1 kai CNF2
                //diagrafoume ta p1,NOTp1
                HashSet<Literal> CNF_1_Lits = new HashSet(CNF_1.getLiterals());
                HashSet<Literal> CNF_2_Lits = new HashSet(CNF_2.getLiterals());
                CNF_1_Lits.remove(l);
                CNF_2_Lits.remove(m);

      			newClause.getLiterals().addAll(CNF_1_Lits);
                newClause.getLiterals().addAll(CNF_2_Lits);
				//add to neo clause ston vector me ta nea Clauses
                newClauses.add(newClause);
            }
        }//end while
        
        //epistrofh toy vector
        return newClauses;
    }
    
  @Override
    public boolean equals(Object obj)
    {
        CNFSubClause l = (CNFSubClause)obj;

        Iterator<Literal> iter = l.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
            if(!this.getLiterals().contains(lit))
                return false;
        }
        
        if(l.getLiterals().size() != this.getLiterals().size())
            return false;
        
        return true;
    }
	
    //@Override
    public int hashCode()
    {
        Iterator<Literal> iter = this.getLiteralsList();
        int code = 0;
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
               code = code + lit.hashCode();
        }
        
        return code;
    }
	
    //@Override
    public int compareTo(CNFSubClause x)
    {
        int cmp = 0;
        
        Iterator<Literal> iter = x.getLiteralsList();
        
        while(iter.hasNext())
        {
            Literal lit = iter.next();
            
            Iterator<Literal> iter2 = this.getLiterals().iterator();
                    
            while(iter2.hasNext())
            {                
                Literal lit2 = iter2.next();
                cmp = cmp + lit.compareTo(lit2);
            }
        }
        
        return cmp;
    }
    
    
    public static CNFSubClause DeMorgan(CNFSubClause c)
    {
        Literal l=null;
        CNFSubClause DeMorgan=new CNFSubClause();
        while(c.getLiteralsList().hasNext())
        {
            l=c.getLiteralsList().next();
            l.setNeg(!(l.getNeg()));
            if(l.getOperator()!=-1)
            {
                if(l.getOperator()==0)
                {
                    l.setOperator(1);
                }
                else
                {
                    l.setOperator(0);
                }
            }
            DeMorgan.addLitetal(l);
        } 
        return DeMorgan;
    }//end Demorgan
}
