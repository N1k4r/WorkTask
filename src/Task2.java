import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    static CountDownLatch countDownLatch;
    static ArrayList<FutureTask<String>> tasks = new ArrayList<>();
    static ExecutorService service = Executors.newFixedThreadPool(3);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write the path to the file. Write \"run\" to run the analysis.");
        String path;
        ArrayList<String> files = new ArrayList<>();
        while(true){
            System.out.print("path - ");
            path = sc.nextLine();
            if (path.equalsIgnoreCase("run"))
                break;
            else if (Files.exists(Path.of(path)))
                files.add(path);
            else
                System.out.println("Invalid file path, try again");
        }
        countDownLatch = new CountDownLatch(files.size());
        for(var file : files){
            FutureTask<String> task = new FutureTask<>(new Task(file));
            tasks.add(task);
            service.execute(task);
        }
        countDownLatch.await();
        service.close();
        for(var task : tasks){
            System.out.println(task.get());
        }
    }

    public static class Task implements Callable<String> {
        String path;
        String text;
        public Task(String path) {
            this.path = path;
        }

        @Override
        public String call() {
            StringBuilder builder = new StringBuilder();
            try(BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
                while (reader.ready()){
                    builder.append(reader.readLine());
                }
                text = builder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String result = "------------------" + "\n" + Paths.get(path).getFileName() + "\n" +
                    "Words - " + countValue("[a-zA-Zа-я-А-Я]+") + "\n" +
                    "Sentences - " + countValue("\\.") + "\n" +
                    "Spaces - " + countValue(" ") + "\n" +
                    "Symbols - " + countValue("\\D");
            countDownLatch.countDown();
            return result;
        }
        public int countValue(String regex){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            int x = 0;
            while(matcher.find()) {
                x++;
            }
            return x;
        }
    }
}