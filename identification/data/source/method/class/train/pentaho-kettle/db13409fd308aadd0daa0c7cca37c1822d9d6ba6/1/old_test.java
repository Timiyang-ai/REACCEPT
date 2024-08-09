  @Test
  public void getFileEncodingTest() throws Exception {
    String path = getClass().getResource( "txt-sample.txt" ).getPath();
    String encoding = ValueDataUtil.getFileEncoding( new ValueMetaString(), path );
    assertEquals( "US-ASCII", encoding );
  }