package org.playground;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
abstract public class ListBenchmark {
    private static final int MAX_SIZE = 1000;
    private Random random = new Random();
    private List<Integer> implementation;
    private List<Integer> values = new ArrayList<>();
    private Integer nextValue;
    private Integer nextIndex;

    @Param ({"100", "1000", "10000", "100000"})
    private long initialSize = 0;

    @Setup(Level.Trial)
    public void generateValues() {
        for (int i = 0; i < MAX_SIZE; i++) {
            values.add(random.nextInt(MAX_SIZE));
        }
    }

    @Setup(Level.Iteration)
    public void setUp() {
        implementation = createList();
        for (int i = 0; i < initialSize; i++) {
            implementation.add(12345);
        }
    }

    @Setup(Level.Invocation)
    public void getNextValue() {
        int valueIndex = random.nextInt(MAX_SIZE);
        nextValue = values.get(valueIndex);
    }

    @Setup(Level.Invocation)
    public void generateNextIndex() {
        nextIndex = implementation.size() / 2;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public List<Integer> addToBeginning() {
        implementation.add(0, nextValue);
        return implementation;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public List<Integer> addToEnd() {
        implementation.add(nextValue);
        return implementation;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public List<Integer> insertIntoMiddle() {
        implementation.add(nextIndex, nextValue);
        return implementation;
    }
    
    abstract List<Integer> createList();
}