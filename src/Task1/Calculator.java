package Task1;

public enum Calculator {
    PLUS(" + "), MINUS(" - "), MULTIPLY(" * "), DIVIDE(" / "),
    FACTORIAL("! "){
        @Override
        public String action(Double[] numbers) {
            String finalResult = "";
            for (Double number : numbers) {
                int result = 1;
                int x = (int) ((double) number);
                for (int j = x; j > 1; j--) {
                    result *= j;
                }
                finalResult += x + "! = " + result + " | ";
            }
            return finalResult + "\n" + "-".repeat(finalResult.length());
        }
    };

    private final String actionSymbol;

    Calculator(String actionSymbol) {
        this.actionSymbol = actionSymbol;
    }

    public String action(Double[] numbers){
        double result = numbers[0];
        String finalResult = result + "";
        if (numbers.length > 1){
            for (int i = 1; i < numbers.length; i++) {
                finalResult += actionSymbol + numbers[i];
                result = calculate(result, numbers[i]);
            }
            finalResult += " = " + result;
        }
        return finalResult + "\n" + "-".repeat(finalResult.length());
    }
    
    public double calculate(Double x, Double y){
        return switch (actionSymbol){
            case " + " -> x + y;
            case " - " -> x - y;
            case " * " -> x * y;
            default -> x / y;
        };

    }
}
