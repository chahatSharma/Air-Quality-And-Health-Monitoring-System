
public class Test {

	public static void main(String[] args) {
		computeFactorial(5);
	}

	public static int computeFactorial(int number) {
		int result = number;
		if (number >= 0) {
			if (number == 0 || number == 1)
				return 1;
			result = result * computeFactorial(number - 1);

			return result;
		} else {
			return number;
		}
	}
}
