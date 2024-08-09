  @Test
  public void getFeatureFlagValue_returnsValueOfFlagWhenRequestingSetFlag() throws Exception {
    Label ruleLabel = Label.parseAbsoluteUnchecked("//a:a");
    Optional<String> flagValue =
        getConfigurationWithFlags(ImmutableMap.of(ruleLabel, "valued"))
            .getFeatureFlagValue(new LabelArtifactOwner(ruleLabel));
    assertThat(flagValue).isPresent();
    assertThat(flagValue).hasValue("valued");
  }