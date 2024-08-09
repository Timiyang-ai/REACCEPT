  @Test
  public void test_copyOf_List() {
    assertContent(DoubleArray.copyOf(ImmutableList.of(1d, 2d, 3d)), 1d, 2d, 3d);
    assertContent(DoubleArray.copyOf(ImmutableList.of()));
  }