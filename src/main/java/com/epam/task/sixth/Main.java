package com.epam.task.sixth;

import com.epam.task.sixth.data.JSONReader;
import com.epam.task.sixth.data.JSONReaderException;
import com.epam.task.sixth.entity.Wagon;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {

    private static final String INPUT_FILE = "./src/main/resources/input.json";

    public static void main(String[] args) throws  JSONReaderException, ExecutionException, InterruptedException {

        JSONReader jsonReader = new JSONReader();
        List<Wagon> wagons;
        wagons = jsonReader.readData(INPUT_FILE);

        ExecutorService executorService = Executors.newFixedThreadPool(wagons.size());

        List<Future<?>> futures = wagons.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());

        executorService.shutdown();

        for (Future<?> future : futures) {
            future.get();
        }

        System.out.println("Wagons after processing:");
        wagons.forEach(System.out::println);
    }

}
