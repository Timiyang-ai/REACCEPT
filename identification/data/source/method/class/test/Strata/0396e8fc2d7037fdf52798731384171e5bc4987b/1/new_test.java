  @Test
  public void test_flatMap() {
    ValueWithFailures<List<String>> base =
        ValueWithFailures.of(ImmutableList.of("1", "a", "2"), ImmutableList.of(FAILURE1));
    ValueWithFailures<List<Integer>> test = base.flatMap(this::flatMapFunction);
    assertThat(test.getValue()).isEqualTo(ImmutableList.of(Integer.valueOf(1), Integer.valueOf(2)));
    assertThat(test.getFailures().size()).isEqualTo(2);
    assertThat(test.getFailures().get(0)).isEqualTo(FAILURE1);
    assertThat(test.getFailures().get(1).getReason()).isEqualTo(FailureReason.INVALID);
  }