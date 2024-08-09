  @Test
  public void test_merge_group2_within2_initialStub() {
    Schedule test = Schedule.builder()
        .periods(ImmutableList.of(P1_STUB, P2_NORMAL, P3_NORMAL))
        .frequency(P1M)
        .rollConvention(DAY_17)
        .build();
    Schedule expected = Schedule.builder()
        .periods(ImmutableList.of(P1_STUB, P2_3))
        .frequency(P2M)
        .rollConvention(DAY_17)
        .build();
    assertThat(test.mergeRegular(2, true)).isEqualTo(expected);
    assertThat(test.mergeRegular(2, false)).isEqualTo(expected);
    assertThat(test.merge(2, P2_NORMAL.getUnadjustedStartDate(), P3_NORMAL.getUnadjustedEndDate())).isEqualTo(expected);
    assertThat(test.merge(2, P2_NORMAL.getStartDate(), P3_NORMAL.getEndDate())).isEqualTo(expected);
  }