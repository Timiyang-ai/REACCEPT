  @Test
  public void setSelectRename() {
    selectValuesMeta.setSelectRename( new String[] { FIRST_FIELD, SECOND_FIELD } );
    assertArrayEquals( new String[] { FIRST_FIELD, SECOND_FIELD }, selectValuesMeta.getSelectRename() );
  }