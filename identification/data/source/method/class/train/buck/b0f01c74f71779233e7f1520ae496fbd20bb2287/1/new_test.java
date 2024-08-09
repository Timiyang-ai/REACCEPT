  @Test
  public void combine() {
    Object[] result = FileFinder.combine(ImmutableSet.of(), "foo", ImmutableSet.of()).toArray();
    Arrays.sort(result);
    Assert.assertArrayEquals(new String[] {"foo"}, result);

    result =
        FileFinder.combine(ImmutableSet.of(), "foo", ImmutableSet.of(".exe", ".com", ".bat"))
            .toArray();
    Arrays.sort(result);
    Assert.assertArrayEquals(new String[] {"foo.bat", "foo.com", "foo.exe"}, result);

    result = FileFinder.combine(ImmutableSet.of("lib", ""), "foo", ImmutableSet.of()).toArray();
    Arrays.sort(result);
    Assert.assertArrayEquals(new String[] {"foo", "libfoo"}, result);
  }