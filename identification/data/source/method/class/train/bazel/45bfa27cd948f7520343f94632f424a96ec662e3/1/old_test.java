  @Test
  public void getSelectRestriction_RetrievesRestrictionObject() throws Exception {
    TransitiveOptionDetails details =
        TransitiveOptionDetails.forOptionsForTesting(parseOptions(ImmutableList.of(Options.class)));
    assertThat(details.getSelectRestriction("nonselectable_option")).isNotNull();
  }