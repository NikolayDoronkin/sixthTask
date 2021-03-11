package com.epam.task.sixth.entity;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Base {

    private static final int terminals = 3;
    private static final Lock instanceLock = new ReentrantLock();
    private static final AtomicReference<Base> instance = new AtomicReference<>();
    private final Semaphore semaphore = new Semaphore(terminals);
    private final Lock terminalLock = new ReentrantLock();
    private final Queue<Terminal> terminalList;
    private final Lock wagonLock = new ReentrantLock();
    private final Queue<Wagon> wagons;

    private Base() {
        this.terminalList = IntStream.range(0, terminals)
                .mapToObj(value -> new Terminal())
                .collect(Collectors.toCollection(LinkedList::new));

        this.wagons = new PriorityQueue<>((first, second) ->
                -Boolean.compare(first.isHasPerishableFood(), second.isHasPerishableFood()));
    }

    public static Base getInstance() {
        if (instance.get() == null) {
            try {
                instanceLock.lock();

                if (instance.get() == null) {
                    Base base = new Base();
                    instance.getAndSet(base);
                }
            } finally {
                instanceLock.unlock();
            }
        }

        return instance.get();
    }

    private void processNextWagon() throws Exception {
        semaphore.acquire();
        wagonLock.lock();
        try {
            Wagon wagon = wagons.poll();
            terminalLock.lock();
            Terminal terminal = terminalList.poll();

            if (wagon == null || terminal == null) {
                throw new Exception("Van or terminal is null");
            }
            terminal.processing(wagon);
            terminalList.add(terminal);
        } finally {
            terminalLock.unlock();
            wagonLock.unlock();
            semaphore.release();
        }
    }

    public void addWagon(Wagon wagon) throws Exception {
        try {
            wagonLock.lock();
            wagons.add(wagon);
        } finally {
            wagonLock.unlock();
        }

        processNextWagon();
    }
}