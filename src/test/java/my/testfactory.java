import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class testfactory {

    @TestFactory
    Collection<DynamicTest> dynamicTests() {
        return Arrays.asList(
            DynamicTest.dynamicTest("foo-bar-123", () -> assertTrue(true)),
            DynamicTest.dynamicTest("this is not java-like", () -> assertTrue(true)),
            DynamicTest.dynamicTest("123_invalid_name", () -> assertTrue(true))
        );
    }
}
