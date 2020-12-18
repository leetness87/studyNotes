import java.util.Scanner;

public class funtimes {

	public static void main(String[] args) {
		Scanner scanner1 = new Scanner(System.in);
		System.out.print("Enter your name:");
		String name;
		name = scanner1.next();
		System.out.print("Enter your age (years):");
		int age = scanner1.nextInt();
		System.out.print("Enter your Height (m): ");
		double height = scanner1.nextDouble();
		System.out.print("Enter your wright (kg):");
		double weight = scanner1.nextDouble();
		double bmi = weight / (height * height);
		scanner1.close();
		System.out.println("Hello " + name + "!");
		System.out.println("You are " + age + " but your body mass index is " + bmi + ".");
		
		if (bmi < 16.5) {
			System.out.println("You are Severly underweight");
		}
		else if (bmi <= 18.5){
			System.out.println("You are Underweight");
		}
		else if (bmi <= 25){
			System.out.println("You are normal");
		}
		else if (bmi <= 30){
			System.out.println("You are Overweight");
		}
		else if (bmi <= 35){
			System.out.println("You are Obese class 1");
		}
		else if (bmi <= 40){
			System.out.println("You are Obese class 2");
		}
		else if(bmi <= 45){
			System.out.println("You are Severely Obese");
		}
		else if (bmi <= 50){
			System.out.println("You are Morbidly Obese");
		}
		else if (bmi <= 60){
			System.out.println("You are Super Obese");
		}
		else if (bmi > 60){
			System.out.println("You are Hyper Obese");
		}
	}

}
