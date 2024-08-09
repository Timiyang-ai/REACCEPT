  @Test
  public void fromFile() throws Exception {
    Location location = Location.fromPathFragment(path);
    assertThat(location.getPath()).isEqualTo(path);
    assertThat(location.getStartOffset()).isEqualTo(0);
    assertThat(location.getEndOffset()).isEqualTo(0);
    assertThat(location.getStartLineAndColumn()).isNull();
    assertThat(location.getEndLineAndColumn()).isNull();
    assertThat(location.print()).isEqualTo(path + ":1");
  }