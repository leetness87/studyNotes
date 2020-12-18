

import java.util.Scanner;

public class Testing {
	public static int add(int x, int y) {
		return x + y;
	}

	public static int subtract(int x, int y) {
		return x - y;
	}

	public static int multiply(int x, int y) {
		return x * y;
	}

	public static double divide(int x, int y) {
		return x / y;
	}

	public static void main(String[] args){
		System.out.println("1.Add");
		System.out.println("2.Subtract");
		System.out.println("3.Multiply");
		System.out.println("4.Divide");
		Scanner input = new Scanner(System.in);
		int selection = input.nextInt();
		if (selection == 1 || selection ==2 || selection ==3 || selection ==4){
		System.out.println("enter first number");
		int num1 = input.nextInt();
		System.out.println("enter second number");
		int num2 = input.nextInt();
		if (selection == 1){
		System.out.println(num1 + " + " + num2 + " = "+ add(num1,num2));
		}
		else if (selection == 2){
		System.out.println(num1 + " - " + num2 + " = " + subtract(num1, num2));
		}
		else if (selection == 3){
		System.out.println(num1 + " * " + num2 + " = "+ multiply(num1, num2));
		}
		else if (selection == 4){
		System.out.println(num1 + " / " + num2 + " = "+ divide(num1, num2));
		}

		}
		else{
		System.out.println("invalid input");
		}
}
}