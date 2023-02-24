package Task2;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class View {
    Scanner scanner = new Scanner(System.in);
    public String printStartMenu(){
        System.out.println();
        System.out.println("add - add file");
        System.out.println("run - start analysis files");
        System.out.println("exit - terminate");
        System.out.print("command - ");
        return scanner.nextLine();
    }

    public void printBooleanAddAnalysisTask(boolean task){
        System.out.println(task ? "Task added!" : "Task was not added. Incorrect file path");
    }

    public void printUnknownCommand(){
        System.out.println("Unknown command");
    }

    public void printResultAnalysisTask(List<FutureTask<String>> results) throws ExecutionException, InterruptedException {
        if(results.isEmpty())
            System.out.println("Task for analysis empty");
        else
            for (var result : results)
                System.out.println(result.get());
    }

    public String readFilePath(){
        System.out.print("File path - ");
        return scanner.nextLine();
    }
}
