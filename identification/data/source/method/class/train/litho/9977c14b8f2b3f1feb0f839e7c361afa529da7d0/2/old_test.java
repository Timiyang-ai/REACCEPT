  @Test
  public void markPoint_pointsTraced() {
    mTestLithoStartupLogger.markPoint("_event1", "_stage1");
    mTestLithoStartupLogger.markPoint("_event2", "_stage2");
    mTestLithoStartupLogger.markPoint("_event3", "_stage4", "attr1");

    assertThat(mTestLithoStartupLogger.tracePointCount()).isEqualTo(3);
    assertThat(mTestLithoStartupLogger.getTracedPointAt(0)).isEqualTo("litho_event1_stage1");
    assertThat(mTestLithoStartupLogger.getTracedPointAt(1)).isEqualTo("litho_event2_stage2");
    assertThat(mTestLithoStartupLogger.getTracedPointAt(2)).isEqualTo("litho_attr1_event3_stage4");
  }