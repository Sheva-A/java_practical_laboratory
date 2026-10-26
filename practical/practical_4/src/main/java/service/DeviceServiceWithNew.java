package service;

import model.Device;

public class DeviceServiceWithNew {

    // Сервіс сам створює залежність через new
    private final ConsoleLogger logger = new ConsoleLogger();

    public void start(Device device) {
        logger.log("Запуск: " + device.info());
        logger.log(device.work());
    }
}
