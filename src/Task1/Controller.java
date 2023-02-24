package Task1;

import java.util.concurrent.ExecutionException;

public class Controller {
    private final Model model = new Model();
    private final View view = new View();
    private boolean exit = false;

    public void execute() throws ExecutionException, InterruptedException {
        while(!exit){
            String command = view.printStartMenu();
            switch (command){
                case "add" -> view.printBooleanSetAction(model.addTask(view.readAction(), view.readNumbers()));
                case "run" -> view.printResultsTasks(model.getResultListTask());
                case "exit" -> exit = true;
                default -> view.printUnknownCommand();
            }
        }
    }
}
