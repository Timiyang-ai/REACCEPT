  @Test
  public void getOptions() {
    assertThat(TraceOptions.DEFAULT.getOptions()).isEqualTo(0);
    assertThat(TraceOptions.builder().setIsSampled(false).build().getOptions()).isEqualTo(0);
    assertThat(TraceOptions.builder().setIsSampled(true).build().getOptions()).isEqualTo(1);
    assertThat(TraceOptions.builder().setIsSampled(true).setIsSampled(false).build().getOptions())
        .isEqualTo(0);
    assertThat(TraceOptions.fromByte(FIRST_BYTE).getOptions()).isEqualTo(-1);
    assertThat(TraceOptions.fromByte(SECOND_BYTE).getOptions()).isEqualTo(1);
    assertThat(TraceOptions.fromByte(THIRD_BYTE).getOptions()).isEqualTo(6);
  }