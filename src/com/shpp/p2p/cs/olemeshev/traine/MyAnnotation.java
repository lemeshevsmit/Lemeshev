package com.shpp.p2p.cs.olemeshev.traine;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String method() default "default";
}
