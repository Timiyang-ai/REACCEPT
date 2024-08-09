  @Test
  public void test_concat_varargs() {
    IntArray test1 = IntArray.of(1, 2, 3);
    assertContent(test1.concat(5, 6, 7), 1, 2, 3, 5, 6, 7);
    assertContent(test1.concat(new int[] {5, 6, 7}), 1, 2, 3, 5, 6, 7);
    assertContent(test1.concat(EMPTY_INT_ARRAY), 1, 2, 3);
    assertContent(IntArray.EMPTY.concat(new int[] {1, 2, 3}), 1, 2, 3);
  }