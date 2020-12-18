import javax.swing.JOptionPane;
public class JOption {

	public static void main(String[] args) {
		String name=JOptionPane.showInputDialog("Enter your name");
		int age=Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
		double height=Double.parseDouble(JOptionPane.showInputDialog("Enter your height (m)"));
		double weight=Double.parseDouble(JOptionPane.showInputDialog("Enter your weight (Kg)"));
		double bmi=weight/(height*height);
		JOptionPane.showMessageDialog(null, "Hello "+name+"!");
		JOptionPane.showMessageDialog(null, "You are "+age+" and your body mass index is "+bmi+".");

		if (bmi < 16.5) {
			JOptionPane.showMessageDialog(null,"You are Severly underweight");
		}
		else if (bmi <= 18.5){
			JOptionPane.showMessageDialog(null,"You are Underweight");
		}
		else if (bmi <= 25){
			JOptionPane.showMessageDialog(null,"You are normal");
		}
		else if (bmi <= 30){
			JOptionPane.showMessageDialog(null,"You are Overweight");
		}
		else if (bmi <= 35){
			JOptionPane.showMessageDialog(null,"You are Obese class 1");
		}
		else if (bmi <= 40){
			JOptionPane.showMessageDialog(null,"You are Obese class 2");
		}
		else if(bmi <= 45){
			JOptionPane.showMessageDialog(null,"You are Severely Obese");
		}
		else if (bmi <= 50){
			JOptionPane.showMessageDialog(null,"You are Morbidly Obese");
		}
		else if (bmi <= 60){
			JOptionPane.showMessageDialog(null,"You are Super Obese");
		}
		else if (bmi > 60){
			JOptionPane.showMessageDialog(null,"You are Hyper Obese");
		}
	}

}
