  @Test
  public void getLines() {
    List<String> lines = sourceFile.getLines();
    assertThat(lines).hasSize(8);
    assertThat(lines.get(0))
        .isEqualTo("// Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do");
    assertThat(lines.get(7)).isEqualTo("// est laborum.");
  }