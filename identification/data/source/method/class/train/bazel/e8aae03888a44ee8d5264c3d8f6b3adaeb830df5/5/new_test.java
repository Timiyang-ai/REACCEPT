  @Test
  public void getOptionValue_ReturnsDefaultValueIfNotSet() throws Exception {
    TransitiveOptionDetails details =
        TransitiveOptionDetails.forOptionsForTesting(parseOptions(ImmutableList.of(Options.class)));
    assertThat(details.getOptionValue("boolean_option")).isEqualTo(true);
  }