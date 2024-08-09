  @Test
  public void methodWithClassAndName() {
    Matcher<MethodTree> matcher =
        Matchers.methodWithClassAndName("com.google.errorprone.foo.bar.Test", "myMethod");
    CompilationTestHelper.newInstance(methodTreeCheckerSupplier(matcher), getClass())
        .addSourceLines(
            "com/google/errorprone/foo/bar/Test.java",
            "package com.google.errorprone.foo.bar;",
            "public class Test {",
            "  // BUG: Diagnostic contains:",
            "  public void myMethod() {}",
            "}")
        .doTest();
  }