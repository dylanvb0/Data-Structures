package project4;

public class UnlimitedIntegerTester {
	public static void main(String[] args) {

		UnlimitedInteger a;
		UnlimitedInteger b;
		UnlimitedInteger c;

		a = new UnlimitedInteger("12");
		System.out.println("A is: " + a);

		b = new UnlimitedInteger("7");
		System.out.println("B is: " + b);

		c = (a.add(b));

		System.out.print(a + " + " + b + " = " + c);
		if (c.toString().equals("19"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.subtract(b));
		System.out.print(a + " - " + b + " = " + c);
		if (c.toString().equals("5"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.multiply(b));
		System.out.print(a + " * " + b + " = " + c);
		if (c.toString().equals("84"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.pow(2));
		System.out.print(a + " ^ " + 2 + " = " + c);
		if (c.toString().equals("144"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (new UnlimitedInteger("5")).factorial();
		System.out.print("5! = " + c);
		if (c.toString().equals("120"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		System.out.println("\n\nAnd now for something a bit harder...");
		a = new UnlimitedInteger("839239482794723476287429374");
		b = new UnlimitedInteger("72384792834");

		c = (a.add(b));
		System.out.print(a + " + " + b + " = " + c);
		if (c.toString().equals("839239482794723548672222208"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.subtract(b));
		System.out.print(a + " - " + b + " = " + c);
		if (c.toString().equals("839239482794723403902636540"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.multiply(b));
		System.out.print(a + " * " + b + " = " + c);
		if (c.toString().equals("60748176100209366179381886675396305916"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (a.pow(4));
		System.out.print(a + " ^ " + 4 + " = " + c);
		if (c.toString()
				.equals("496070760820562664276199775648265093082469075439403000786371347578457203012689523655888432884242559592079376"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

		c = (new UnlimitedInteger("200")).factorial();
		System.out.print("200! = " + c);
		if (c.toString()
				.equals("788657867364790503552363213932185062295135977687173263294742533244359449963403342920304284011984623904177212138919638830257642790242637105061926624952829931113462857270763317237396988943922445621451664240254033291864131227428294853277524242407573903240321257405579568660226031904170324062351700858796178922222789623703897374720000000000000000000000000000000000000000000000000"))
			System.out.println("  Correct");
		else
			System.out.println("  Wrong!");

	}
}
