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

    private final String symbol;

    Calculator(String symbol) {
        this.symbol = symbol;
    }

    public String action(Double[] numbers){
        String finalResult = numbers[0] + symbol + numbers[1];
        double result = reg(numbers[0], numbers[1]);
        if (numbers.length > 2)
            for (int i = 2; i < numbers.length; i++) {
                finalResult += symbol + numbers[i];
                result = reg(result, numbers[i]);
            }
        finalResult += " = " + result;
        return finalResult + "\n" + "-".repeat(finalResult.length());
    }
    
    public double reg(Double x, Double y){
        return switch (symbol){
            case " + " -> x + y;
            case " - " -> x - y;
            case " * " -> x * y;
            case " / " -> x / y;
            default -> x;
        };

    }
}
