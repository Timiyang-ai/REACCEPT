  @Test
  public void processJavacopts_useSourceByDefault() {
    TurbineOptions options = TurbineOptions.builder().setOutput("/out").build();
    ImmutableList<String> javacopts = JavacTurbine.processJavacopts(options);
    assertThat(javacopts).contains("-source");
    assertThat(javacopts).doesNotContain("--release");
  }