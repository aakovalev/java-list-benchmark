package org.playground;

import java.util.LinkedList;
import java.util.List;

public class LinkedListBenchmark extends ListBenchmark {
    @Override
    List<Integer> createList() {
        return new LinkedList<>();
    }
}
