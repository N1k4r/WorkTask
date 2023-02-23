import java.util.*;
import java.util.concurrent.*;

public class Task1 {
    static Scanner scanner = new Scanner(System.in);
    static CountDownLatch countDownLatch;
    static ArrayList<Map<String, Double[]>> listTasks = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Arrays.toString(Calculator.values()));
        while(true){
            listTasks.add(Map.of(actions(), numbers()));
            System.out.println("Write any character to add calculations or \"run\" to start tasks.");
            String action = scanner.nextLine();
            if (action.equals("run"))
                break;
        }
        startTask();
    }
    public static String actions(){
        System.out.print("Write an action - ");
        String finalAction;
        while(true){
            String action = scanner.nextLine();
            finalAction = action;
            if(Arrays.stream(Calculator.values()).noneMatch(x -> String.valueOf(x).equals(action)))
                System.out.println("Invalid action, try again");
            else
                break;
        }
        return finalAction;
    }

    public static Double[] numbers(){
        ArrayList<Double> numbers = new ArrayList<>();
        System.out.println("Write a number, or \"exit\" to finish");
        while (true) {
            System.out.print("number - ");
            String number = scanner.nextLine();
            if(number.equals("exit")){
                if(numbers.size() > 1)
                    break;
                else
                    System.out.println("Minimum number of numbers is 2");
            } else if (number.matches("[\\d\\.]+"))
                    numbers.add(Double.parseDouble(number));
                else
                    System.out.println("Unknown command or incorrect value");
        }
        return numbers.toArray(new Double[]{});
    }

    public static void startTask() throws InterruptedException, ExecutionException {
        countDownLatch = new CountDownLatch(listTasks.size());
        List<Future<Double>> list = new ArrayList<>();
            for(var taskList : listTasks){
                for(var action : taskList.keySet()){
                    FutureTask<Double> future = new FutureTask<>(new CalculateTask(action, taskList.get(action)));
                    new Thread(future).start();
                    list.add(future);
                }
            }
        countDownLatch.await();
        System.out.println("------------");
        for (var l : list)
            System.out.println(l.get());
    }
    public static class CalculateTask implements Callable<Double> {
        String action;
        Double[] numbers;

        public CalculateTask(String action, Double[] numbers) {
            this.action = action;
            this.numbers = numbers;
        }

        @Override
        public Double call() throws Exception {
            Double number = Calculator.valueOf(action).action(numbers);
            countDownLatch.countDown();
            return number;
        }
    }

}
