  @Test
  public void replaceFlagValues_reflectedInGetFlagValues() throws Exception {
    Map<Label, String> originalMap =
        ImmutableMap.of(
            Label.parseAbsoluteUnchecked("//label:a"), "value",
            Label.parseAbsoluteUnchecked("//label:b"), "otherValue");
    BuildOptions options = FeatureFlagValue.replaceFlagValues(emptyBuildOptions(), originalMap);
    assertThat(FeatureFlagValue.getFlagValues(options)).containsExactlyEntriesIn(originalMap);
  }