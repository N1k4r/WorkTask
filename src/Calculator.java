public enum Calculator {
    PLUS{
        @Override
        public String action(Double[] numbers){
            String finalResult = numbers[0] + " + " + numbers[1];
            double result = numbers[0] + numbers[1];
            if(numbers.length > 2)
                for(int i = 2; i < numbers.length; i++) {
                    finalResult += " + " + numbers[i];
                    result += numbers[i];
                }
            finalResult += " = " + result;
            return finalResult + "\n" + "-".repeat(finalResult.length());
        }
    },
    MINUS{
        @Override
        public String action(Double[] numbers){
            String finalResult = numbers[0] + " - " + numbers[1];
            double result = numbers[0] - numbers[1];
            if(numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    finalResult += " - " + numbers[i];
                    result -= numbers[i];
                }
            finalResult += " = " + result;
            return finalResult + "\n" + "-".repeat(finalResult.length());
        }
    },
    MULTIPLY{
        @Override
        public String action(Double[] numbers){
            String finalResult = numbers[0] + " * " + numbers[1];
            double result = numbers[0] * numbers[1];
            if (numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    finalResult += " * " + numbers[i];
                    result *= numbers[i];
                }
            finalResult += " = " + result;
            return finalResult + "\n" + "-".repeat(finalResult.length());
        }
    },
    DIVIDE{
        @Override
        public String action(Double[] numbers){
            String finalResult = numbers[0] + " / " + numbers[1];
            double result = numbers[0] / numbers[1];
            if (numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    finalResult += " / " + numbers[i];
                    result /= numbers[i];
                }
            finalResult += " = " + result;
            return finalResult + "\n" + "-".repeat(finalResult.length());
        }
    },
    FACTORIAL{
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
    public abstract String action(Double[] numbers);
}
