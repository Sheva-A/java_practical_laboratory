package service;

public class ConsoleLogger implements EventLogger {

    @Override
    public void log(String message) {
        System.out.println("    [консоль] " + message);
    }
}
