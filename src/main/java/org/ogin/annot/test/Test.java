package org.ogin.annot.test;

/**
 * @author ogin
 */
public @interface Test {
    public boolean enabled() default true;
}
