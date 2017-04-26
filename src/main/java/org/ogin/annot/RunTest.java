package org.ogin.annot;

import org.ogin.annot.test.TestExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ogin
 */
public class RunTest {
    private static Logger logger = LoggerFactory.getLogger(RunTest.class);

    public static void main(String[] args) {
        int passed = 0, failed = 0, count = 0, ignore = 0;
        Class<TestExample> obj = TestExample.class;

        //Process @TesterInfo
        if (obj.isAnnotationPresent(TesterInfo.class)) {
            Annotation annotation = obj.getAnnotation(TesterInfo.class);
            TesterInfo testerInfo = (TesterInfo) annotation;
            logger.info("Priority {}" , testerInfo.priority());
            logger.info("Created By {}" ,  testerInfo.createdBy());
            logger.info("Tags :");

            int tagLength = testerInfo.tags().length;

            for (String tag : testerInfo.tags()) {
                if (tagLength > 1) {
                    logger.info(tag + ", ");
                }
                else {
                    logger.info(tag);
                }
                tagLength--;
            }
            logger.info("LastModified: {}", testerInfo.lastModifiedBy());
        }

        // Process @Test
        for (Method method : obj.getDeclaredMethods()) {
            //if method is annoted with @Test
            if (method.isAnnotationPresent(Test.class)) {
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;

                // if enabled = true (default)
                if (test.enabled()) {
                    try {
                        method.invoke(obj.newInstance());
                        logger.info("%s - Test- passed" + method.getName());

                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                    } catch (InstantiationException e) {
                        logger.error(e.getMessage());
                    } catch (InvocationTargetException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
    }
}
