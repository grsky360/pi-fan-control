package cn.hssnow.pi.fanctrl;

import cn.hssnow.pi.support.*;

import java.io.IOException;

public class FanCtrl {

    private static final Pin pin = Pin.getByBcm(18);

    private static final int upperTemp = 45;

    private static final int lowerTemp = 40;

    static void run() {
        GpioController gpioController = GpioController.newInstance(pin);
        gpioController.export();
        try {
            gpioController.setDirection(PinDirection.OUT);
            gpioController.setState(PinState.HIGH);
            while (true) {
                int temp = getTemp();
                if (temp >= upperTemp) {
                    gpioController.setState(PinState.LOW);
                } else if (temp < lowerTemp) {
                    gpioController.setState(PinState.HIGH);
                }
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            gpioController.unexport();
        }
    }

    private static int getTemp() {
        try {
            String temp = FileUtil.readFromFile(Constants.TEMPERATURE);
            return Integer.valueOf(temp);
        } catch (IOException e) {
            return 0;
        }
    }
}
