package Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Model {
    static CountDownLatch countDownLatch;
    private static final ArrayList<Map<String, Double[]>> listTasks = new ArrayList<>();
    private static final List<Future<String>> resultTastList = new ArrayList<>();

    public boolean addTask(String action, String[] numbers){
        if (calculateActionIsExist(action) && numbers.length != 0){
            for (var number : numbers){
                if (!number.matches("[\\d.-]+"))
                    return false;
            }
            Double[] doublesNumber = new Double[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                doublesNumber[i] = Double.valueOf(numbers[i]);
            }
            listTasks.add(Map.of(action, doublesNumber));
            return true;
        }
        return false;
    }

    public boolean calculateActionIsExist(String action){
        return Arrays.stream(Calculator.values()).anyMatch(x -> String.valueOf(x).equals(action));
    }

    public List<Future<String>> getResultListTask() throws InterruptedException {
        resultTastList.clear();
        countDownLatch = new CountDownLatch(listTasks.size());
        for(var taskList : listTasks)
            for(var action : taskList.keySet()){
                FutureTask<String> future = new FutureTask<>(() -> {
                    String result = Calculator.valueOf(action).action(taskList.get(action));
                    countDownLatch.countDown();
                    return result;
                });
                new Thread(future).start();
                resultTastList.add(future);
            }
        countDownLatch.await();
        listTasks.clear();
        return resultTastList;
    }
}
