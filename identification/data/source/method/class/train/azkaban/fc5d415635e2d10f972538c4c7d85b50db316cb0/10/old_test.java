  @Test
  public void getOsTotalFreeMemorySize() {
    final List<String> lines =
        Arrays.asList("MemFree:        1 kB", "Buffers:          2 kB", "Cached:          3 kB",
            "SwapFree:    4 kB",
            "Foo: 10 kB");

    final long size = this.util.getOsTotalFreeMemorySizeFromStrings(lines, OsMemoryUtil.MEM_KEYS);
    assertEquals(10, size);
  }