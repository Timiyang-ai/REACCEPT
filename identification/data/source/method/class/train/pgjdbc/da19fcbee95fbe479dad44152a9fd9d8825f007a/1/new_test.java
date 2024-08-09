  private static void read(InputStream is, int... expected) throws IOException {
    byte[] actual = new byte[4];
    Arrays.fill(actual, (byte) 0x00);
    int nActual = is.read(actual);
    int[] actualInts = new int[4];
    for (int i = 0; i < actual.length; i++) {
      actualInts[i] = actual[i] & 0xff;
    }
    if (expected.length > 0) {
      // Ensure "expected" has 4 bytes
      expected = Arrays.copyOf(expected, 4);
      assertEquals(Arrays.toString(expected), Arrays.toString(actualInts));
    } else {
      assertEquals("should be end-of-stream", -1, nActual);
      is.close();
    }
  }