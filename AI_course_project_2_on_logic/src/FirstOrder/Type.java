
package FirstOrder;

public class Type {
    boolean hasNo_NOT_infront;
	Type(boolean value){
		setNegativity(value);
	}
	
	public void setNegativity(boolean value){
		hasNo_NOT_infront = value;
	}
	
	public boolean getNegativity(){
		return hasNo_NOT_infront;
	}
}
