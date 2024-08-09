  @Test
  public void allowsMultipleValues_ReturnsFalseForUndefinedOption() throws Exception {
    TransitiveOptionDetails details =
        TransitiveOptionDetails.forOptionsForTesting(parseOptions(ImmutableList.of(Options.class)));
    assertThat(details.allowsMultipleValues("undefined_option")).isFalse();
  }