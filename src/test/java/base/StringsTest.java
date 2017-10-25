package base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringsTest {
    @Test
    void returnEmptyString() {
        assertEquals("", Strings.returnEmptyString());
    }

}