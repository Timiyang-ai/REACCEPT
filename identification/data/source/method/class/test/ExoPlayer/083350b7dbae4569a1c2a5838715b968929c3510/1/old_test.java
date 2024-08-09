  @Test
  public void getRetryDelayMsFor_dontRetryParserException() {
    assertThat(getDefaultPolicyRetryDelayOutputFor(new ParserException(), 1))
        .isEqualTo(C.TIME_UNSET);
  }