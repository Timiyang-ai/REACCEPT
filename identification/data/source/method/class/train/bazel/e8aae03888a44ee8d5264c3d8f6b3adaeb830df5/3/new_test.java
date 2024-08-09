  @Test
  public void getOptionClass_ReturnsClassOfPresentOptions() throws Exception {
    TransitiveOptionDetails details =
        TransitiveOptionDetails.forOptionsForTesting(parseOptions(ImmutableList.of(Options.class)));
    assertThat(details.getOptionClass("boolean_option")).isEqualTo(Options.class);
  }