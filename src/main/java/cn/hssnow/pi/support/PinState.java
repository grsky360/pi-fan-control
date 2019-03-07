package cn.hssnow.pi.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum PinState {
    HIGH(1),
    LOW(0);

    @Getter private Integer value;

    public static PinState get(int value) {
        return Arrays.stream(values()).filter(state -> state.value == value).findFirst().orElse(null);
    }
}
