//ayth h class adiproswpeuei ena literal ths protasiakhs logikhs
package PL;
public class Literal{
	
	private String name;//onoma ths metavlhths
	private boolean Neg;//timh pou apodidetai sthn metavlhth
        private int operator;//-1=tipota,0=AND,1=OR
        //o operator adiproswpeuei to ti syndesmos akolouthei
	
	public Literal(String n, boolean Neg){
		this.name=n;
		this.Neg=Neg;
                this.operator=-1;
	}//constructor enos literal
	
	
	
	/***********Setters/Getters***************/
	
	public String getName(){return this.name;}
	
	public void setName(String n){this.name=n;}
	
	public boolean getNeg(){return this.Neg;}
	
	public void setNeg(boolean v){this.Neg=v;}
        
        public void setOperator(int o){this.operator=o;}
        
        public int getOperator(){return operator;}
	
	/*******************************************/
	
	//
	public int hashCode(){
		if(this.Neg){
			return this.name.hashCode() + 1;
        }
        else{
           return this.name.hashCode() + 0;                        
        }
    }
    
    public int compareTo(Literal x){
            int a = 0;
            int b = 0;
            
            if(x.getNeg())
                a = 1;
            
            if(this.getNeg())
                b = 1;
            
            return x.getName().compareTo(name) + a-b;
    }
    
    public void print(){
        if(Neg)
            System.out.println("NOT_" + name);
        else
            System.out.println(name);
    }  
	
	
}//end Class literal
