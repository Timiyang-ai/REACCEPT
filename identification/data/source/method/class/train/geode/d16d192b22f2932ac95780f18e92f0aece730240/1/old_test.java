  @Test
  public void toString_returnsOrderedStringInBrackets() {
    Object[] arrayOfThree = new Object[] {"test", "testing", "tested"};

    assertThat(ArrayUtils.toString(arrayOfThree)).isEqualTo("[test, testing, tested]");
  }