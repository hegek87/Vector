import java.util.ArrayList;

public class Radical{
	private double value;
	
	private int radicand;
	private int multiple;
	
	private double decimal;
	
	public Radical(double value){
		this.value = value;
		radicalForm();
		this.decimal = getValue();
	}
	
	public double getValue(){
		if(decimal == 0){
			return Math.sqrt(value);
		}
		return decimal;
	}
	
	private ArrayList<Integer> getDivisors(){
		ArrayList<Integer> divisors = new ArrayList<Integer>();
		int current = 2;
		while(current < value){
			if(Math.IEEEremainder(value, current) ==0){
				divisors.add(current);
			}
			++current;
		}
		return divisors;
	}
	
	public boolean isPerfect(double square){
		double root = Math.floor(Math.sqrt(square));
		if((square - (root * root)) < .01){
			return true;
		}
		return false;
	}
	
	private void radicalForm(){
		ArrayList<Integer> divisors = getDivisors();
		for(int i = divisors.size()-1; i >= 0; --i){
			if(isPerfect(divisors.get(i))){
				radicand = (int)value / divisors.get(i);
				multiple = (int)Math.floor(Math.sqrt(divisors.get(i)));
				return;
			}
		}
		this.radicand = (int)value;
		this.multiple = 1;
	}
	
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append((multiple == 1) ? "" : multiple);
		sb.append((char)251);
		sb.append("(");
		sb.append(this.radicand);
		sb.append(")");
		return sb.toString();
	}
	
	public static void main(String[] args){
		Radical test = new Radical(675);
		
		System.out.println(test);
	}
}