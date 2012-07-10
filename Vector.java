public class Vector{

	private final double[] components;
	private final Radical magnitude;
	
	public Vector(double[] initial, double[] terminal){
		checkValidity(initial, terminal);
		
		this.components = new double[initial.length];
		
		for(int i = 0; i < this.components.length; ++i){
			this.components[i] = terminal[i] - initial[i];
		}
		
		double magnitudeValue = 0;
		for(int i = 0; i < components.length; ++i){
			double temp = this.components[i];
			magnitudeValue += temp * temp;
		}
		this.magnitude = new Radical(magnitudeValue);
		
		this.getMagnitude();
	}
	
	public Vector(int dimensions){
		this.components = populateArray(dimensions, 0);
		
		double magnitudeValue = 0;
		for(int i = 0; i < components.length; ++i){
			double temp = this.components[i];
			magnitudeValue += temp * temp;
		}
		this.magnitude = new Radical(magnitudeValue);
	}
	
	public Vector(double[] terminal){
		this.components = terminal;
		
		double magnitudeValue = 0;
		for(int i = 0; i < components.length; ++i){
			double temp = this.components[i];
			magnitudeValue += temp * temp;
		}
		this.magnitude = new Radical(magnitudeValue);
	}
	
	public Vector(double i, double j, double k){
		this(new double[] {i, j, k});
	}
	
	public double[] populateArray(int dimensions, double value){
		double[] result = new double[dimensions];
		
		for(int i = 0; i < dimensions; ++i){
			result[i] = value;
		}
		
		return result;
	}
	
	private void checkValidity(Vector otherVector){
		if(this.components.length != otherVector.components.length){
			throw new RuntimeException();
		}
	}
	
	private void checkValidity(double[] initial, double[] terminal){
		if(initial != terminal){
			throw new RuntimeException();
		}
	}
	
	private void is3Space(Vector otherVector){
		checkValidity(otherVector);
		if(this.components.length != 3){
			throw new RuntimeException();
		}
	}
	
	public Radical getMagnitude(){
		return this.magnitude;
	}
	
	public Vector add(Vector otherVector){
		checkValidity(otherVector);
		
		double[] result = new double[this.components.length];
		
		for(int i = 0; i < components.length; ++i){
			result[i] = this.components[i] + otherVector.components[i];
		}
		
		return new Vector(result);
	}
	
	public Vector scalarMult(double scalar){
		double[] result = new double[this.components.length];
		for(int i = 0; i < this.components.length; ++i){
			result[i] = scalar * this.components[i];
		}
		
		return new Vector(result);
	}
	
	public Vector subtract(Vector otherVector){
		return this.add(otherVector.scalarMult(-1));
	}
	
	public double dotProduct(Vector otherVector){
		double dotResult = 0;
		for(int i = 0; i < this.components.length; ++i){
			dotResult += (this.components[i] * otherVector.components[i]);
		}
		
		return dotResult;
	}
	
	public Vector crossProduct(Vector otherVector){
		is3Space(otherVector);
		
		double[] result = new double[3];
		
		double iComponent = this.components[1] * otherVector.components[2];
		iComponent -= (this.components[2] * otherVector.components[1]);
		
		double jComponent = this.components[0]*otherVector.components[2];
		jComponent -= (otherVector.components[0]*this.components[2]);
		
		double kComponent = this.components[0]*otherVector.components[1];
		kComponent -= (otherVector.components[0]*this.components[1]);
		
		return new Vector(iComponent, -jComponent, kComponent);
	}
	
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i = 0; i < this.components.length; ++i){
			sb.append(this.components[i]);
			if(i == this.components.length-1){
				sb.append(">");
			}
			else{
				sb.append(", ");
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		Vector v1 = new Vector(1,2,3);
		Vector v2 = new Vector(3,4,5);
		
		System.out.println(v1.getMagnitude());
		
		System.out.println(v1.crossProduct(v2));
	}	
}