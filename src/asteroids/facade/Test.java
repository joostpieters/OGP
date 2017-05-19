package asteroids.facade;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(sumfac(4.0));
		System.out.println(fac(4.0));
	}
	
	private static double sumfac(double s1){
		double a = s1;
		double t = 1.0;
		while(1.5<a){
			System.out.println("before:"+t+", "+a);
			t = t + (a*sumfac(a - 1.0));
			a = a - 1.0;
			System.out.println("after: "+t+", "+a);
		}
		return t;
	}
	
	public static double fac(double s1){
		System.out.println(s1);
		if(s1<1.5){return 1.0;}
		else{return s1*fac(s1-1.0);}
	}

}
