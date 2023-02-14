package com.mapstructwithbuilder.mapstructwithbuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
  This is an annotation named "Default" that is used to mark a constructor.
  The annotation is marked with the "Target" annotation, which specifies that
  it can only be applied to constructors. The "Retention" annotation indicates
  that the annotation will be retained by the compiler at compile-time and will
   be available in the class file, but will not be visible at runtime.
   The annotation is used as a marker to indicate that a particular constructor
   should be used as a default constructor during code generation.
*/
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.CLASS)
public @interface Default {
}
