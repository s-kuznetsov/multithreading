import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerTest {

    @Test
    void convertNumeralsToNumber() {
        Handler handler = new Handler();
        try {
            assertEquals(10, handler.convertNumeralsToNumber("ten"));
            assertEquals(10, handler.convertNumeralsToNumber("TEN"));
            assertEquals(9999, handler.convertNumeralsToNumber("nine thousand nine hundred ninety nine"));
            assertEquals(1, handler.convertNumeralsToNumber("one"));
            assertEquals(11, handler.convertNumeralsToNumber("eleven"));
            assertEquals(100, handler.convertNumeralsToNumber("hundred"));
            assertEquals(101, handler.convertNumeralsToNumber("hundred  one"));
            assertEquals(99, handler.convertNumeralsToNumber("ninety nine"));
            assertEquals(1999, handler.convertNumeralsToNumber("thousand nine hundred ninety nine"));
            assertEquals(1999, handler.convertNumeralsToNumber("one thousand nine hundred ninety nine"));
            assertEquals(1999, handler.convertNumeralsToNumber(" one     thousand nine    hundred  ninety nine "));
            assertEquals(1500, handler.convertNumeralsToNumber("      thousand five    hundred   "));
            assertEquals(199, handler.convertNumeralsToNumber("hundred ninety nine"));
            assertEquals(199, handler.convertNumeralsToNumber("one hundred ninety nine"));
            assertEquals(99, handler.convertNumeralsToNumber("ninety nine"));
            assertEquals(999, handler.convertNumeralsToNumber("nine hundred ninety nine"));
            assertEquals(2100, handler.convertNumeralsToNumber("two thousand one hundred"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("rt");
        });
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("one one");
        });
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("hundred  one hundred  one");
        });
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("hundred hundred");
        });
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("thousand  one thousand  one");
        });
        Assertions.assertThrows(Exception.class, () -> {
            handler.convertNumeralsToNumber("hundred thousand");
        });
    }
}