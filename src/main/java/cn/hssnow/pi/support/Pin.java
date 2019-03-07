package cn.hssnow.pi.support;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@ToString
public enum Pin {

    P1(1, "3.3V"),
    P2(2, "5V"),
    P3(3, 2, 8, "SDA.1"),
    P4(4, "5V"),
    P5(5, 3, 9, "SCL.1"),
    P6(6, "GND"),
    P7(7, 4, 7, "GPIO.7"),
    P8(8, 14, 15, "TXD"),
    P9(9, "GND"),
    P10(10, 15, 16, "RXD"),
    P11(11, 17, 0, "GPIO.0"),
    P12(12, 18, 1, "GPIO.1"),
    P13(13, 27, 2, "GPIO.2"),
    P14(14, "GND"),
    P15(15, 22, 3, "GPIO.3"),
    P16(16, 23, 4, "GPIO.4"),
    P17(17, "3.3V"),
    P18(18, 24, 5, "GPIO.5"),
    P19(19, 10, 12, "MOSI"),
    P20(20, "GND"),
    P21(21, 9, 13, "MISO"),
    P22(22, 25, 6, "GPIO.6"),
    P23(23, 11, 14, "SCLK"),
    P24(24, 8, 10, "CE0"),
    P25(25, "GND"),
    P26(26, 7, 11, "CE1"),
    P27(27, 0, 30, "SDA.0"),
    P28(28, 1, 31, "SCL.0"),
    P29(29, 5, 21, "GPIO.21"),
    P30(30, "GND"),
    P31(31, 6, 22, "GPIO.22"),
    P32(32, 12, 26, "GPIO.26"),
    P33(33, 13, 23, "GPIO.23"),
    P34(34, "GND"),
    P35(35, 19, 24, "GPIO.24"),
    P36(36, 16, 27, "GPIO.27"),
    P37(37, 26, 25, "GPIO.25"),
    P38(38, 20, 28, "GPIO.28"),
    P39(39, "GND"),
    P40(40, 21, 29, "GPIO.29"),

    ;
    @Getter private Integer boardId;
    @Getter private Integer bcmCode;
    @Getter private Integer wiringPiCode;
    @Getter private String name;

    private static final List<Integer> GPIO_BOARD_ID = Arrays.asList(
            7, 11, 12, 13, 15, 16, 18, 22, 29,
            31, 32, 33, 35, 36, 37, 38, 40
    );

    Pin(Integer boardId, String name) {
        this(boardId, null, null, name);
    }

    Pin(Integer boardId, Integer bcmCode, Integer wiringPiCode, String name) {
        this.boardId = boardId;
        this.bcmCode = bcmCode;
        this.wiringPiCode = wiringPiCode;
        this.name = name;
    }

    public Boolean isGpio() {
        return GPIO_BOARD_ID.contains(boardId);
    }

    public static Pin getByBcm(int code) {
        return Arrays.stream(values()).filter(pin -> pin.bcmCode != null && pin.bcmCode == code).findFirst().orElse(null);
    }

    public static Pin getGpioByCode(int code) {
        return Arrays.stream(values()).filter(pin ->
                GPIO_BOARD_ID.contains(pin.getBoardId()) &&
                        Integer.valueOf(pin.getName().split("\\.")[2]) == code
        ).findFirst().orElse(null);
    }
}
