
/*Ayth h class adiproswpeyei syzeukeis diazeuksewn/th vash gnwshs*/
package PL;
import java.util.ArrayList;

public class CNFClause 
{
    public ArrayList<CNFSubClause> Clauses = new ArrayList<CNFSubClause>();
    
    public ArrayList<CNFSubClause> getSubclauses()
    {
        return Clauses;
    }
    
    //elegxei an mesa senan vector yparxei hdh ena claus
    public boolean contains(CNFSubClause newS)
    {
        for(int i = 0; i < Clauses.size(); i ++)
        {
            if(Clauses.get(i).getLiterals().equals(newS.getLiterals()))
            {
                return true;
            }
        }
        return false;
    }
    
    //Resolution dexetai o eisodo mia vash gnwshs kai to literal pou theloyme na deiksoume
    //oti apodeiknyetai apo th vash gnwshs
    public static boolean PL_Resolution(CNFClause KB, Literal a)
    {
        //CNF clause pou periexei ola ta clauses ths vashs gnwshs
        CNFClause clauses = new CNFClause();
        clauses.getSubclauses().addAll(KB.getSubclauses());
        
        //vazei mesa kai ena clause me thn arnhsh toy a
        Literal aCopy = new Literal(a.getName(), !a.getNeg());
        CNFSubClause aClause = new CNFSubClause();
        aClause.getLiterals().add(aCopy);
        clauses.getSubclauses().add(aClause);

        System.out.println("We want to prove...");
        a.print();

        boolean stop = false;
        int step = 1;
        //efarmozoume resolution mexri na mh borei na paraxthei kanena neo clause
        while(!stop)
        {
        	//vector opou tha parei ta nea subclauses
            ArrayList<CNFSubClause> newsubclauses = new ArrayList<CNFSubClause>();
            ArrayList<CNFSubClause> subclauses = clauses.getSubclauses();

            System.out.println("Step:" + step);
            step++;
            //Gia kathe zeygari clauses
            for(int i = 0; i < subclauses.size(); i++)
            {
                CNFSubClause Ci = subclauses.get(i);

                for(int j = i+1; j < subclauses.size(); j++)
                {
                    CNFSubClause Cj = subclauses.get(j);

                    //pairnoume kathe neo clause
                    ArrayList<CNFSubClause> new_subclauses_for_ci_cj = CNFSubClause.resolution(Ci, Cj);

                    //elegxoume gia nea subclauses
                    for(int k = 0; k < new_subclauses_for_ci_cj.size(); k++)
                    {
                        CNFSubClause newsubclause = new_subclauses_for_ci_cj.get(k);

                        //an to subclause poy paraxthike einai keno tote apodeiskame ayto p thelame
                        if(newsubclause.isEmpty()) 
                        {   
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                           // Ci.print();
                            System.out.println("and");
                            //Cj.print();
                            System.out.println("produced:");
                            System.out.println("Empty subclause!!!");
                            System.out.println("----------------------------------");
                            return true;
                        }
                        
                        //ola ta subclauses p einai kainourgia ginodai add sth vash gnvshs
                        if(!newsubclauses.contains(newsubclause) && !clauses.contains(newsubclause))
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            //Ci.print();
                            System.out.println("and");
                            //Cj.print();
                            System.out.println("produced:");
                            //newsubclause.print();
                            //newsubclauses.add(newsubclause);
                            System.out.println("----------------------------------");
                        }
                    }                           
                }
            }
            
            boolean newClauseFound = false;

            //elegxei an dhmiourgithikan nea clauses se aythn thn epanalhpsh
            for(int i = 0; i < newsubclauses.size(); i++)
            {
                if(!clauses.contains(newsubclauses.get(i)))
                {
                    clauses.getSubclauses().addAll(newsubclauses);                    
                    newClauseFound = true;
                }                        
            }

            //an den dhmiourgithikan shmainei oti den ginetai na apodeixthei logika apo th vash gnwshs to a
            if(!newClauseFound)
            {
                System.out.println("Not found new clauses");
                stop = true;
            }   
        }
        return false;
    } 
    
    public static boolean PL_ResolutionSub(CNFClause KB,CNFSubClause a)
    {
        //CNF clause pou periexei ola ta clauses ths vashs gnwshs
        CNFClause clauses = new CNFClause();
        clauses.getSubclauses().addAll(KB.getSubclauses());
        
        //vazei mesa kai ena clause me thn arnhsh toy a
        //Literal aCopy = new Literal(a.getName(), !a.getNeg());
        CNFSubClause aDeMorgan=CNFSubClause.DeMorgan(a);
        
        //CNFSubClause aClause = new CNFSubClause();
        //aClause.getLiterals().add(aCopy);
        //clauses.getSubclauses().add(aClause);
        clauses.getSubclauses().add(aDeMorgan);

        //System.out.println("We want to prove...");
       // a.print();

        boolean stop = false;
        int step = 1;
        //efarmozoume resolution mexri na mh borei na paraxthei kanena neo clause
        while(!stop)
        {
            //vector opou tha parei ta nea subclauses
            ArrayList<CNFSubClause> newsubclauses = new ArrayList<CNFSubClause>();
            ArrayList<CNFSubClause> subclauses = clauses.getSubclauses();

            System.out.println("Step:" + step);
            step++;
            //Gia kathe zeygari clauses
            for(int i = 0; i < subclauses.size(); i++)
            {
                CNFSubClause Ci = subclauses.get(i);

                for(int j = i+1; j < subclauses.size(); j++)
                {
                    CNFSubClause Cj = subclauses.get(j);

                    //pairnoume kathe neo clause
                    ArrayList<CNFSubClause> new_subclauses_for_ci_cj = CNFSubClause.resolution(Ci, Cj);

                    //elegxoume gia nea subclauses
                    for(int k = 0; k < new_subclauses_for_ci_cj.size(); k++)
                    {
                        CNFSubClause newsubclause = new_subclauses_for_ci_cj.get(k);

                        //an to subclause poy paraxthike einai keno tote apodeiskame ayto p thelame
                        if(newsubclause.isEmpty()) 
                        {   
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                           // Ci.print();
                            System.out.println("and");
                            //Cj.print();
                            System.out.println("produced:");
                            System.out.println("Empty subclause!!!");
                            System.out.println("----------------------------------");
                            return true;
                        }
                        
                        //ola ta subclauses p einai kainourgia ginodai add sth vash gnvshs
                        if(!newsubclauses.contains(newsubclause) && !clauses.contains(newsubclause))
                        {
                            System.out.println("----------------------------------");
                            System.out.println("Resolution between");
                            //Ci.print();
                            System.out.println("and");
                            //Cj.print();
                            System.out.println("produced:");
                            //newsubclause.print();
                            //newsubclauses.add(newsubclause);
                            System.out.println("----------------------------------");
                        }
                    }                           
                }
            }
            
            boolean newClauseFound = false;

            //elegxei an dhmiourgithikan nea clauses se aythn thn epanalhpsh
            for(int i = 0; i < newsubclauses.size(); i++)
            {
                if(!clauses.contains(newsubclauses.get(i)))
                {
                    clauses.getSubclauses().addAll(newsubclauses);                    
                    newClauseFound = true;
                }                        
            }

            //an den dhmiourgithikan shmainei oti den ginetai na apodeixthei logika apo th vash gnwshs to a
            if(!newClauseFound)
            {
                System.out.println("Not found new clauses");
                stop = true;
            }   
        }
        return false;
    }
}
