public class Feature {
	
	private String name;
	private int value;
	
	public Feature(String nm){
		setName(nm);
		setValue(0);
	}
	
	public Feature(){
		setValue(0);
	}
	
	public void setName(String  n){
		name = n;
	}
	public String getName(){
		return name;
	}
	public void setValue(int c){
		value = c;
	}
	public int getValue(){
		return value;
	}

}
