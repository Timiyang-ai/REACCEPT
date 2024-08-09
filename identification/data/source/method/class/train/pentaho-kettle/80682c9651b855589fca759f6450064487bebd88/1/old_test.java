  @Test
  public void loadFileContentInBinary() throws Exception {
    String path = getClass().getResource( "txt-sample.txt" ).getPath();
    byte[] content = ValueDataUtil.loadFileContentInBinary( new ValueMetaString(), path, true );
    assertTrue( Arrays.equals( "test".getBytes(), content ) );
  }