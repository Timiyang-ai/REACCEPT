  @Test
  public void test_range() {
    assertThat(Predefined.range(1, 10), instanceOf(IntRange.class));
    assertThat(Predefined.range(1, 10L), instanceOf(LongRange.class));
    assertThat(Predefined.range(1L, 10), instanceOf(LongRange.class));
    assertThat(Predefined.range(1L, 10L), instanceOf(LongRange.class));
    assertThat(Predefined.range(10), instanceOf(IntRange.class));
    assertThat(Predefined.range(10L), instanceOf(LongRange.class));
    assertThat(Predefined.range(10), is(Predefined.range(0, 10)));
    assertThat(Predefined.range(10L), is(Predefined.range(0L, 10L)));
    assertThat(Predefined.range('a', 'd'), instanceOf(CharRange.class));
    assertThat(Predefined.range('D'), instanceOf(CharRange.class));
    assertThat(Predefined.range('D'), is(Predefined.range('A', 'D')));

    assertThat(Predefined.range(ONE, TEN), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(1, TEN), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(1L, TEN), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(ONE, 10L), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(ONE, 10), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(TEN), instanceOf(BigIntegerRange.class));
    assertThat(Predefined.range(TEN), is(Predefined.range(ZERO, TEN)));
  }