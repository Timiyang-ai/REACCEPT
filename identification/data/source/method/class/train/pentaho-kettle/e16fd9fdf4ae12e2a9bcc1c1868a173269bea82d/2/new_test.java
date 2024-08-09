  @Test
  public void setSelectLength() {
    selectValuesMeta.setSelectLength( new int[] { 1, 2 } );
    assertArrayEquals( new int[] { 1, 2 }, selectValuesMeta.getSelectLength() );
  }