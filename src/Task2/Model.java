package Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    private static CountDownLatch countDownLatch;
    private final List<FutureTask<String>> analysisTaskList = new ArrayList<>();
    private final ExecutorService service = Executors.newFixedThreadPool(3);
    private final ArrayList<String> filesPaths = new ArrayList<>();

    public boolean addAnalysisTask(String filePath){
        Path path = Path.of(filePath);
        if (Files.exists(path) && !Files.isDirectory(path)){
            filesPaths.add(filePath);
            return true;
        }
        return false;
    }

    public List<FutureTask<String>> getAnalysisResult() throws InterruptedException {
        analysisTaskList.clear();
        countDownLatch = new CountDownLatch(filesPaths.size());
        for (var filePath : filesPaths) {
            FutureTask<String> future = new FutureTask<>(new AnalysisThreadTask(filePath));
            service.submit(future);
            analysisTaskList.add(future);
        }
        countDownLatch.await();
        service.close();
        filesPaths.clear();
        return analysisTaskList;
    }

    public static class AnalysisThreadTask implements Callable<String> {
        private final Path path;
        private String textFromFile;
        public AnalysisThreadTask(String path) {
            this.path = Path.of(path);
        }
        @Override
        public String call() {
            StringBuilder builder = new StringBuilder();
            try(BufferedReader reader = Files.newBufferedReader(path)) {
                while (reader.ready()){
                    builder.append(reader.readLine());
                }
                textFromFile = builder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String result = "------------------" + "\n" + path.getFileName() + "\n" +
                    "Words - " + getAnalysisValue("[a-zA-Zа-я-А-Я]+") + "\n" +
                    "Sentences - " + getAnalysisValue("\\.") + "\n" +
                    "Spaces - " + getAnalysisValue(" ") + "\n" +
                    "Symbols - " + getAnalysisValue("\\D");
            countDownLatch.countDown();
            return result;
        }
        public int getAnalysisValue(String regex){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(textFromFile);
            int x = 0;
            while(matcher.find()) {
                x++;
            }
            return x;
        }
    }
}
