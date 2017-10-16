import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Maths Maths = new Maths();

        double answer = 0;
        double inputA, inputB;
        char operator;
        boolean done = false;

        while (done == false) {
            System.out.print("Please enter you first number: ");

            inputA = input.nextDouble();
            System.out.print("Please enter type of operation: ");
            operator = input.next().charAt(0);
            System.out.print("Please enter you second number: ");
            inputB = input.nextDouble();

            switch (operator) {
                case '+': answer = Maths.add(inputA, inputB);
                    break;
                case '-': answer = Maths.subtract(inputA, inputB);
                    break;
                case '*': answer = Maths.multiply(inputA, inputB);
                    break;
                case '/': answer = Maths.divide(inputA, inputB);
                    break;
                case '^': answer = Maths.power(inputA, inputB);
                    break;
            }

            System.out.println(answer);
        }

        input.close();

    }

}