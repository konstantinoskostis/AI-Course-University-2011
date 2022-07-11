/**
 * @(#)Text1.java
 *
 *
 * @author 
 * @version 1.00 2011/12/29
 */
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

import PL.CNFSubClause;
import PL.Literal;

public class InputThingy {
	/*Scanner da_thingy;
	Scanner liner;
	Literal pros_apodiksi;
	Vector<String> keimeno;
	String [] grammes;
	
    public InputThingy(FileInputStream file) 
    {
    	da_thingy = new Scanner(file);
    	Scanner liner = da_thingy.useDelimiter(".\n"); //isws xriastei allagi tou delimiter an dn katalavei to ".\n"
    	
    }
    
    public String ReadLine()
    {	if(liner.hasNext())
    	return liner.next();
    	else return null;
    }
     
    //droped down sinartisi, prosperase tin, xrisimopoioume tin CreateKB()
    public CNFSubClause Create()
    { CNFSubClause clause = new CNFSubClause();
      Scanner worker = new Scanner(ReadLine());
      while (worker.hasNext())
      {String c = worker.findInLine("\\(*\\) ");
       Scanner w = new Scanner(c);
       w = w.useDelimiter("AND");
       while (w.hasNext())
       		{if (w.next(".").equals("~"))
       		 //isws xriastei rithmisi gia na petaei ektos ton xaraktira ~
       		 clause.literals.add(new Literal(w.next(),false));
       		 //else if (!w.hasNext("\\(*\\)"))
       		 //	{ if (w.next(".").equals("~")) pros_apodiksi = new Literal(w.next(),false);
       		 //	  else pros_apodiksi = new Literal(w.next(),true);
       		 //	}
       		 else clause.literals.add(new Literal(w.next(),true));
       		}//eswterika parenthesewn
      }//eswterika grammis
      return clause;
    }
    
    public String[] grammes()
    {keimeno = new Vector<String>();
     while (da_thingy.hasNext())
     {keimeno.add(da_thingy.next());
     }
     grammes = new String[keimeno.size()];
     for (int i=0; i<keimeno.size(); i++)
     {grammes[i] = keimeno.get(i);
     }
     return grammes;
    }
    
    public CNFSubClause CreateKB()
    {CNFSubClause clause = new CNFSubClause();
      String[] grammes = grammes();
      for(int i=0; i<grammes.length -1; i++)
      {Scanner worker = new Scanner(grammes[i]);
      	while (worker.hasNext())
      	{ String c = worker.findInLine("\\(*\\) ");
      	  Scanner w = new Scanner(c);
      	  w = w.useDelimiter("AND");
       	  while (w.hasNext())
       	  {if (w.next(".").equals("~"))
       		 //isws xriastei rithmisi gia na petaei ektos ton xaraktira ~
       		 clause.literals.add(new Literal(w.next(),false));
       	   else clause.literals.add(new Literal(w.next(),true));
       	  }//end while 2
      	} //end while 1
      } //endfor
      return clause;
    }//end method
    
    public CNFSubClause CreateSC(String[] grammes, int intex)
    {CNFSubClause clause = new CNFSubClause();
     Scanner worker = new Scanner(grammes[intex]);
      	while (worker.hasNext())
      	{ String c = worker.findInLine("\\(*\\) ");
      	  Scanner w = new Scanner(c);
      	  w = w.useDelimiter("AND");
       	  while (w.hasNext())
       	  {if (w.next(".").equals("~"))
       		 //isws xriastei rithmisi gia na petaei ektos ton xaraktira ~
       		 clause.literals.add(new Literal(w.next(),false));
       	   else clause.literals.add(new Literal(w.next(),true));
       	  }//end while 2
      	} //end while 1
      	return clause;
    }
    
    public CNFSubClause pros_apodiksi()
    {CNFSubClause a;
     Literal l;
     String[] grammes = grammes();
     Scanner worker = new Scanner(grammes[grammes.length]);
       	a = new CNFSubClause ();
     	while (worker.hasNext("[a-z]"))
     		{ if(worker.next(".").equals("~"))
     		  l = new Literal (worker.next("[a-z]"),false);
     		  else l = new Literal (worker.next("[a-z]"),true);
     		  a.literals.add(l);
     		}
     return a;
    }
    
    
    */
    
    
    
    
}