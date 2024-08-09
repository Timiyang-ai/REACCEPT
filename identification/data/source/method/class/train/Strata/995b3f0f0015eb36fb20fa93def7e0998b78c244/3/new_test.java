  @Test
  public void test_resolveValues_dateBased() {
    ValueStep step1 = ValueStep.of(date(2014, 2, 1), ValueAdjustment.ofReplace(300d));
    ValueStep step2 = ValueStep.of(date(2014, 3, 1), ValueAdjustment.ofReplace(400d));
    // no steps
    ValueSchedule test0 = ValueSchedule.of(200d, ImmutableList.of());
    assertThat(test0.resolveValues(SCHEDULE)).isEqualTo(DoubleArray.of(200d, 200d, 200d));
    // step1
    ValueSchedule test1a = ValueSchedule.of(200d, ImmutableList.of(step1));
    assertThat(test1a.resolveValues(SCHEDULE)).isEqualTo(DoubleArray.of(200d, 300d, 300d));
    // step2
    ValueSchedule test1b = ValueSchedule.of(200d, ImmutableList.of(step2));
    assertThat(test1b.resolveValues(SCHEDULE)).isEqualTo(DoubleArray.of(200d, 200d, 400d));
    // step1 and step2
    ValueSchedule test2 = ValueSchedule.of(200d, ImmutableList.of(step1, step2));
    assertThat(test2.resolveValues(SCHEDULE)).isEqualTo(DoubleArray.of(200d, 300d, 400d));
  }