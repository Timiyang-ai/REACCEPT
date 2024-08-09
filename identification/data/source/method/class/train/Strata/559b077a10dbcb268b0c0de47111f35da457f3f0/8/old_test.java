  @Test
  public void test_withInfo() {
    DefaultCurveMetadata base = DefaultCurveMetadata.of(CURVE_NAME);
    assertThat(base.findInfo(CurveInfoType.DAY_COUNT).isPresent()).isFalse();
    DefaultCurveMetadata test = base.withInfo(CurveInfoType.DAY_COUNT, ACT_360);
    assertThat(base.findInfo(CurveInfoType.DAY_COUNT).isPresent()).isFalse();
    assertThat(test.findInfo(CurveInfoType.DAY_COUNT).isPresent()).isTrue();
  }