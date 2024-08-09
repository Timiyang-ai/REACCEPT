  @Test
  public void setSelectPrecision() {
    selectValuesMeta.setSelectPrecision( new int[] { 1, 2 } );
    assertArrayEquals( new int[] { 1, 2 }, selectValuesMeta.getSelectPrecision() );
  }