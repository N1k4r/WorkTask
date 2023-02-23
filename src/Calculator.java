public enum Calculator {
    PLUS{
        @Override
        public double action(Double[] numbers){
            double result = 0;
            for (double number : numbers) {
                result += number;
            }
            return result;
        }
    },
    MINUS{
        @Override
        public double action(Double[] numbers){
            double result = numbers[0] - numbers[1];
            if(numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    result -= numbers[i];
                }
            return result;
        }
    },
    MULTIPLY{
        @Override
        public double action(Double[] numbers){
            double result = numbers[0] * numbers[1];
            if (numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    result *= numbers[i];
                }
            return result;
        }
    },DIVIDE{
        @Override
        public double action(Double[] numbers){
            double result = numbers[0] / numbers[1];
            if (numbers.length > 2)
                for (int i = 2; i < numbers.length; i++) {
                    result /= numbers[i];
                }
            return result;
        }
    };
    public abstract double action(Double[] numbers);
}
