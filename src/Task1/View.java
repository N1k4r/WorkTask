package Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class View {
    static Scanner scanner = new Scanner(System.in);

    public String printStartMenu(){
        System.out.println();
        System.out.println(Arrays.toString(Calculator.values()));
        System.out.println("add - add calculate task");
        System.out.println("run - start tasks");
        System.out.println("exit - terminate");
        System.out.print("command - ");
        return scanner.nextLine();
    }

    public void printBooleanSetAction(boolean result){
        System.out.println(result ? "Task added!" : "Task was not added. Unknown action or incorrect numbers value");
    }

    public void printUnknownCommand(){
        System.out.println("Unknown command");
    }

    public void printResultsTasks(List<Future<String>> results) throws ExecutionException, InterruptedException {
        if (results.isEmpty()){
            System.out.println("Tasks is empty");
        } else
            for(var result : results){
                System.out.println(result.get());
            }
    }

    public String readAction(){
        System.out.print("Enter action - ");
        return scanner.nextLine();
    }

    public String[] readNumbers(){
        System.out.println("Write a number, or press Enter to finish");
        List<String> listNumbers = new ArrayList<>();
        String number;
        while(!(number = scanner.nextLine()).equals("")){
            listNumbers.add(number);
        }
        return listNumbers.toArray(new String[]{});
    }
}
