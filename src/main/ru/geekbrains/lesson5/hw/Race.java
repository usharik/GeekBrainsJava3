package ru.geekbrains.lesson5.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {

    private List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}