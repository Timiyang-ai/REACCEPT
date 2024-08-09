  @Test
  public void getSizeFromBytes() {
    class TestCase {
      String mExpected;
      long mInput;

      public TestCase(String expected, long input) {
        mExpected = expected;
        mInput = input;
      }
    }

    List<TestCase> testCases = new ArrayList<>();
    testCases.add(new TestCase("4B", 1L << 2));
    testCases.add(new TestCase("8B", 1L << 3));
    testCases.add(new TestCase("4096B", 1L << 12));
    testCases.add(new TestCase("8.00KB", 1L << 13));
    testCases.add(new TestCase("4096.00KB", 1L << 22));
    testCases.add(new TestCase("8.00MB", 1L << 23));
    testCases.add(new TestCase("4096.00MB", 1L << 32));
    testCases.add(new TestCase("8.00GB", 1L << 33));
    testCases.add(new TestCase("4096.00GB", 1L << 42));
    testCases.add(new TestCase("8.00TB", 1L << 43));
    testCases.add(new TestCase("4096.00TB", 1L << 52));
    testCases.add(new TestCase("8.00PB", 1L << 53));
    testCases.add(new TestCase("4096.00PB", 1L << 62));

    for (TestCase testCase : testCases) {
      assertEquals(testCase.mExpected, FormatUtils.getSizeFromBytes(testCase.mInput));
    }
  }