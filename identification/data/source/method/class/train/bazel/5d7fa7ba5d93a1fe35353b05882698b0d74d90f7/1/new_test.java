  @Test
  public void diffForReconstruction_againstDifferentBase() throws Exception {
    BuildOptions base1 = BuildOptions.of(ImmutableList.of(CppOptions.class), "--compiler=base1");
    BuildOptions base2 = BuildOptions.of(ImmutableList.of(CppOptions.class), "--compiler=base2");
    BuildOptions other = BuildOptions.of(ImmutableList.of(CppOptions.class), "--compiler=other");

    OptionsDiffForReconstruction diff1 = BuildOptions.diffForReconstruction(base1, other);
    OptionsDiffForReconstruction diff2 = BuildOptions.diffForReconstruction(base2, other);

    assertThat(diff1).isNotEqualTo(diff2);
    assertThat(base1.applyDiff(diff1)).isEqualTo(other);
    assertThat(base2.applyDiff(diff2)).isEqualTo(other);
  }