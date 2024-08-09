  @Test
  public void parseFlagsTestCoverageDirOutputFile() {
    LcovMergerFlags flags =
        LcovMergerFlags.parseFlags(
            new String[] {
              "--coverage_dir=my_dir", "--output_file=my_file",
            });
    assertThat(flags.coverageDir()).isEqualTo("my_dir");
    assertThat(flags.outputFile()).isEqualTo("my_file");
    assertThat(flags.reportsFile()).isNull();
    assertThat(flags.filterSources()).isEmpty();
  }