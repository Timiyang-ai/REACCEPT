@Test
  public void testReset() throws IOException {
    String output = singleLineFileInit(file, Charsets.UTF_8);

    PositionTracker tracker = new DurablePositionTracker(meta, file.getPath());
    ResettableInputStream in = new ResettableFileInputStream(file, tracker);

    String result1 = readLine(in, output.length());
    assertEquals(output, result1);

    in.reset();
    String result2 = readLine(in, output.length());
    assertEquals(output, result2);

    String result3 = readLine(in, output.length());
    assertNull(result3);

    in.close();
  }