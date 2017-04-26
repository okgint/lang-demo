package org.ogin.annot.test;

import org.ogin.annot.Test;
import org.ogin.annot.TesterInfo;

/**
 * @author ogin
 */
@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createdBy = "ogin.org",
        tags = {"sales", "test"}
)
public class TestExample {
    @Test
    void testA() {
        if(true) {
            throw new RuntimeException("This test always failed");
        }
    }
    @Test(enabled = false)
    void testB() {
        if(false) {
            throw new RuntimeException("This test always passed");
        }
    }
    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            // do nothing
        }
    }
}
