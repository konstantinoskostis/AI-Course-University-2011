package FirstOrder;
public class Parameter{
	private String name;
	private boolean type;//true an h parametros einai constant(stathera)/false an h parametros einai variable(metavlhth)
	
	public Parameter(String name,boolean t){
		this.name=name;
		this.type=t;
		}
		
	public Parameter(Parameter pIn){
		this.name = pIn.name;
		this.type = pIn.type;
	}
	
	
	public String getParameterName(){
		return this.name;
	}
	
	public void setParameterName(String name){
		this.name=name;
	}
	
	public void setType(boolean t){
		this.type=t;
	}
	
	public String returnType(){
		if(type){
			return "Constant";
		}
		else{
			return "Variable";
		}
	}
        
     public boolean isConstant()
    {
		if(this.returnType()=="Constant")
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
        public boolean isEqualTo(Parameter right){
			if( (name.equals(right.name)) && (type==right.type)  ){
				return true;
			}
			return false;
		}
    
    public void setParameter(Parameter pIn){
    	setParameterName(pIn.getParameterName());
    	setType(pIn.type);
    }//allazei mia parametro(mporei apo metavliti na tin kanei stathera) xrisimeuei stin antikatastasi
    
        
}//end class
	
	
