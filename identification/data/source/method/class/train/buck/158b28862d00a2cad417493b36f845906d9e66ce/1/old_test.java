  @Test
  public void getRustLibraryFlags() {
    RustBuckConfig customConfig =
        new RustBuckConfig(
            FakeBuckConfig.builder()
                .setSections(
                    ImmutableMap.of(
                        "rust#custom", ImmutableMap.of("rustc_flags", "-flag1"),
                        "rust", ImmutableMap.of("rustc_flags", "-flag2")))
                .build());
    assertThat(customConfig.getRustcLibraryFlags("custom"), Matchers.contains("-flag1"));
    assertThat(customConfig.getRustcLibraryFlags("custom2"), Matchers.contains("-flag2"));
  }