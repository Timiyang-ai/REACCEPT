  @Test
  public void trimFlagValues_leavesNonFeatureFlagValuesAlone() throws Exception {
    BuildOptions options =
        emptyBuildOptions()
            .toBuilder()
            .addStarlarkOption(Label.parseAbsoluteUnchecked("//unrelated/starlark:option"), true)
            .build();
    options =
        FeatureFlagValue.replaceFlagValues(
            options,
            ImmutableMap.of(
                Label.parseAbsoluteUnchecked("//label:a"),
                "value",
                Label.parseAbsoluteUnchecked("//label:d"),
                "otherValue"));
    options =
        FeatureFlagValue.trimFlagValues(
            options,
            ImmutableSet.of(
                Label.parseAbsoluteUnchecked("//label:a"),
                Label.parseAbsoluteUnchecked("//label:b")));

    options = FeatureFlagValue.trimFlagValues(options, ImmutableSet.of());

    assertThat(options.getStarlarkOptions())
        .containsEntry(Label.parseAbsoluteUnchecked("//unrelated/starlark:option"), true);
  }