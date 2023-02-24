package Task2;

import java.util.concurrent.ExecutionException;

public class Controller {
    private final Model model = new Model();
    private final View view = new View();
    private boolean exit = false;

    public void execute() throws InterruptedException, ExecutionException {
        while(!exit){
            String command = view.printStartMenu();
            switch (command){
                case "add" -> view.printBooleanAddAnalysisTask(model.addAnalysisTask(view.readFilePath()));
                case "run" -> view.printResultAnalysisTask(model.getAnalysisResult());
                case "exit" -> exit = true;
                default -> view.printUnknownCommand();
            }
        }
    }
}
