  @Test
  public void setStatus_Ok() {
    EndSpanOptions endSpanOptions = EndSpanOptions.builder().setStatus(Status.OK).build();
    assertThat(endSpanOptions.getStatus()).isEqualTo(Status.OK);
  }