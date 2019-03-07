package cn.hssnow.pi.support;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum PinDirection {
    OUT("out"),
    IN("in");

    @Getter private String value;

    public static PinDirection get(String value) {
        return Arrays.stream(values()).filter(direction -> direction.value.equals(value)).findFirst().orElse(null);
    }
}
