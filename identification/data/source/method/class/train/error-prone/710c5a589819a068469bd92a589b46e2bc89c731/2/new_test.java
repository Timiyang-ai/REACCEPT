  @Test
  public void methodReturnsNonPrimitiveType() {
    Matcher<MethodTree> matcher = Matchers.methodReturnsNonPrimitiveType();
    CompilationTestHelper.newInstance(methodTreeCheckerSupplier(matcher), getClass())
        .addSourceLines(
            "test/MethodReturnsSubtypeTest.java",
            "package test;",
            "public class MethodReturnsSubtypeTest {",
            "  public int doesntMatch() {",
            "    return 0;",
            "  }",
            "  public void doesntMatch2() {",
            "  }",
            "  // BUG: Diagnostic contains:",
            "  public String matches() {",
            "    return \"\";",
            "  }",
            "}")
        .doTest();
  }