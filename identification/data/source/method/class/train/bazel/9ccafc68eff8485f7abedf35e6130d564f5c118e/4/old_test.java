  @Test
  public void trim() throws Exception {
    BuildOptions original = BuildOptions.of(ImmutableList.of(CppOptions.class, JavaOptions.class));
    BuildOptions trimmed = original.trim(ImmutableSet.of(CppOptions.class));
    assertThat(trimmed.getFragmentClasses()).containsExactly(CppOptions.class);
  }