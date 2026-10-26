package service;

import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements EventLogger {

    private final List<String> entries = new ArrayList<>();

    @Override
    public void log(String message) {
        entries.add(message);
    }

    public List<String> getEntries() {
        return entries;
    }
}
