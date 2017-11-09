package sample;

public class Circle extends Shape {
	
	double r;

	public Circle(double radius){
		r = radius;
	}

	double getArea() {
		return Math.PI * r * r;
	}

}
