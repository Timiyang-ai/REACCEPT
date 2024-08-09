  @Test
  public void load() throws Exception {
    addToolchain(
        "extra",
        "extra_toolchain_linux",
        ImmutableList.of("//constraints:linux"),
        ImmutableList.of("//constraints:linux"),
        "baz");

    // Create a static UnloadedToolchainContext.
    UnloadedToolchainContext unloadedToolchainContext =
        UnloadedToolchainContext.builder()
            .setExecutionPlatform(linuxPlatform)
            .setTargetPlatform(linuxPlatform)
            .setRequiredToolchainTypes(ImmutableSet.of(testToolchainType))
            .setRequestedLabelToToolchainType(
                ImmutableMap.of(testToolchainTypeLabel, testToolchainType))
            .setToolchainTypeToResolved(
                ImmutableBiMap.<ToolchainTypeInfo, Label>builder()
                    .put(
                        testToolchainType,
                        Label.parseAbsoluteUnchecked("//extra:extra_toolchain_linux_impl"))
                    .build())
            .build();

    // Create the prerequisites.
    ConfiguredTargetAndData toolchain =
        getConfiguredTargetAndData(
            Label.parseAbsoluteUnchecked("//extra:extra_toolchain_linux_impl"), targetConfig);

    // Resolve toolchains.
    ResolvedToolchainContext toolchainContext =
        ResolvedToolchainContext.load(
            toolchain.getTarget().getPackage().getRepositoryMapping(),
            unloadedToolchainContext,
            "test",
            ImmutableList.of(toolchain));
    assertThat(toolchainContext).isNotNull();
    assertThat(toolchainContext.forToolchainType(testToolchainType)).isNotNull();
    assertThat(toolchainContext.forToolchainType(testToolchainType).getValue("data"))
        .isEqualTo("baz");
  }