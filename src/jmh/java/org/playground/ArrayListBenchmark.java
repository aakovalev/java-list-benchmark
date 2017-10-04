package org.playground;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBenchmark extends ListBenchmark {
    @Override
    List<Integer> createList() {
        return new ArrayList<>();
    }
}
