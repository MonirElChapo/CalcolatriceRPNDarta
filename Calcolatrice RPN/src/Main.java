import java.util.Stack;

class RPNCalculator {

    public static double calculateRPN(String expression) {
        Stack<Double> stack = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    System.err.println("Errore: Operatore senza abbastanza operandi.");
                    return Double.NaN; // Valore non valido
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(operand1, operand2, token);
                stack.push(result);
            } else {
                System.err.println("Errore: Token non riconosciuto - " + token);
                return Double.NaN; // Valore non valido
            }
        }

        if (stack.size() == 1) {
            return stack.pop();
        } else {
            System.err.println("Errore: Troppi operandi.");
            return Double.NaN; // Valore non valido
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperator(String str) {
        return str.matches("[+\\-*/]");
    }

    public static double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    System.err.println("Errore: Divisione per zero.");
                    return Double.NaN; // Valore non valido
                }
                return operand1 / operand2;
            default:
                return Double.NaN; // Valore non valido
        }
    }

    public static void main(String[] args) {
        String expression = "3 4 + 5 * 2 /";
        double result = calculateRPN(expression);
        System.out.println("Risultato: " + result);
    }
}
