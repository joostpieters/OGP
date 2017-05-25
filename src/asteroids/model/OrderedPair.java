package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

@Value
public class OrderedPair {

	public double X;
	public double Y;
	
	public OrderedPair(double xcoordinate, double ycoordinate){
		X = xcoordinate;
		Y = ycoordinate;
	}
	
	public double getX(){
		return X;
	}
	
	public double getY(){
		return Y;
	}
	
	public double getLength(){
		return Math.sqrt(this.dotProduct(this));
	}
	
	public double[] toArray() {
		return new double[]{getX(),getY()};
	}
	
	public OrderedPair sum(OrderedPair vector){
		return new OrderedPair(this.getX()+vector.getX(),this.getY()+vector.getY());
	}
	
	public OrderedPair multiply(double factor){
		return new OrderedPair(this.getX()*factor,this.getY()*factor);
	}
	
	public OrderedPair getDifference(OrderedPair vector){
		return new OrderedPair(vector.getX()-this.getX(),vector.getY()-this.getY());
	}
	
	public double getDistance(OrderedPair vector){
		OrderedPair difference = getDifference(vector);
		return Math.sqrt(difference.dotProduct(difference));
	}
	
	/**
	 * Calculates the dot product of this OrderedPair and the given OrderedPair
	 * @param  vector
	 * 	       A given OrderedPair
	 * @return Return the dot product of the two OrderedPairs
	 * 	       | result.equals(this.getX()*vector.getX()+this.getY()*vector.getY())
	 */
	@Raw
	public double dotProduct(OrderedPair vector) {
		return this.getX()*vector.getX()+this.getY()*vector.getY();
	}
	
	@Override
	public boolean equals(Object other){
		if (!(other instanceof OrderedPair)) return false;
		OrderedPair otherPair = (OrderedPair) other;
		return (getX() == otherPair.getX() && getY() == otherPair.getY());
	}
	
	@Override
	public int hashCode(){
		return Double.hashCode(getX()+getY());
		
	}
	
	@Override
	public String toString(){
		return "X: " + getX() + ", Y: " + getY();
	}
	
}
