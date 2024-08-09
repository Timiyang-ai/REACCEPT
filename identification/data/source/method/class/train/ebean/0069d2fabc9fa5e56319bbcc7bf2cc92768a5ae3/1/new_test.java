  @Test
  public void read(){

    ExtraDdl read = ExtraDdlXmlReader.read();
    assertNotNull(read);
  }