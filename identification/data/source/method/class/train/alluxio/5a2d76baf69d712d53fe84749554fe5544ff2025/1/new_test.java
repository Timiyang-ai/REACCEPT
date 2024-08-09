  @Test
  public void parametersToString() {
    class TestCase {
      String mExpected;
      Object[] mInput;

      public TestCase(String expected, Object[] objs) {
        mExpected = expected;
        mInput = objs;
      }
    }

    List<TestCase> testCases = new ArrayList<>();
    testCases.add(new TestCase("()", null));
    testCases.add(new TestCase("(null)", new Object[] {null}));
    testCases.add(new TestCase("()", new Object[] {""}));
    testCases.add(new TestCase("(foo)", new Object[] {"foo"}));
    testCases.add(new TestCase("(foo, bar)", new Object[] {"foo", "bar"}));
    testCases.add(new TestCase("(foo, , bar)", new Object[] {"foo", "", "bar"}));
    testCases.add(new TestCase("(, foo, )", new Object[] {"", "foo", ""}));
    testCases.add(new TestCase("(, , )", new Object[] {"", "", ""}));
    testCases.add(new TestCase("(1)", new Object[] {1}));
    testCases.add(new TestCase("(1, 2, 3)", new Object[] {1, 2, 3}));

    for (TestCase testCase : testCases) {
      assertEquals(testCase.mExpected, FormatUtils.parametersToString(testCase.mInput));
    }
  }