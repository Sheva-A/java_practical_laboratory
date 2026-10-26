package service;

import model.Device;

public class DeviceService {

    private final EventLogger logger;

    // Залежність передається через конструктор як інтерфейс
    public DeviceService(EventLogger logger) {
        this.logger = logger;
    }

    public void start(Device device) {
        logger.log("Запуск: " + device.info());
        logger.log(device.work());
    }
}
