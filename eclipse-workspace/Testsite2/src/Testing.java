
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		int n, i, sum = 0;
		System.out.println("Enter a postive number");
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		i = 1;
		while (i <= n) {
			sum = sum + i;
			i++;

		}
		System.out.println("The sum of all numbers between 0 and your selection are " + sum);
	}
}