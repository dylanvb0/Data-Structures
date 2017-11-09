package sample;

public class Triangle extends Polygon {
	double l1;
	double l2;
	double l3;

	double getArea() {
		double p = (l1 + l2 + l3) / 2;
		return Math.sqrt(p * (p-l1) * (p-l2) * (p - l3));
	}

	boolean isIsosceles() {
		return (l1 == l2 || l2 == l3 || l1 == l3);
	}

	public Triangle(double l1, double l2, double l3) {
		super();
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
	}

}
