package cn.hssnow.pi.support;

import java.io.File;
import java.io.IOException;

public class GpioController {

    private Pin pin;

    private GpioController(Pin pin) {
        this.pin = pin;
    }

    public static GpioController newInstance(Pin pin) {
        if (!pin.isGpio()) {
            throw new RuntimeException("pin " + pin + " is not gpio pin");
        }
        return new GpioController(pin);
    }

    public PinState getState() {
        if (!isExported()) {
            return null;
        }
        try {
            String value = FileUtil.readFromFile(Constants.GPIO_PATH + "/gpio" + pin.getBcmCode() + "/" + Constants.DIRECTION);
            return PinState.get(Integer.valueOf(value));
        } catch (IOException ignored) {
        }
        return null;
    }

    public boolean setState(PinState state) {
        if (!isExported()) {
            return false;
        }
        try {
            FileUtil.writeToFile(Constants.GPIO_PATH + "/gpio" + pin.getBcmCode() + "/" + Constants.STATE, state.getValue());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public PinDirection getDirection() {
        if (!isExported()) {
            return null;
        }
        try {
            String value = FileUtil.readFromFile(Constants.GPIO_PATH + "/gpio" + pin.getBcmCode() + "/" + Constants.DIRECTION);
            return PinDirection.get(value);
        } catch (IOException ignored) {
        }
        return null;
    }

    public boolean setDirection(PinDirection direction) {
        if (!isExported()) {
            return false;
        }
        try {
            FileUtil.writeToFile(Constants.GPIO_PATH + "/gpio" + pin.getBcmCode() + "/" + Constants.DIRECTION, direction.getValue());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean export() {
        if (isExported()) {
            return true;
        }
        try {
            FileUtil.writeToFile(Constants.GPIO_PATH + "/" + Constants.EXPORT, pin.getBcmCode());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean unexport() {
        if (!isExported()) {
            return true;
        }
        try {
            FileUtil.writeToFile(Constants.GPIO_PATH + "/" + Constants.UNEXPORT, pin.getBcmCode());
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean isExported() {
        File file = new File(Constants.GPIO_PATH + "/gpio" + pin.getBcmCode());
        return file.exists() && file.isDirectory();
    }

}
