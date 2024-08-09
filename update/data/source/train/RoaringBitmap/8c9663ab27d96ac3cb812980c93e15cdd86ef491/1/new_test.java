@Test
  public void notTest1() {
    // Array container, range is complete
    final short[] content = {1, 3, 5, 7, 9};
    final MappeableContainer c = makeContainer(content);
    final MappeableContainer c1 = c.not(0, 65536);
    final short[] s = new short[65536 - content.length];
    int pos = 0;
    for (int i = 0; i < 65536; ++i) {
      if (Arrays.binarySearch(content, (short) i) < 0) {
        s[pos++] = (short) i;
      }
    }
    assertTrue(checkContent(c1, s));
    assertTrue(checkContent(c, content));
  }