  @Test
  public void equalIncreasingByteArray() {
    class TestCase {
      boolean mExpected;
      byte[] mArray;
      int mLength;
      int mStart;

      public TestCase(boolean expected, byte[] array, int length, int start) {
        mExpected = expected;
        mArray = array;
        mLength = length;
        mStart = start;
      }
    }

    ArrayList<TestCase> testCases = new ArrayList<>();
    testCases.add(new TestCase(false, null, 0, 0));
    testCases.add(new TestCase(true, new byte[] {}, 0, 0));
    testCases.add(new TestCase(false, new byte[] {1}, 0, 0));
    testCases.add(new TestCase(true, new byte[] {}, 0, 3));
    testCases.add(new TestCase(false, new byte[] {1}, 0, 3));
    testCases.add(new TestCase(true, new byte[] {0}, 1, 0));
    testCases.add(new TestCase(false, new byte[] {1}, 1, 0));
    testCases.add(new TestCase(true, new byte[] {0, 1, 2}, 3, 0));
    testCases.add(new TestCase(false, new byte[] {0, 1, 2, (byte) 0xFF}, 3, 0));
    testCases.add(new TestCase(false, new byte[] {1, 2, 3}, 3, 0));
    testCases.add(new TestCase(true, new byte[] {3}, 1, 3));
    testCases.add(new TestCase(false, new byte[] {2}, 1, 3));
    testCases.add(new TestCase(true, new byte[] {3, 4, 5}, 3, 3));
    testCases.add(new TestCase(false, new byte[] {3, 4, 5, (byte) 0xFF}, 3, 3));
    testCases.add(new TestCase(false, new byte[] {2, 3, 4}, 3, 3));

    for (TestCase testCase : testCases) {
      boolean result = BufferUtils.equalIncreasingByteArray(testCase.mStart, testCase.mLength,
          testCase.mArray);
      assertEquals(testCase.mExpected, result);
    }
  }