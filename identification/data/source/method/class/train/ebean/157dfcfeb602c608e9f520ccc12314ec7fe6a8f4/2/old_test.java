  @Test
  public void read_file() throws Exception {

    File testFile = new File("src/test/resources/autotune/test-autotune.xml");

    Autotune tuneInfo = AutoTuneXmlReader.read(testFile);
    assertThat(tuneInfo.getOrigin()).isNotEmpty();
  }